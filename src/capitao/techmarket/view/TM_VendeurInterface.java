/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.techmarket.view;
import capitao.outils.FileToStr;
import capitao.outils.importSql;
import java.io.File;
import java.sql.SQLException;
import java.util.StringTokenizer;
import javax.swing.*;


/**
 *
 * @author jonathan.capitao
 */
public class TM_VendeurInterface extends javax.swing.JFrame {
    
    private static TM_VendeurInterface MyWindows = null;
    String[] data = new String [3];
    
    
    public static TM_VendeurInterface getInstance(){
        if (MyWindows == null){
            MyWindows = new TM_VendeurInterface();
        }
        return MyWindows;
    }
    /**
     * Creates new form TM_VendeurInterface
     */
    private TM_VendeurInterface() {
        initComponents();
        initFen();
        this.setLocationRelativeTo(null);
    }
    
    private void initFen(){
        String conf = FileToStr.read(new File("").getAbsolutePath() +"/config.cfg");
        StringTokenizer stLigne = new StringTokenizer(conf,";");
        int i = 0;
        while (stLigne.hasMoreTokens()) {
            StringTokenizer stData = new StringTokenizer(stLigne.nextToken(),":");
            while (stData.hasMoreTokens()){
                 stData.nextToken();
                 data[i] = stData.nextToken();
            }
            i++;
        }
        tfSoc.setText(data[0]);
        tfAddress.setText(data[1]);
        tfCP.setText(data[2]);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbSoc = new java.awt.Label();
        lbAdresse = new java.awt.Label();
        lbCodePost = new java.awt.Label();
        tfSoc = new java.awt.TextField();
        tfAddress = new java.awt.TextField();
        tfCP = new java.awt.TextField();
        mainMenuBarre = new javax.swing.JMenuBar();
        menuAdmin = new javax.swing.JMenu();
        menuImport = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuQuitter = new javax.swing.JMenuItem();
        menuDonnee = new javax.swing.JMenu();
        menuComposant = new javax.swing.JMenuItem();
        menuMarque = new javax.swing.JMenuItem();
        menuSpecification = new javax.swing.JMenuItem();
        menuTypeCompo = new javax.swing.JMenuItem();
        menuStock = new javax.swing.JMenuItem();
        men_help = new javax.swing.JMenu();
        men_help_apropos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TekMarket Manager");
        setResizable(false);

        lbSoc.setText("Societé");

        lbAdresse.setText("Adresse ");

        lbCodePost.setText("Code postale");

        tfSoc.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                tfSocTextValueChanged(evt);
            }
        });

        tfAddress.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                tfAddressTextValueChanged(evt);
            }
        });

        tfCP.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                tfCPTextValueChanged(evt);
            }
        });

        menuAdmin.setText("Administration");

        menuImport.setText("Import");
        menuImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuImportActionPerformed(evt);
            }
        });
        menuAdmin.add(menuImport);
        menuAdmin.add(jSeparator1);

        menuQuitter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK));
        menuQuitter.setText("Fermer");
        menuQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQuitterActionPerformed(evt);
            }
        });
        menuAdmin.add(menuQuitter);

        mainMenuBarre.add(menuAdmin);

        menuDonnee.setText("Gestion des données");
        menuDonnee.setToolTipText("Accéder aux différentes données de l'application pour les modifier");

        menuComposant.setText("Gestion des composants");
        menuComposant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuComposantActionPerformed(evt);
            }
        });
        menuDonnee.add(menuComposant);

        menuMarque.setText("Gestion des marques");
        menuMarque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMarqueActionPerformed(evt);
            }
        });
        menuDonnee.add(menuMarque);

        menuSpecification.setText("Gestion des spécifications");
        menuSpecification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSpecificationActionPerformed(evt);
            }
        });
        menuDonnee.add(menuSpecification);

        menuTypeCompo.setText("Gestion des catégories de composants");
        menuTypeCompo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTypeCompoActionPerformed(evt);
            }
        });
        menuDonnee.add(menuTypeCompo);

        menuStock.setText("Gestion du stock");
        menuStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStockActionPerformed(evt);
            }
        });
        menuDonnee.add(menuStock);

        mainMenuBarre.add(menuDonnee);

        men_help.setText("Aide");

        men_help_apropos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        men_help_apropos.setText("À propos");
        men_help_apropos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                men_help_aproposActionPerformed(evt);
            }
        });
        men_help.add(men_help_apropos);

        mainMenuBarre.add(men_help);

        setJMenuBar(mainMenuBarre);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbCodePost, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbSoc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(tfSoc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbSoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbCodePost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuImportActionPerformed
        //Create a file chooser
        JFileChooser fc_import =new JFileChooser("./data");
        int result= fc_import.showOpenDialog(null);
        if(result==JFileChooser.CANCEL_OPTION)
            return;
        String BD=fc_import.getSelectedFile().getAbsolutePath();
        File fileBD = fc_import.getSelectedFile();
        try {
            importSql.resetDatabase(fileBD);
        } catch (SQLException ex) {
            System.err.println("TM_VendeurInterface.menuImportActionPerformed: " + ex.getMessage());
        } catch (Exception ex){
            System.err.println("TM_VendeurInterface.menuImportActionPerformed: " + ex.getMessage());
        }
    }//GEN-LAST:event_menuImportActionPerformed

    private void menuQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQuitterActionPerformed
        this.dispose();
    }//GEN-LAST:event_menuQuitterActionPerformed

    private void menuComposantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuComposantActionPerformed
        TM_RD_Composant compoFrame = TM_RD_Composant.getInstance();
        compoFrame.setVisible(true);
    }//GEN-LAST:event_menuComposantActionPerformed

    private void menuMarqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMarqueActionPerformed
        TM_CRUD_Marque marqueFrame = TM_CRUD_Marque.getInstance();
        marqueFrame.setVisible(true);
    }//GEN-LAST:event_menuMarqueActionPerformed

    private void menuSpecificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSpecificationActionPerformed
        TM_CRUD_Specification specFrame = TM_CRUD_Specification.getInstance();
        specFrame.setVisible(true);
    }//GEN-LAST:event_menuSpecificationActionPerformed

    private void menuTypeCompoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTypeCompoActionPerformed
        TM_CRUD_CompoType cpTypeFrame = TM_CRUD_CompoType.getInstance();
        cpTypeFrame.setVisible(true);
    }//GEN-LAST:event_menuTypeCompoActionPerformed

    private void menuStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStockActionPerformed
        TM_Stock cpStock = TM_Stock.getInstance();
        cpStock.setVisible(true);
    }//GEN-LAST:event_menuStockActionPerformed

    private void men_help_aproposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_men_help_aproposActionPerformed
        TM_APropos.getInstance().setVisible(true);
    }//GEN-LAST:event_men_help_aproposActionPerformed

    private void tfSocTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_tfSocTextValueChanged
        maj();
    }//GEN-LAST:event_tfSocTextValueChanged

    private void tfAddressTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_tfAddressTextValueChanged
        maj();
    }//GEN-LAST:event_tfAddressTextValueChanged

    private void tfCPTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_tfCPTextValueChanged
        maj();
    }//GEN-LAST:event_tfCPTextValueChanged

    public void maj(){
        int lign = 1;
        data[0] = tfSoc.getText();
        data[1] = tfAddress.getText();
        data[2] = tfCP.getText();
        String[] configContent = new String[lign];
        configContent[0] = "Societe:"+data[0]+";SocAdr:"
                +data[1]+";CodePost:"+data[2];
        FileToStr.write(new File("").getAbsolutePath() +"/config.cfg", configContent);
    }
    
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
            java.util.logging.Logger.getLogger(TM_VendeurInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TM_VendeurInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TM_VendeurInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TM_VendeurInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TM_VendeurInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private java.awt.Label lbAdresse;
    private java.awt.Label lbCodePost;
    private java.awt.Label lbSoc;
    private javax.swing.JMenuBar mainMenuBarre;
    private javax.swing.JMenu men_help;
    private javax.swing.JMenuItem men_help_apropos;
    private javax.swing.JMenu menuAdmin;
    private javax.swing.JMenuItem menuComposant;
    private javax.swing.JMenu menuDonnee;
    private javax.swing.JMenuItem menuImport;
    private javax.swing.JMenuItem menuMarque;
    private javax.swing.JMenuItem menuQuitter;
    private javax.swing.JMenuItem menuSpecification;
    private javax.swing.JMenuItem menuStock;
    private javax.swing.JMenuItem menuTypeCompo;
    private java.awt.TextField tfAddress;
    private java.awt.TextField tfCP;
    private java.awt.TextField tfSoc;
    // End of variables declaration//GEN-END:variables
}
