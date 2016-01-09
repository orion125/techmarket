/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.techmarket.view;
import capitao.base.ComposantDao;
import capitao.base.ComposantTypeDao;
import capitao.base.MarqueDao;
import capitao.base.SpecificationDao;
import capitao.techmarket.domaine.TM_Composant;
import capitao.techmarket.domaine.TM_Marque;
import capitao.techmarket.domaine.TM_ComposantType;
import capitao.techmarket.domaine.TM_LigneCommande;
import capitao.techmarket.domaine.TM_Specification;
import capitao.techmarket.domaine.TM_SpecificationAsValue;
import capitao.outils.filterTools;
import java.awt.CheckboxGroup;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.Action;
import javax.swing.JMenuItem;
import jdk.nashorn.internal.objects.NativeArray;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import sun.java2d.SurfaceData;

/**
 *
 * @author jonathan.capitao
 */
public class TM_ClientInterface extends javax.swing.JFrame {

    private static TM_ClientInterface MyWindows = null;
    public ArrayList<TM_ComposantType> CompoType = new ArrayList();
    public ArrayList<TM_Marque> Marques = new ArrayList();
    public ArrayList<TM_Specification> Specs = new ArrayList();
    public ArrayList<TM_SpecificationAsValue> SpecV = new ArrayList();
    public ArrayList<TM_Composant> ComposantsTrouvee = new ArrayList();
    public ArrayList<TM_LigneCommande> alc = new ArrayList<>();
    ArrayList<TM_Composant> allComp = ComposantDao.getListeComp();
    TM_ComposantType cp;
    private boolean onTimeInitArray = true;
    /**
     * Creates new form ClientInterface
     */
    
    public static TM_ClientInterface getInstance(){
        if (MyWindows == null){
            MyWindows = new TM_ClientInterface();
        }
        return MyWindows;
    }
    
    
    
    private TM_ClientInterface() {
        initComponents();
        this.setLocationRelativeTo(null);
        initCompoTypeList ();
    }
    
