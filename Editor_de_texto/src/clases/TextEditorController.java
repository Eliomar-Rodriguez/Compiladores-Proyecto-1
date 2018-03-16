/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.prism.paint.Color;
import editor_de_texto.EditorFrame;

import static java.awt.Color.RED;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import generated.MonkeyParser;
import generated.MonkeyScanner;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author Josua
 */
public class TextEditorController extends WindowAdapter implements ActionListener, CaretListener {

    private int currentLine;
    private int currentCol;
    private File currentFile;
    private EditorFrame editor;
    private int fontSize;
    private TextEditorModel model;
    private boolean executeState;


    public TextEditorController(EditorFrame window) {
        this.editor = window;
        this.currentCol = 1;
        this.currentLine = 1;
        this.currentFile = null;
        this.fontSize = 14;
        model = new TextEditorModel();
        this.executeState=false;

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


    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void showException(JTextArea errorArea,Exception exception){
        errorArea.setForeground(RED);
        StringWriter stackTraceWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTraceWriter));
        errorArea.setText(stackTraceWriter.toString());
        JOptionPane.showMessageDialog(editor.getRootPane(),"La compilación fue abortada debido a un que ocurrió un error" +
                        " en el proceso","Alerta de sistema", JOptionPane.ERROR_MESSAGE);
    }

    public void execute(JTextArea errorArea){
        try {
            this.model.AnalizeAndExecute(this.currentFile.getPath());
            JOptionPane.showMessageDialog(this.editor.getRootPane(), "Compilación exitosa", "Done", JOptionPane.INFORMATION_MESSAGE);
            this.executeState=true;
        }
        catch (IOException exception){
            this.showException(errorArea,exception);
            this.executeState=false;
        }
        catch (ParseCancellationException exception){
            this.showException(errorArea,exception);
            this.executeState=false;
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
                JOptionPane.showMessageDialog(editor.getRootPane(), "Saved", "Done", JOptionPane.INFORMATION_MESSAGE);
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
            this.readFile(this.currentFile);

        } else {
            System.out.println("No file selected");
        }
    }

    public void showTree(ParseTree tree, Parser parser){
        java.util.concurrent.Future <JFrame> treeGUI = org.antlr.v4.gui.Trees.inspect(tree,parser);
        try {
            treeGUI.get().setVisible(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
            // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
            // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
            // what position that line starts on.
            int caretpos = editArea.getCaretPosition();

            linenum = editArea.getLineOfOffset(caretpos);

            // We subtract the offset of where our line starts from the overall caret position.
            // So lets say that we are on line 5 and that line starts at caret position 100, if our caret position is currently 106
            // we know that we must be on column 6 of line 5.
            columnnum = caretpos - editArea.getLineStartOffset(linenum);

            // We have to add one here because line numbers start at 0 for getLineOfOffset and we want it to start at 1 for display.
            linenum += 1;
        } catch (Exception ex) {
            System.out.println("An error doesn't allow to finish the update process");
        }

        // Once we know the position of the line and the column, pass it to a helper function for updating the status bar.
        updateCaretPositions(linenum, columnnum, editor.line, editor.column);
    }

    @Override
    public void windowClosing(WindowEvent event) {
        this.askForSaving();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

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
        else if (event.getActionCommand().equals(this.editor.execute.getActionCommand())){
            if ((this.currentFile == null) && (this.editor.display.getText().length() > 0)) {
                this.saveFileChanges();
            } else {
                if (this.editor.display.getText().length() > 0) {
                    this.editor.errorsArea.setText("");
                    this.editor.executionArea.setText("");
                    this.executeState=false;
                    this.execute(this.editor.errorsArea);
                }
            }
        }
        // view tree
        else {
                if (this.executeState==true){
                    this.showTree(this.model.getTree(),this.model.getParser());
                }
                else{
                    JOptionPane.showMessageDialog(editor.getRootPane(), "Error Occured", "you have not run any program", JOptionPane.ERROR_MESSAGE);
                }

        }
    }

}
