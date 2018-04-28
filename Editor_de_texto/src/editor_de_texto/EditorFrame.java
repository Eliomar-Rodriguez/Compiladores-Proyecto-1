package editor_de_texto;

import clases.TextEditorController;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EditorFrame extends javax.swing.JFrame {

    public EditorFrame() {
        initComponents();

        //Filter Files to display
        //Set JFileChooser to accept only text files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileOpener.setFileFilter(filter);
        this.setTitle("Monkey Text Editor");
        //Launch the application on the middle of Screen
        this.setLocationRelativeTo(null);
        
        try {
            this.setIconImage(ImageIO.read(EditorFrame.class.getResource("/Images/genuine_coder_logo.png")));
        } catch (IOException ex) {
            Logger.getLogger(EditorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        assignListeners(new TextEditorController(this));
        
    }
    
    public void assignListeners(TextEditorController controller){
        this.openFile.addActionListener((ActionListener) controller);
        this.newFile.addActionListener((ActionListener) controller);
        this.saveButton.addActionListener((ActionListener) controller);
        this.increase.addActionListener((ActionListener) controller);
        this.decrease.addActionListener((ActionListener) controller);
        this.execute.addActionListener((ActionListener) controller);
        this.display.addCaretListener((CaretListener)controller);
        this.viewTree.addActionListener((ActionListener) controller);
        this.addWindowListener((WindowAdapter)controller);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileOpener = new javax.swing.JFileChooser();
        saveDialog = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        openFile = new javax.swing.JButton();
        newFile = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        increase = new javax.swing.JButton();
        decrease = new javax.swing.JButton();
        execute = new javax.swing.JButton();
        viewTree = new javax.swing.JButton();
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
        jToolBar1.setEnabled(false);

        openFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Open_file.png"))); // NOI18N
        openFile.setText("Open");
        openFile.setFocusable(false);
        openFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(openFile);

        newFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/open_new_file.png"))); // NOI18N
        newFile.setText("New File");
        newFile.setFocusable(false);
        newFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(newFile);

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Save_icon.png"))); // NOI18N
        saveButton.setText("Save");
        saveButton.setFocusable(false);
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(saveButton);

        increase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/increase_font.png"))); // NOI18N
        increase.setText("Increase Font");
        increase.setFocusable(false);
        increase.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        increase.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(increase);

        decrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/decrease_font.png"))); // NOI18N
        decrease.setText("Decrease Font");
        decrease.setFocusable(false);
        decrease.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        decrease.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(decrease);

        execute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/execute.png"))); // NOI18N
        execute.setText("Execute");
        execute.setFocusable(false);
        execute.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        execute.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(execute);

        viewTree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tree.png"))); // NOI18N
        viewTree.setText("Tree view");
        viewTree.setFocusable(false);
        viewTree.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        viewTree.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(viewTree);

        display.setColumns(20);
        display.setRows(5);
        editorPanel.setViewportView(display);

        //executionPanel.setNextFocusableComponent(errorsArea);

        executionArea.setColumns(20);
        executionArea.setRows(5);
        execution.setViewportView(executionArea);

        executionPanel.addTab("Execute", execution);
        execution.setViewportView(errorsArea);

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
            java.util.logging.Logger.getLogger(EditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditorFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel column;
    public javax.swing.JButton decrease;
    public javax.swing.JTextArea display;
    private javax.swing.JScrollPane editorPanel;
    private javax.swing.JScrollPane errors;
    public javax.swing.JTextArea errorsArea;
    public javax.swing.JButton execute;
    private javax.swing.JScrollPane execution;
    public javax.swing.JTextArea executionArea;
    public javax.swing.JTabbedPane executionPanel;
    public javax.swing.JFileChooser fileOpener;
    public javax.swing.JButton increase;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    public javax.swing.JLabel line;
    public javax.swing.JButton newFile;
    public javax.swing.JButton openFile;
    public javax.swing.JButton saveButton;
    public javax.swing.JFileChooser saveDialog;
    public javax.swing.JButton viewTree;
    // End of variables declaration//GEN-END:variables
}
