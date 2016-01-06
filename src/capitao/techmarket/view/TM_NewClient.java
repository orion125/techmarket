/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.techmarket.view;

import capitao.base.ClientDao;
import capitao.techmarket.domaine.TM_Client;

/**
 *
 * @author jonathan.capitao
 */
public class TM_NewClient extends javax.swing.JFrame {
   
    public static TM_NewClient MyWindows = null;   
    public static TM_ClientSelect MyWindowsparent = null;
    TM_Client newCli = null;

    public static TM_NewClient getInstance(TM_ClientSelect parent){
        if (MyWindows == null){
            MyWindows = new TM_NewClient(parent);
        }
        return MyWindows;
    }
    
    
    
    /**
     * Creates new form TM_NewClient
     */
    private TM_NewClient(TM_ClientSelect parent) {
        initComponents();
        this.MyWindowsparent = parent;
        this.setLocationRelativeTo(null);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jbtValidez = new javax.swing.JButton();
        tfNom = new java.awt.TextField();
        tfPrenom = new java.awt.TextField();
        tfAdresse = new java.awt.TextField();
        tfTel = new java.awt.TextField();
        tfMail = new java.awt.TextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMain = new javax.swing.JMenu();
        menuFermer = new javax.swing.JMenuItem();
        men_help = new javax.swing.JMenu();
        men_help_apropos1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Créer un nouveau Client");

        jLabel1.setText("Nom");

        jLabel2.setText("Prénom");

        jLabel3.setText("Téléphone");

        jLabel4.setText("Adresse");

        jLabel5.setText("E-mail");

        jbtValidez.setText("Enregistrer");
        jbtValidez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtValidezActionPerformed(evt);
            }
        });

        tfTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfTelKeyPressed(evt);
            }
        });

        menuMain.setText("Fichier");

        menuFermer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK));
        menuFermer.setText("Fermer");
        menuFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFermerActionPerformed(evt);
            }
        });
        menuMain.add(menuFermer);

        jMenuBar1.add(menuMain);

        men_help.setText("Aide");

        men_help_apropos1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        men_help_apropos1.setText("À propos");
        men_help_apropos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                men_help_apropos1ActionPerformed(evt);
            }
        });
        men_help.add(men_help_apropos1);

        jMenuBar1.add(men_help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfMail, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(tfTel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfAdresse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfPrenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jbtValidez))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfAdresse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfTel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtValidez)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtValidezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtValidezActionPerformed
        String[] dataCli = new String[5];
        if (!(tfNom.getText().equals("")) && !(tfPrenom.getText().equals(""))){
            dataCli[0] = tfPrenom.getText();
            dataCli[1] = tfNom.getText(); 
            dataCli[2] =  (tfAdresse.getText().equals("")) ? null : tfAdresse.getText();
            dataCli[3] =  (tfTel.getText().equals("")) ? null : tfTel.getText() ;
            dataCli[4] =  (tfMail.getText().equals("")) ? null : tfMail.getText();
            
            newCli = new TM_Client(
                    -1, // ce -1 sera remplacé par la clé du prochain client (retrouvée dans la base de données)
                    dataCli[0], 
                    dataCli[1], 
                    dataCli[2], 
                    dataCli[3], 
                    dataCli[4]
            );
            ClientDao.insert(newCli);
            MyWindowsparent.setClient(newCli);
            this.dispose();
        }
    }//GEN-LAST:event_jbtValidezActionPerformed

    private void men_help_apropos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_men_help_apropos1ActionPerformed
        TM_APropos.getInstance().setVisible(true);
    }//GEN-LAST:event_men_help_apropos1ActionPerformed

    private void menuFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFermerActionPerformed
        this.dispose();
    }//GEN-LAST:event_menuFermerActionPerformed

    private void tfTelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTelKeyPressed
        capitao.outils.filterTools.filterTel(evt);
    }//GEN-LAST:event_tfTelKeyPressed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TM_NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TM_NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TM_NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TM_NewClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TM_NewClient(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton jbtValidez;
    private javax.swing.JMenu men_help;
    private javax.swing.JMenuItem men_help_apropos1;
    private javax.swing.JMenuItem menuFermer;
    private javax.swing.JMenu menuMain;
    private java.awt.TextField tfAdresse;
    private java.awt.TextField tfMail;
    private java.awt.TextField tfNom;
    private java.awt.TextField tfPrenom;
    private java.awt.TextField tfTel;
    // End of variables declaration//GEN-END:variables
}
