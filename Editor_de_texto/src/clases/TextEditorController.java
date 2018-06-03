/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.*;
import java.awt.event.*;
import javax.activation.FileDataSource;
import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import clases.Interpreter.Interpreter;
import clases.Interpreter.InterpreterException;
import com.sun.prism.paint.Color;
import editor_de_texto.EditorFrame;

import static java.awt.Color.RED;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

import generated.MonkeyParser;
import generated.MonkeyScanner;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author Josua
 */
public class TextEditorController extends WindowAdapter implements ActionListener, CaretListener, KeyListener {

    private int currentLine;
    private int currentCol;
    private File currentFile;
    private EditorFrame editor;
    private int fontSize;
    private TextEditorModel model;
    private boolean executeState;
    private List<String> consoleHistory;
    private int indexConsoleHistory = 0;


    public TextEditorController(EditorFrame window) {
        this.consoleHistory = new ArrayList<String>();
        this.editor = window;
        this.currentCol = 1;
        this.currentLine = 1;
        this.currentFile = null;
        this.fontSize = 14;
        model = new TextEditorModel();
        this.executeState=false;
        this.editor.executionArea.setVisible(true);
        this.editor.executionArea.setEnabled(true);

        this.editor.errorsArea.setEditable(false);
        this.editor.executionArea.setEditable(true);
        this.editor.executionPanel.setSelectedIndex(0);
        this.editor.errorsArea.setEditable(false); // desactivar edicion
    }


