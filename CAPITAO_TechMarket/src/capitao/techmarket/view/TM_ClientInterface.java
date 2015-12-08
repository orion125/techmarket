/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.techmarket.view;
import capitao.base.ComposantTypeDao;
import capitao.techmarket.domaine.TM_Composant;
import capitao.techmarket.domaine.TM_Marque;
import capitao.techmarket.domaine.TM_ComposantType;
import java.awt.CheckboxGroup;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.Action;
import javax.swing.JMenuItem;
import jdk.nashorn.internal.objects.NativeArray;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 *
 * @author jonathan.capitao
 */
public class TM_ClientInterface extends javax.swing.JFrame {

    public static TM_ClientInterface MyWindows = null;
    public ArrayList<TM_ComposantType> CompoType = new ArrayList();
    public ArrayList<TM_Marque> Marques = new ArrayList();
    public ArrayList<TM_Composant> ComposantsTrouvee = new ArrayList();
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
        // Testing code
        CompoType = ComposantTypeDao.getListeCatCompo() ;        
    /*    CompoType.add(new TM_ComposantType(1,"Carte graphiques"));
        CompoType.add(new TM_ComposantType(2,"Carte mères"));
        CompoType.add(new TM_ComposantType(3,"Casques"));
        CompoType.add(new TM_ComposantType(4,"Claviers"));
        CompoType.add(new TM_ComposantType(5,"Disque Dur"));
        CompoType.add(new TM_ComposantType(6,"Ecrans"));
        CompoType.add(new TM_ComposantType(7,"Mémoire vive (RAM)"));
        CompoType.add(new TM_ComposantType(8,"Souris"));
        CompoType.add(new TM_ComposantType(9,"Imprimante"));
        CompoType.add(new TM_ComposantType(10,"Processeur"));*/
                
        // Main Code
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
        // Testing code
        list_marque.removeAll();
        
        if (onTimeInitArray == true) {
            int[] listNb =  {1,2};
            ArrayList array1 = new ArrayList();
            for (int i= 0;i<listNb.length;i++)
                array1.add(CompoType.get(i));
            Marques.add(new TM_Marque(1, "ASUS", array1));
            Marques.add(new TM_Marque(2, "ZOOTAC", array1));
            Marques.add(new TM_Marque(3, "NVIDIA", array1));
            
            int[] listNb4 =  {6,9};
            ArrayList array2 = new ArrayList();
            for (int i= 0;i<listNb4.length;i++)
                array2.add(CompoType.get(i));
            Marques.add(new TM_Marque(4, "HP", array2));
            int[] listNb5 =  {6};
            ArrayList array3 = new ArrayList();
            array3.add(CompoType.get(listNb5[0]-1));
            Marques.add(new TM_Marque(5, "PHILIPS", array3));
            int[] listNb6 =  {7,10};
            ArrayList array4 = new ArrayList();
            for (int i= 0;i<listNb6.length;i++)
                array4.add(CompoType.get(i));
            Marques.add(new TM_Marque(6, "CORSAIR",array4));
            
            int[] listNb7 =  {3,4,8};
            ArrayList array5 = new ArrayList();
            for (int i= 0;i<listNb7.length;i++)
                array5.add(CompoType.get(i));
            Marques.add(new TM_Marque(7, "RAZER", array5));
            Marques.add(new TM_Marque(8, "LOGITECH", array5));
            
            int[] listNb9 =  {5};
            ArrayList array6 = new ArrayList();
            array3.add(CompoType.get(listNb9[0]-1));
            Marques.add(new TM_Marque(9, "SAMSUNG", array6));
            onTimeInitArray = false;
        }     
        
        setMarqueList(cp);        
    //    cp.getId()
        
    }
    
    private void setMarqueList(TM_ComposantType cp){
        int marqueTrouve = 0;
        for (int i = 0; i < Marques.size(); i++) {
            if (((TM_Marque)Marques.get(i)).hasTypeComposant(cp)){
                String nomMarque = ((TM_Marque)Marques.get(i)).getNom();
                list_marque.add(nomMarque);
                System.out.println(nomMarque);
                this.validate();
            }
        }
    }
    
   
    
    private void initSpecList(TM_ComposantType cp){
        
    }
    
    public void chiffreObliger (KeyEvent evt){       
            char c = evt.getKeyChar() ;
                
        if (!   ((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE) 
            ||  (c== KeyEvent.VK_ENTER)      || (c == KeyEvent.VK_TAB) 
            ||  (Character.isDigit(c)))) 
        {
           evt.consume() ;
        } 
    }
    
    public void menuCompTypeActionPerformed(String Source){
        for (int i = 0; i < CompoType.size(); i++) {
           if (((TM_ComposantType)CompoType.get(i)).equals(Source)){
               initMarkList((TM_ComposantType)CompoType.get(i));
            //   initModelList((TM_Composant_Type)CompoType.get(i));
            //   initSpecList((TM_Composant_Type)CompoType.get(i));
           }
       } 
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
        list1 = new java.awt.List();
        lbPrixMin = new java.awt.Label();
        lbPrixMax = new java.awt.Label();
        tf_prixMin = new javax.swing.JTextField();
        tf_prixMax = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jbt_GoPan = new javax.swing.JButton();
        jbt_PanAdd = new javax.swing.JButton();
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

        lb_marque.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lb_marque.setText("Marques");

        lb_Spec.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lb_Spec.setText("Spécifications");

        list_composants.setMultipleMode(true);

        list_marque.setMultipleMode(true);

        list1.setMultipleMode(true);

        lbPrixMin.setText("Prix minimal");

        lbPrixMax.setText("Prix maximal");

        tf_prixMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_prixMinKeyTyped(evt);
            }
        });

        tf_prixMax.setToolTipText("");
        tf_prixMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_prixMaxKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setText("Liste des composants");

        jbt_GoPan.setText("Panier");
        jbt_GoPan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_GoPanActionPerformed(evt);
            }
        });

        jbt_PanAdd.setText("Ajouter au panier");

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
                                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_prixMax, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPrixMax, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tf_prixMin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbPrixMin, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
                            .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tf_prixMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_prixMinKeyTyped
        chiffreObliger(evt);
    }//GEN-LAST:event_tf_prixMinKeyTyped

    private void tf_prixMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_prixMaxKeyTyped
        chiffreObliger(evt);
    }//GEN-LAST:event_tf_prixMaxKeyTyped

    private void jbt_GoPanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_GoPanActionPerformed
        TM_PanierInterface.getInstance().setVisible(true);
    }//GEN-LAST:event_jbt_GoPanActionPerformed

    private void men_help_aproposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_men_help_aproposActionPerformed
        TM_APropos.getInstance().setVisible(true);
    }//GEN-LAST:event_men_help_aproposActionPerformed

    private void menuFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFermerActionPerformed
        this.dispose();
    }//GEN-LAST:event_menuFermerActionPerformed

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
    private java.awt.List list1;
    private java.awt.List list_composants;
    private java.awt.List list_marque;
    private javax.swing.JMenu men_compType;
    private javax.swing.JMenu men_help;
    private javax.swing.JMenuItem men_help_apropos;
    private javax.swing.JMenuItem menuFermer;
    private javax.swing.JMenu menuMain;
    private javax.swing.JTextField tf_prixMax;
    private javax.swing.JTextField tf_prixMin;
    // End of variables declaration//GEN-END:variables
}
