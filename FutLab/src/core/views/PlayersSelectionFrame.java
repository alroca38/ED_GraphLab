package core.views;

import core.controllers.MatrixFileController;
import core.controllers.PlayersFileController;
import core.controllers.utils.Response;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PlayersSelectionFrame extends javax.swing.JFrame {

    DefaultTableModel tableModel;

    public PlayersSelectionFrame() {
        BackgroundPanel background = new BackgroundPanel("/resources/images/DarkBG.png");
        setContentPane(background);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        playersTable.setRowHeight(30);
        tableModel = (DefaultTableModel) playersTable.getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);
        tableModel.addColumn("POSICION");
        tableModel.addColumn("NOMBRE");
        tableModel.addColumn("VELOCIDAD");
        tableModel.addColumn("POSESION");
        tableModel.addColumn("REMATE");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        confirmationButton = new javax.swing.JButton();
        playersSelectionButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        adjacencySelectionButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        playersTable = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        confirmationButton.setText("Empezar");
        confirmationButton.setEnabled(false);
        confirmationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmationButtonActionPerformed(evt);
            }
        });

        playersSelectionButton.setText("Ingresar jugadores");
        playersSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playersSelectionButtonActionPerformed(evt);
            }
        });

        backButton.setText("Volver al menú");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        adjacencySelectionButton.setText("Ingresar adyacencias");
        adjacencySelectionButton.setEnabled(false);
        adjacencySelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjacencySelectionButtonActionPerformed(evt);
            }
        });

        playersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        playersTable.setShowGrid(true);
        playersTable.getTableHeader().setResizingAllowed(false);
        playersTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(playersTable);
        playersTable.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(playersSelectionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(confirmationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(adjacencySelectionButton)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmationButton)
                    .addComponent(playersSelectionButton)
                    .addComponent(adjacencySelectionButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backButton)
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playersSelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playersSelectionButtonActionPerformed
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(frame);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File players = fileChooser.getSelectedFile();
            Response response = PlayersFileController.readPlayersFile(players, playersTable, tableModel);
            if (response.getStatus() >= 500) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
            } else if (response.getStatus() >= 400) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
                playersSelectionButton.setEnabled(false);
                adjacencySelectionButton.setEnabled(true);
            }
        }
    }//GEN-LAST:event_playersSelectionButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void adjacencySelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjacencySelectionButtonActionPerformed
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(frame);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File matrix = fileChooser.getSelectedFile();
            Response response = MatrixFileController.readMatrixFile(matrix);
            if (response.getStatus() >= 500) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
            } else if (response.getStatus() >= 400) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
                adjacencySelectionButton.setEnabled(false);
                confirmationButton.setEnabled(true);
            }
        }
    }//GEN-LAST:event_adjacencySelectionButtonActionPerformed

    private void confirmationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmationButtonActionPerformed
        dispose();
        MenuFrame.getInstance().setVisible(false);
        new FieldView().setVisible(true);
    }//GEN-LAST:event_confirmationButtonActionPerformed

    @Override
    public void dispose() {
        super.dispose();
        MenuFrame.getInstance().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adjacencySelectionButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton confirmationButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton playersSelectionButton;
    private javax.swing.JTable playersTable;
    // End of variables declaration//GEN-END:variables
}
