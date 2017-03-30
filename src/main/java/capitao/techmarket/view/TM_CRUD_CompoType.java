package capitao.techmarket.view;

import capitao.base.ComposantTypeDao;
import capitao.techmarket.domaine.TM_ComposantType;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jonathan.capitao
 */
public class TM_CRUD_CompoType extends javax.swing.JFrame {

    ArrayList<TM_ComposantType> CompoType ;
    String mod = "Add";
    TM_ComposantType ct;
    
    private static TM_CRUD_CompoType MyWindows = null;
    
    public static TM_CRUD_CompoType getInstance(){
        if (MyWindows == null){
            MyWindows = new TM_CRUD_CompoType();
        }
        return MyWindows;
    }    
    
    // Initialise la liste des catégories de composants
    public void initList(){
        CompoType = ComposantTypeDao.getListeCatCompo() ;
        for (TM_ComposantType ct : CompoType) 
            listCompoType.add(ct.toString());
    }
    
    /**
     * Creates new form TM_CRUD_Marque
     */
    private TM_CRUD_CompoType() {
        initComponents();
        this.setLocationRelativeTo(null);
        initList();
    }

    // Détermine le type d'action a effectué et vérifie si les champs son remplie
    // puis appel le DAO pour l'ajout ou la modification des données
    public void valider(){
        if (mod.equals("Add")){
            if (!tfName.getText().equals("")){
                ct = new TM_ComposantType(tfName.getText());
                ComposantTypeDao.insert(ct);
            }else{
                JOptionPane.showMessageDialog(this
                        , "Veuillez remplir toutes les données de la page"
                        , "Erreur : Données manquantes"
                        , JOptionPane.ERROR_MESSAGE
                );
            }
        }
        if (mod.equals("Mod")){
            if (!tfName.getText().equals("")){
                ct.setNom(tfName.getText());
                ComposantTypeDao.update(ct);
            }else{
                JOptionPane.showMessageDialog(this
                        , "Veuillez remplir toutes les données de la page"
                        , "Erreur : Données manquantes"
                        , JOptionPane.ERROR_MESSAGE
                );
            }
        }
        listCompoType.removeAll();
        initList();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panAddMod = new javax.swing.JPanel();
        btValider = new javax.swing.JButton();
        tfName = new java.awt.TextField();
        lbName = new java.awt.Label();
        listCompoType = new java.awt.List();
        btAdd = new javax.swing.JButton();
        btMod = new javax.swing.JButton();
        lbListCompoType = new java.awt.Label();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMain = new javax.swing.JMenu();
        menuFermer = new javax.swing.JMenuItem();
        men_help = new javax.swing.JMenu();
        men_help_apropos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion des cat�gories de composants");
        setMinimumSize(null);
        setName("frmMarque"); // NOI18N
        setPreferredSize(new java.awt.Dimension(290, 353));
        setResizable(false);
        setSize(new java.awt.Dimension(290, 343));
        setType(java.awt.Window.Type.POPUP);

        btValider.setText("Valider");
        btValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btValiderActionPerformed(evt);
            }
        });

        lbName.setText("Libell� du type de composant");

        javax.swing.GroupLayout panAddModLayout = new javax.swing.GroupLayout(panAddMod);
        panAddMod.setLayout(panAddModLayout);
        panAddModLayout.setHorizontalGroup(
            panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAddModLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panAddModLayout.createSequentialGroup()
                        .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btValider)
                            .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panAddModLayout.setVerticalGroup(
            panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btValider)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tfName.getAccessibleContext().setAccessibleName("");
        tfName.getAccessibleContext().setAccessibleDescription("");
        lbName.getAccessibleContext().setAccessibleName("");

        listCompoType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listCompoTypeItemStateChanged(evt);
            }
        });

        btAdd.setText("Ajouter");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btMod.setText("Modifier");
        btMod.setEnabled(false);
        btMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModActionPerformed(evt);
            }
        });

        lbListCompoType.setText("Types de composants");

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

        men_help_apropos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        men_help_apropos.setText("� propos");
        men_help_apropos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                men_help_aproposActionPerformed(evt);
            }
        });
        men_help.add(men_help_apropos);

        jMenuBar1.add(men_help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btMod, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbListCompoType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listCompoType, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panAddMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panAddMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbListCompoType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(listCompoType, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdd)
                    .addComponent(btMod))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFermerActionPerformed
        this.dispose();
    }//GEN-LAST:event_menuFermerActionPerformed

    private void btModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModActionPerformed
        resize(true);
        mod = "Mod";
        ct = CompoType.get(listCompoType.getSelectedIndex());
        tfName.setText(ct.toString());
    }//GEN-LAST:event_btModActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        resize(true);
        mod = "Add";
        tfName.setText("");
    }//GEN-LAST:event_btAddActionPerformed

    private void btValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btValiderActionPerformed
        resize(false);
        valider();
    }//GEN-LAST:event_btValiderActionPerformed

    private void men_help_aproposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_men_help_aproposActionPerformed
        TM_APropos.getInstance().setVisible(true);
    }//GEN-LAST:event_men_help_aproposActionPerformed

    private void listCompoTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listCompoTypeItemStateChanged
        btMod.setEnabled(true);
    }//GEN-LAST:event_listCompoTypeItemStateChanged

    // Adapte la taille de la fenètre pour cacher les champs de modification en mode liste
    public void resize(boolean minmax){
        int width = 0;
        if (minmax){
            width = 512;
        }else{
            width = 290;
        }    
        Dimension dt = new Dimension(width, 353);
        this.setPreferredSize(dt);
        this.pack();
        this.validate();
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
            java.util.logging.Logger.getLogger(TM_CRUD_CompoType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TM_CRUD_CompoType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TM_CRUD_CompoType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TM_CRUD_CompoType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TM_CRUD_CompoType().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btMod;
    private javax.swing.JButton btValider;
    private javax.swing.JMenuBar jMenuBar1;
    private java.awt.Label lbListCompoType;
    private java.awt.Label lbName;
    private java.awt.List listCompoType;
    private javax.swing.JMenu men_help;
    private javax.swing.JMenuItem men_help_apropos;
    private javax.swing.JMenuItem menuFermer;
    private javax.swing.JMenu menuMain;
    private javax.swing.JPanel panAddMod;
    private java.awt.TextField tfName;
    // End of variables declaration//GEN-END:variables
}