    public int getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }

    public TextEditorModel getModel() {
        return model;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void showException(JTextArea errorArea,Exception exception){
        //change to the errros panel
        this.editor.executionPanel.setSelectedIndex(1);
        errorArea.setForeground(RED);
        StringWriter stackTraceWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTraceWriter));
        errorArea.setText(stackTraceWriter.toString());
        JOptionPane.showMessageDialog(editor.getRootPane(),"La compilación fue abortada debido a un que ocurrió un error" +
                        " en el proceso","Alerta de sistema", JOptionPane.ERROR_MESSAGE);
    }

    public void printSemanticError(ArrayList<String> errors,JTextArea errorArea){
        errorArea.setForeground(RED);
        //change to the errros panel
        this.editor.executionPanel.setSelectedIndex(1);
        int size=errors.size();
        for (int i = 0; i < size; i++) {
            this.editor.errorsArea.append(errors.get(i));
            this.editor.errorsArea.append("\n");
        }
        JOptionPane.showMessageDialog(editor.getRootPane(),"La compilación fue abortada debido a un que ocurrió un error" +
                " en el proceso de verificación de semántica","Alerta de sistema", JOptionPane.ERROR_MESSAGE);
    }

    public void execute(JTextArea errorArea){
        this.saveFileChanges();
        this.editor.errorsArea.setText("");
        this.editor.executionArea.setText("");
        this.executeState = false;
        try {
            this.model.AnalizeAndExecute(this.currentFile.getPath());

            //check semantic errros
            if (this.model.getChecker().getErrorsList().size()>0){
                this.printSemanticError(this.model.getChecker().getErrorsList(),this.editor.errorsArea);
                this.executeState=true;
            }
            else{
                this.executeState=true;
                //go to code interpretation
                this.model.codeInterpretation(this.model.getTree(),this.editor.executionArea);
                this.editor.executionArea.setText("Cambios guardados\n\nCompilación exitosa\n\n");
                //JOptionPane.showMessageDialog(this.editor.getRootPane(), "Compilación exitosa", "Done", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (IOException exception){
            this.showException(errorArea,exception);
            this.executeState=false;
        }
        catch (ParseCancellationException exception){
            this.showException(errorArea,exception);
            this.executeState=false;
        }
        catch (InterpreterException e){
            this.executeState=false;
            errorArea.setForeground(RED);
            this.editor.executionArea.setText("The interpretation process ended due to an error found\n\n"
                    + e.getMessage());

        }
    }

    public void createFile(File file) {
        try {
            file.createNewFile();
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(editor.display.getText());
            printWriter.close();
            JOptionPane.showMessageDialog(editor.getRootPane(), "Saved", "Done", JOptionPane.INFORMATION_MESSAGE);
            this.currentFile=file;

        } catch (IOException ex) {
            Logger.getLogger(EditorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createNewFile(JFileChooser saveDialog) {
        int status = saveDialog.showOpenDialog(editor.getRootPane());
        if (status == JFileChooser.APPROVE_OPTION) {
            //We got directory. Now needs file name
            String fileName = JOptionPane.showInputDialog("File Name", "Untitled.txt");
            if (!fileName.contains(".txt")) {
                fileName += ".txt";
            }
            File f = new File(saveDialog.getSelectedFile() + "\\" + fileName);
            if (f.exists()) {
                JOptionPane.showMessageDialog(editor.getRootPane(), "File Already Exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                this.createFile(f);
            }
        } else {
            JOptionPane.showMessageDialog(editor.getRootPane(), "Error Occured", "Can not Save", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void readFile(File file) {
        try {
            Scanner scn = new Scanner(file);
            String buffer = "";
            while (scn.hasNext()) {
                buffer += scn.nextLine() + "\n";
            }
            editor.display.setText(buffer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EditorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveFileChanges() {
        if (this.currentFile != null) {
            try {
                PrintWriter printWriter = new PrintWriter(currentFile);
                printWriter.write(editor.display.getText());
                printWriter.close();

                this.editor.executionArea.setText("Changes saved");
                this.editor.executionPanel.setSelectedIndex(0);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EditorFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            createNewFile(editor.saveDialog);
        }
    }

    public void askForSaving() {
        //if a file is open or text area have content for a new file
        if ((this.currentFile != null) || editor.display.getText().length() > 0) {
            int ans = JOptionPane.showConfirmDialog(editor.getRootPane(), "Save Changes ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (ans == JOptionPane.YES_OPTION) {
                this.saveFileChanges();
            }
        }

    }

    public void openFile(JFileChooser fileOpener) {
        int status = fileOpener.showOpenDialog(editor.getRootPane());
        if (status == JFileChooser.APPROVE_OPTION) {
            this.currentFile = fileOpener.getSelectedFile();
            //clean text area content
            this.editor.display.setText("");
            this.editor.setTitle(this.currentFile.getName());
            this.readFile(this.currentFile);

        } else {
            this.editor.errorsArea.setText("No file selected");
        }
    }

    public void showTree(ParseTree tree, Parser parser){
        try {
            JFrame treeGUI = (JFrame) org.antlr.v4.gui.Trees.inspect(tree,parser);
            treeGUI.setVisible(true);

        }catch (Exception e){
            System.out.println("ERROR: " +e);
        }
    }


    /**
     * @param toLine    linea a la que se debe mover el cursor
     * @param toCol     col a la que se desea mover el cursor
     * @param exhibidor NOTA: este método asume que la columna destino existe en la línea destino
     *                  Por lo que únicamente se calcula la llegada a la línea destino, y luego se suman
     *                  la columna destino al contador de caracteres
     */
    public void setCursorPosition(int toLine, int toCol, JTextArea exhibidor) {
        char enter = '\n';
        String texto = exhibidor.getText();
        int lineCount = 1;
        int count = 0;
        int charCount = 0;
        while (lineCount < toLine) {
            if (texto.charAt(count) == enter) {
                lineCount++;
            }
            charCount++;
            count++;
        }
        exhibidor.setCaretPosition(charCount + toCol);
        updateCaretPositions(toLine, toCol, this.editor.line, this.editor.column);


    }

    public void updateCaretPositions(int row, int col, JLabel line, JLabel column) {
        this.setCurrentLine(row);
        this.setCurrentCol(col);
        line.setText("Line: " + String.valueOf(row));
        column.setText("Column: " + String.valueOf(col));
    }

    @Override
    public void caretUpdate(CaretEvent event) {

        //edit area would have a method with it's possible to get the JTextArea Name that make the event
        JTextArea editArea = (JTextArea) event.getSource();
        int linenum = 1;
        int columnnum = 1;

        try {

            int caretpos = editArea.getCaretPosition();

            linenum = editArea.getLineOfOffset(caretpos);


            columnnum = caretpos - editArea.getLineStartOffset(linenum);

            linenum += 1;
        } catch (Exception ex) {
            System.out.println("An error doesn't allow to finish the update process");
        }

        updateCaretPositions(linenum, columnnum, editor.line, editor.column);
    }

    @Override
    public void windowClosing(WindowEvent event) {
        this.askForSaving();
    }

    private void executionAreaKeyPressed(java.awt.event.KeyEvent evt) throws BadLocationException, IOException {//GEN-FIRST:event_executionAreaKeyPressed

        String linea = "";
        Document doc = this.editor.executionArea.getDocument();
        Element root = doc.getDefaultRootElement();
        Element element = root.getElement(root.getElementCount()-1);
        int start = element.getStartOffset();
        int end = element.getEndOffset();
        linea = doc.getText(start, end - start);
        int indice_ultima_linea = this.editor.executionArea.getDocument().getLength(); //retorna el numero de lineas


        if(evt.getExtendedKeyCode() == 10) {
            System.out.println(linea);
            // if there were errros in interpretation process
            if ( this.executeState==false ){
                JOptionPane.showMessageDialog(editor.getRootPane(), "You can't use command line because interpretation process failed", "System Alert", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try{
                this.consoleHistory.add(linea);
                this.indexConsoleHistory = this.consoleHistory.size() - 1;
                Interpreter interpreter = this.model.getInterpreter();
                interpreter.setFlagConsole(1); // find by for
                ANTLRInputStream input = new ANTLRInputStream(linea);
                MonkeyScanner scanner = new MonkeyScanner(input);
                CommonTokenStream tkn = new CommonTokenStream(scanner);
                MonkeyParser parser = new MonkeyParser(tkn);
                scanner.reset();

                ParseTree tree = parser.program();
                interpreter.visit(tree);
                interpreter.setFlagConsole(0); // find by storageIndex
            }catch (InterpreterException e){
                this.editor.executionArea.setText(this.editor.executionArea.getText()+ "\nError: "+e.getMessage()+"\n");
            }

        }
        else if(evt.getExtendedKeyCode() == 38){
            if(consoleHistory.size() == 0){
                this.editor.executionArea.setCaretPosition(indice_ultima_linea); //ubica el cursor al final
                return;
            }
            String respaldo = this.editor.executionArea.getText();
            System.out.println(indexConsoleHistory);
            if(this.indexConsoleHistory >= 0){
                this.editor.executionArea.setText(respaldo+this.consoleHistory.get(this.indexConsoleHistory));
                this.indexConsoleHistory--;
            }
        }
    }//GEN-LAST:event_executionAreaKeyPressed

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            //create a new file
            if (event.getActionCommand().equals(editor.openFile.getActionCommand())) {
                this.openFile(editor.fileOpener);
            } else if (event.getActionCommand().equals(editor.newFile.getActionCommand())) {
                this.setCurrentFile(null);
                this.editor.display.setText("");

            } else if (event.getActionCommand().equals(editor.saveButton.getActionCommand())) {
                this.saveFileChanges();
            } else if (event.getActionCommand().equals(editor.increase.getActionCommand())) {
                this.setFontSize(this.fontSize + 1);
                this.editor.display.setFont(new java.awt.Font("Monospaced", 0, this.fontSize));
            } else if (event.getActionCommand().equals(editor.decrease.getActionCommand())) {
                this.setFontSize(this.fontSize - 1);
                this.editor.display.setFont(new java.awt.Font("Monospaced", 0, this.fontSize));
            } //execute button was pressed
            else if (event.getActionCommand().equals(editor.execute.getActionCommand())) {
                //change to the execution panel
                this.editor.executionPanel.setSelectedIndex(0);
                if ((this.currentFile == null) && (this.editor.display.getText().length() > 0)) {
                    this.saveFileChanges();
                    if (this.currentFile!=null){
                        this.execute(this.editor.errorsArea);
                    }
                    else{
                        JOptionPane.showMessageDialog(editor.getRootPane(), "The program can't be executed because you didn't save changes correctly", "System Alert", JOptionPane.ERROR_MESSAGE);
                    }

                } else {

                    if (this.editor.display.getText().length() > 0) {
                        this.execute(this.editor.errorsArea);
                    }
                    else{
                        JOptionPane.showMessageDialog(editor.getRootPane(), "You have not written something", "System Alert", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                if (this.executeState == true) {
                    this.showTree(this.model.getTree(), this.model.getParser());

                } else {
                    JOptionPane.showMessageDialog(editor.getRootPane(), "You have not run any program or an error ocurred", "System Alert", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch (Exception e){

            System.out.println("ERROR: " + e);
            System.out.println(e.getStackTrace());

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            executionAreaKeyPressed(e);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
