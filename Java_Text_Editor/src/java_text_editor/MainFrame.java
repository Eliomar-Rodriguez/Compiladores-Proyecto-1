package java_text_editor;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends javax.swing.JFrame {

    File currentEditingFile = null;
    int fontSize = 14;

    public MainFrame() {
        initComponents();

        //Filter Files to display
        //Set JFileChooser to accept only text files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileOpener.setFileFilter(filter);
        
        //Launch the application on the middle of Screen
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e); //To change body of generated methods, choose Tools | Templates.
                int ans = JOptionPane.showConfirmDialog(rootPane, "Save Changes ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (ans == JOptionPane.YES_OPTION) {
                    saveChanges();
                }
            }

        });
        try {
            this.setIconImage(ImageIO.read(MainFrame.class.getResource("/java_text_editor/genuine_coder_logo.png")));
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        setListener();
        
        
    }
    
    public MainFrame(File file) {
        initComponents();
        this.setLocationRelativeTo(null);

        currentEditingFile = file;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileOpener.setFileFilter(filter);
        readTheParamFile(file);
    }

    public void readTheParamFile(File file) {
        try {
            Scanner scn = new Scanner(file);
            String buffer = "";
            while (scn.hasNext()) {
                buffer += scn.nextLine() + "\n";
            }
            display.setText(buffer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setListener(){
        this.display.addCaretListener(new CaretListener(){
            
            public void caretUpdate(CaretEvent e) {
                JTextArea editArea = (JTextArea)e.getSource();

                // Lets start with some default values for the line and column.
                int linenum = 1;
                int columnnum = 1;

                // We create a try catch to catch any exceptions. We will simply ignore such an error for our demonstration.
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
                }
                catch(Exception ex) { }

                // Once we know the position of the line and the column, pass it to a helper function for updating the status bar.
                updatePositions(linenum, columnnum);
            }
        });
    }
    
    public void updatePositions(int lineNum, int columnNum){
        this.line.setText("Line: "+String.valueOf(lineNum));
        this.column.setText("Col: "+String.valueOf(columnNum));        
    }
    
    public void saveChanges() {

        if (this.currentEditingFile != null) {
            try {
                PrintWriter printWriter = new PrintWriter(currentEditingFile);
                printWriter.write(display.getText());
                printWriter.close();
//            JOptionPane.showMessageDialog(rootPane, "Saved", "Done", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            createNewFile();
        }
    }
    
    /**
     * 
     * @param toLine linea a la que se debe mover el cursor
     * @param toCol col a la que se desea mover el cursor
     * @param exhibidor 
     * NOTA: este método asume que la columna destino existe en la línea destino
     * Por lo que únicamente se calcula la llegada a la línea destino, y luego se suman
     * la columna destino al contador de caracteres
     */
    public void setCursorPosition(int toLine, int toCol,JTextArea exhibidor){
        char enter= '\n';
        String texto= this.display.getText();
        int lineCount=1;
        int count=0;
        int charCount=0;
        int finalPosition;
        while(lineCount < toLine){
            if(texto.charAt(count)==enter){
                lineCount++;
            }
            charCount++;
            count++;
        }
        exhibidor.setCaretPosition(charCount+toCol);
        
    }

    public void createNewFile() {
        int status = saveDialog.showOpenDialog(rootPane);
        if (status == JFileChooser.APPROVE_OPTION) {
            //We got directory. Now needs file name
            String fileName = JOptionPane.showInputDialog("File Name", "Untitled.txt");
            if (!fileName.contains(".txt")) {
                fileName += ".txt";
            }
            File f = new File(saveDialog.getSelectedFile() + "\\" + fileName);
            if (f.exists()) {
                JOptionPane.showMessageDialog(rootPane, "File Already Exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                try {
                    f.createNewFile();
                    PrintWriter printWriter = new PrintWriter(f);
                    printWriter.write(display.getText());
                    printWriter.close();
                    JOptionPane.showMessageDialog(rootPane, "Saved", "Done", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Error Occured", "Cant Save", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileOpener = new javax.swing.JFileChooser();
        saveDialog = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        openFile = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        increase = new javax.swing.JButton();
        decrease = new javax.swing.JButton();
        execute = new javax.swing.JButton();
        line = new javax.swing.JLabel();
        column = new javax.swing.JLabel();
        editorPanel = new javax.swing.JScrollPane();
        display = new javax.swing.JTextArea();
        executionPanel = new javax.swing.JTabbedPane();
        execution = new javax.swing.JScrollPane();
        executionArea = new javax.swing.JTextArea();
        errors = new javax.swing.JScrollPane();
        errorsArea = new javax.swing.JTextArea();

        saveDialog.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        saveDialog.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Text Editor");

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setRollover(true);

        openFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_text_editor/open_new_file.png"))); // NOI18N
        openFile.setText("Open");
        openFile.setFocusable(false);
        openFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        jToolBar1.add(openFile);

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_text_editor/Save_icon.png"))); // NOI18N
        guardar.setText("Save");
        guardar.setFocusable(false);
        guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jToolBar1.add(guardar);

        increase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_text_editor/increase_font.png"))); // NOI18N
        increase.setText("Increase Font");
        increase.setFocusable(false);
        increase.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        increase.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        increase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseActionPerformed(evt);
            }
        });
        jToolBar1.add(increase);

        decrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_text_editor/decrease_font.png"))); // NOI18N
        decrease.setText("Decrease Font");
        decrease.setFocusable(false);
        decrease.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        decrease.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        decrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseActionPerformed(evt);
            }
        });
        jToolBar1.add(decrease);

        execute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/execute.png"))); // NOI18N
        execute.setText("Execute");
        execute.setFocusable(false);
        execute.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        execute.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        execute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeActionPerformed(evt);
            }
        });
        jToolBar1.add(execute);

        display.setColumns(20);
        display.setRows(5);
        editorPanel.setViewportView(display);

        executionArea.setColumns(20);
        executionArea.setRows(5);
        execution.setViewportView(executionArea);

        executionPanel.addTab("Execute", execution);

        errorsArea.setColumns(20);
        errorsArea.setRows(5);
        errors.setViewportView(errorsArea);

        executionPanel.addTab("Errors", errors);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(363, Short.MAX_VALUE)
                .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(column, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(editorPanel)
            .addComponent(executionPanel)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(line)
                    .addComponent(column))
                .addGap(11, 11, 11)
                .addComponent(executionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileActionPerformed
        //Show File Open dialouge here
        int status = fileOpener.showOpenDialog(rootPane);
        if (status == JFileChooser.APPROVE_OPTION) {
            if (currentEditingFile != null) {
                // A file is opened and is being edited. Open the new file in new window  
                MainFrame newWindow = new MainFrame(fileOpener.getSelectedFile());
                newWindow.setVisible(true);
                newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                newWindow.pack();
                return;
            }
            currentEditingFile = fileOpener.getSelectedFile();
            System.out.println("File chosen. File name = " + fileOpener.getSelectedFile().getName());

            try {
                //Now read the contents of file
                Scanner scn = new Scanner(new FileInputStream(currentEditingFile));
                String buffer = "";
                while (scn.hasNext()) {
                    buffer += scn.nextLine() + "\n";
                }
                display.setText(buffer);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("No file selected");
        }
    }//GEN-LAST:event_openFileActionPerformed


    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        //If we are editing a file opened, then we have to save the contents on the same file, currentEditingFile
        if (currentEditingFile != null) {
            try {
                PrintWriter printWriter = new PrintWriter(currentEditingFile);
                printWriter.write(display.getText());
                printWriter.close();
                JOptionPane.showMessageDialog(rootPane, "Saved to " + currentEditingFile.getName(), "Done", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
           createNewFile();
        }

    }//GEN-LAST:event_guardarActionPerformed

    private void increaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseActionPerformed
        display.setFont(new java.awt.Font("Monospaced", 0, ++fontSize));
        this.display.setCaretPosition(10);
    }//GEN-LAST:event_increaseActionPerformed

    private void decreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseActionPerformed
        display.setFont(new java.awt.Font("Monospaced", 0, --fontSize));
        this.display.moveCaretPosition(2);
        this.setCursorPosition(1,4,display);
    }//GEN-LAST:event_decreaseActionPerformed

    private void executeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeActionPerformed
        JOptionPane.showMessageDialog(rootPane,"Compilación exitosa", "Done", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_executeActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel column;
    private javax.swing.JButton decrease;
    private javax.swing.JTextArea display;
    private javax.swing.JScrollPane editorPanel;
    private javax.swing.JScrollPane errors;
    private javax.swing.JTextArea errorsArea;
    private javax.swing.JButton execute;
    private javax.swing.JScrollPane execution;
    private javax.swing.JTextArea executionArea;
    private javax.swing.JTabbedPane executionPanel;
    private javax.swing.JFileChooser fileOpener;
    private javax.swing.JButton guardar;
    private javax.swing.JButton increase;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel line;
    private javax.swing.JButton openFile;
    private javax.swing.JFileChooser saveDialog;
    // End of variables declaration//GEN-END:variables
}
