package core.views;

import javax.swing.JFrame;

public class FieldView extends javax.swing.JFrame {

    public FieldView() {
        BackgroundPanel background = new BackgroundPanel("/resources/images/SoccerField.jpg");
        setContentPane(background);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void dispose() {
        super.dispose();
        MenuFrame.getInstance().setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        possesionButton = new javax.swing.JButton();
        longShootingButton = new javax.swing.JButton();
        directButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        possesionButton.setText("Posesion");
        possesionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                possesionButtonActionPerformed(evt);
            }
        });

        longShootingButton.setText("Tiros Lejanos");
        longShootingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                longShootingButtonActionPerformed(evt);
            }
        });

        directButton.setText("Directo");
        directButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Salir");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(possesionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(directButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(longShootingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(443, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(directButton)
                    .addComponent(possesionButton)
                    .addComponent(longShootingButton)
                    .addComponent(exitButton))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void possesionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_possesionButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_possesionButtonActionPerformed

    private void longShootingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_longShootingButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_longShootingButtonActionPerformed

    private void directButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_directButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_directButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton directButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton longShootingButton;
    private javax.swing.JButton possesionButton;
    // End of variables declaration//GEN-END:variables
}