    private void initCompoTypeList (){     
        CompoType = ComposantTypeDao.getListeCatCompo() ; 
        JMenuItem menElemActu = null;
        for (int i = 0; i < CompoType.size(); i++) {
            menElemActu = men_compType.add(((TM_ComposantType)CompoType.get(i)).getNom());
            menElemActu.addActionListener(new ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt){
                    menuCompTypeActionPerformed(((JMenuItem)evt.getSource()).getText());
                }
            });
            

        }
    }
    
    private void initMarkList(TM_ComposantType cp){
        Marques = MarqueDao.getListeMarque(cp);
        list_marque.removeAll();
        for (int i = 0; i < Marques.size(); i++) {
            list_marque.add(Marques.get(i).toString());
            this.validate();
        }
    }
   
    
    private void initSpecList(TM_ComposantType cp){
        Specs = SpecificationDao.getListeSpec(cp);
        list_spec.removeAll();
        for (TM_Specification s : Specs) {
            for (TM_SpecificationAsValue spv : s.getValpos()){
                SpecV.add(spv);
                list_spec.add(spv.toString());
            }
            this.validate();
        }
    }
    

    
    public void menuCompTypeActionPerformed(String Source){
        for (int i = 0; i < CompoType.size(); i++) {
           if (((TM_ComposantType)CompoType.get(i)).equals(Source)){
               initMarkList(CompoType.get(i));
               initSpecList(CompoType.get(i));
               cp = CompoType.get(i);
               list_composants.removeAll();
               list_marque.setEnabled(true);
               list_spec.setEnabled(true);
               tf_prixMax.setEnabled(true);
               tf_prixMin.setEnabled(true);
           }
       } 
    }
    
    private void loadCompo(){
        TM_Marque m ;
        TM_SpecificationAsValue s;
        if (list_marque.getSelectedIndexes().length > 0){
            m = Marques.get(list_marque.getSelectedIndex());
        }
        else m = null;
        if (list_spec.getSelectedIndexes().length > 0){
            s = SpecV.get(list_spec.getSelectedIndex());
        }
        else s = null;
        list_composants.removeAll();
        ComposantsTrouvee = new ArrayList<>();
        double min = (tf_prixMin.getText().length() > 0)? 
        Double.parseDouble(tf_prixMin.getText()) : 0.0;
        double max = (tf_prixMax.getText().length() > 0)? 
        Double.parseDouble(tf_prixMax.getText()) : 9999999.95;
        for (TM_Composant c : allComp){
            ComposantDao.setSpecAsValue(c);
            if (verif(c,m,s, max, min)){
                ComposantsTrouvee.add(c);
                list_composants.add(c.toString());
            }
        }
    }
    
    public boolean verif (TM_Composant c, TM_Marque m, TM_SpecificationAsValue s,
                            double prixMax, double prixMin){
        boolean vpmin = true; boolean vpmax = true;
        boolean vs = true; boolean vm = true;
        if (prixMax > 0){
            if (c.getPrix() > prixMax) vpmax = false;
        }
        if (prixMin > 0){
            if (c.getPrix() < prixMin) vpmin = false;
        }
        if (m != null){
            vm = false;
            if (c.getMarque().equals(m)) vm= true;
        }
        
        if (s != null){
            vs=false;
            for (TM_SpecificationAsValue spv : c.getSpecifications()){
               if (s.equals(spv)) vs= true;
               spv.getSpec().toString();
            }
        }
        return vpmin&&vpmax&&vs&&vm&&verif(c);
    }
    
    public boolean verif (TM_Composant c){
        return c.getCompoType().equals(cp);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_marque = new java.awt.Label();
        lb_Spec = new java.awt.Label();
        list_composants = new java.awt.List();
        list_marque = new java.awt.List();
        list_spec = new java.awt.List();
        lbPrixMin = new java.awt.Label();
        lbPrixMax = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jbt_GoPan = new javax.swing.JButton();
        jbt_PanAdd = new javax.swing.JButton();
        tf_prixMin = new java.awt.TextField();
        tf_prixMax = new java.awt.TextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMain = new javax.swing.JMenu();
        menuFermer = new javax.swing.JMenuItem();
        men_compType = new javax.swing.JMenu();
        men_help = new javax.swing.JMenu();
        men_help_apropos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TechMarket Client");
        setLocation(new java.awt.Point(150, 150));
        setName("frClient"); // NOI18N
        setResizable(false);

        lb_marque.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lb_marque.setText("Marques");

        lb_Spec.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lb_Spec.setText("Spécifications");

        list_marque.setEnabled(false);
        list_marque.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                list_marqueItemStateChanged(evt);
            }
        });

        list_spec.setEnabled(false);
        list_spec.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                list_specItemStateChanged(evt);
            }
        });

        lbPrixMin.setText("Prix minimal");

        lbPrixMax.setText("Prix maximal");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Liste des composants");

        jbt_GoPan.setText("Panier");
        jbt_GoPan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_GoPanActionPerformed(evt);
            }
        });

        jbt_PanAdd.setText("Ajouter au panier");
        jbt_PanAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_PanAddActionPerformed(evt);
            }
        });

        tf_prixMin.setEnabled(false);
        tf_prixMin.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                tf_prixGestTextValueChanged(evt);
            }
        });
        tf_prixMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_prixMinKeyPressed(evt);
            }
        });

        tf_prixMax.setEnabled(false);
        tf_prixMax.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                tf_prixGestTextValueChanged(evt);
            }
        });
        tf_prixMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_prixMaxKeyPressed(evt);
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

        men_compType.setText("Types de composants");
        jMenuBar1.add(men_compType);

        men_help.setText("Aide");

        men_help_apropos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        men_help_apropos.setText("À propos");
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbt_PanAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbt_GoPan, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_marque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(list_marque, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_Spec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(list_spec, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPrixMax, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPrixMin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_prixMin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_prixMax, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(list_composants, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_marque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_Spec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(list_spec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(list_marque, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbPrixMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(tf_prixMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbPrixMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(tf_prixMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(list_composants, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbt_GoPan)
                    .addComponent(jbt_PanAdd))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbt_GoPanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_GoPanActionPerformed
        TM_PanierInterface pan = TM_PanierInterface.getInstance(alc);
        pan.setVisible(true);
    }//GEN-LAST:event_jbt_GoPanActionPerformed

    private void men_help_aproposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_men_help_aproposActionPerformed
        TM_APropos.getInstance().setVisible(true);
    }//GEN-LAST:event_men_help_aproposActionPerformed

    private void menuFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFermerActionPerformed
        this.dispose();
    }//GEN-LAST:event_menuFermerActionPerformed

    private void jbt_PanAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_PanAddActionPerformed
        ArrayList<TM_Composant> alcToCp = new ArrayList<TM_Composant>();
        for (TM_LigneCommande lc : alc){
            alcToCp.add(lc.getCompo());
        }
        TM_Composant comp = ComposantsTrouvee.get(list_composants.getSelectedIndex());
        if (! alcToCp.contains(comp)){
            alc.add(new TM_LigneCommande(comp, 1));
        }
        else{
            for (TM_LigneCommande lc : alc){
                if (lc.equals(comp))
                    lc.setQte(lc.getQte() +1);
            }
        }
    }//GEN-LAST:event_jbt_PanAddActionPerformed

    private void list_marqueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_list_marqueItemStateChanged
       // if (list_spec.getSelectedIndexes().length > 0) 
            loadCompo();
    }//GEN-LAST:event_list_marqueItemStateChanged

    private void list_specItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_list_specItemStateChanged
        //if (list_marque.getSelectedIndexes().length > 0) 
            loadCompo();
    }//GEN-LAST:event_list_specItemStateChanged

    private void tf_prixMinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_prixMinKeyPressed
        filterTools.filterPositiveInt(evt);
    }//GEN-LAST:event_tf_prixMinKeyPressed

    private void tf_prixMaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_prixMaxKeyPressed
        filterTools.filterPositiveInt(evt);
    }//GEN-LAST:event_tf_prixMaxKeyPressed

    private void tf_prixGestTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_tf_prixGestTextValueChanged
        double min = (tf_prixMin.getText().length() > 0)? 
                Double.parseDouble(tf_prixMin.getText()) : 0.0;
        double max = (tf_prixMax.getText().length() > 0)? 
                Double.parseDouble(tf_prixMax.getText()) : 9999999.95;
        if (min > max){
            JOptionPane.showMessageDialog(this,
                    "Erreur le prix minimal ne peut pas dépasser le prix maximal",
                    "Erreur : Prix minimal trop élevé",
                    JOptionPane.ERROR_MESSAGE);
            tf_prixMin.setText("");
        }else{
            loadCompo();
        }
    }//GEN-LAST:event_tf_prixGestTextValueChanged

    
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
            java.util.logging.Logger.getLogger(TM_ClientInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TM_ClientInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TM_ClientInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TM_ClientInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TM_ClientInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton jbt_GoPan;
    private javax.swing.JButton jbt_PanAdd;
    private java.awt.Label lbPrixMax;
    private java.awt.Label lbPrixMin;
    private java.awt.Label lb_Spec;
    private java.awt.Label lb_marque;
    private java.awt.List list_composants;
    private java.awt.List list_marque;
    private java.awt.List list_spec;
    private javax.swing.JMenu men_compType;
    private javax.swing.JMenu men_help;
    private javax.swing.JMenuItem men_help_apropos;
    private javax.swing.JMenuItem menuFermer;
    private javax.swing.JMenu menuMain;
    private java.awt.TextField tf_prixMax;
    private java.awt.TextField tf_prixMin;
    // End of variables declaration//GEN-END:variables
}
