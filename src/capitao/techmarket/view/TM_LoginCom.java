/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.techmarket.view;

import capitao.base.ManagerDao;
import capitao.techmarket.domaine.TM_Manager;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author jonathan.capitao
 */
public class TM_LoginCom extends javax.swing.JFrame {

    public static TM_LoginCom MyWindows = null;
    public static java.awt.List listToUnlock = null;
    /**
     * Creates new form TM_LoginOrClient    /**
     * Creates new form TM_LoginOrClient
     */
    
    public static TM_LoginCom getInstance(java.awt.List listToUnlock) {
        if (MyWindows == null){
            MyWindows = new TM_LoginCom();
        }
        MyWindows.listToUnlock = listToUnlock;
        return MyWindows;    
    }   
    
    public static void choix(String args) {
        if (args.equals("Client")){
            TM_ClientInterface.getInstance().setVisible(true);
        }else if (args.equals("Manager")){
            TM_VendeurInterface.getInstance().setVisible(true);
        }
        MyWindows.dispose();
    }     
    
    /**
     * Creates new form TM_Login
     */
    public TM_LoginCom() {
        initComponents();
        this.setLocationRelativeTo(null);
        loginActivation ();
    }

    public void loginActivation (){
        jbtLogin.setEnabled(!(jtf_Username.getText().equals(""))
                          && (jpw_Password.getPassword().length > 0));
    }
    
    public boolean infoCorrect(){
        TM_Manager temp = new TM_Manager(jtf_Username.getText(),
                                new String(jpw_Password.getPassword()));
        TM_Manager mainAdm = new TM_Manager("Admin","1234"); // admin de test
        ArrayList<TM_Manager> manList = ManagerDao.getListeManager();
        //manList = new ArrayList<>();
        manList.add(mainAdm);
        return manList.contains(temp);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtf_Username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jpw_Password = new javax.swing.JPasswordField();
        jbtLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TekMarket : Connexion");
        setAlwaysOnTop(true);
        setResizable(false);

        jtf_Username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtf_UsernameKeyReleased(evt);
            }
        });

        jLabel1.setText("Nom d'utilisateur");

        jLabel2.setText("Mot de passe");

        jpw_Password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jpw_PasswordKeyReleased(evt);
            }
        });

        jbtLogin.setText("Connexion");
        jbtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpw_Password, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(jtf_Username))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jpw_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jpw_PasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpw_PasswordKeyReleased
        loginActivation ();
    }//GEN-LAST:event_jpw_PasswordKeyReleased

    private void jtf_UsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_UsernameKeyReleased
        loginActivation ();
    }//GEN-LAST:event_jtf_UsernameKeyReleased

    private void jbtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLoginActionPerformed
        if (infoCorrect()){
            listToUnlock.setEnabled(true);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe erroné"
                    , "Erreur : Indentifiants erronés", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtLoginActionPerformed

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
            java.util.logging.Logger.getLogger(TM_LoginCom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TM_LoginCom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TM_LoginCom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TM_LoginCom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TM_LoginCom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtLogin;
    private javax.swing.JPasswordField jpw_Password;
    private javax.swing.JTextField jtf_Username;
    // End of variables declaration//GEN-END:variables
}
