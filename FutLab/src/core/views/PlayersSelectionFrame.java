/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package core.views;

import core.controllers.PlayersFileController;
import core.controllers.utils.Response;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author FAMILIA RODRIGUEZ
 */
public class PlayersSelectionFrame extends javax.swing.JFrame {

    JFrame menuFrame;

    public PlayersSelectionFrame(JFrame menuFrame) {
        this.menuFrame = menuFrame;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirmationButton = new javax.swing.JButton();
        playersSelectionButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        playersList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        confirmationButton.setText("Confirmar");

        playersSelectionButton.setText("Seleccionar archivo de jugadores");
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

        playersList.setBorder(new javax.swing.border.MatteBorder(null));
        playersList.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane2.setViewportView(playersList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(95, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(confirmationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(playersSelectionButton)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(playersSelectionButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(confirmationButton)
                        .addGap(18, 18, 18)
                        .addComponent(backButton)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playersSelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playersSelectionButtonActionPerformed
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(frame);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File players = fileChooser.getSelectedFile();
            Response response = PlayersFileController.readPlayersFile(players, playersList);
            if (response.getStatus() >= 500) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
            } else if (response.getStatus() >= 400) {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }//GEN-LAST:event_playersSelectionButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    @Override
    public void dispose() {
        super.dispose();
        menuFrame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton confirmationButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> playersList;
    private javax.swing.JButton playersSelectionButton;
    // End of variables declaration//GEN-END:variables
}
