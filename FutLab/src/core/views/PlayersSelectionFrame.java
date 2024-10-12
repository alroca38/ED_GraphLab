package core.views;

import core.controllers.PlayersFileController;
import core.controllers.utils.Response;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PlayersSelectionFrame extends javax.swing.JFrame {

    public PlayersSelectionFrame() {
        BackgroundPanel background = new BackgroundPanel("/resources/images/DarkBG.png");
        setContentPane(background);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirmationButton = new javax.swing.JButton();
        playersSelectionButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        playersList = new javax.swing.JList<>();
        adjacencySelectionButton = new javax.swing.JButton();

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

        playersList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        playersList.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        playersList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(playersList);

        adjacencySelectionButton.setText("Ingresar adyacencias");
        adjacencySelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjacencySelectionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adjacencySelectionButton)
                            .addComponent(playersSelectionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(confirmationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(backButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(confirmationButton)
                        .addGap(18, 18, 18)
                        .addComponent(playersSelectionButton)
                        .addGap(18, 18, 18)
                        .addComponent(adjacencySelectionButton)
                        .addGap(54, 54, 54)
                        .addComponent(backButton)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))))
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

    private void adjacencySelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjacencySelectionButtonActionPerformed
        
    }//GEN-LAST:event_adjacencySelectionButtonActionPerformed

    private void confirmationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmationButtonActionPerformed
        
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> playersList;
    private javax.swing.JButton playersSelectionButton;
    // End of variables declaration//GEN-END:variables
}
