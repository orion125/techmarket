/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.techmarket.view;

import capitao.base.ComposantDao;
import capitao.base.ComposantTypeDao;
import capitao.base.SpecificationDao;
import capitao.techmarket.domaine.TM_Composant;
import capitao.techmarket.domaine.TM_ComposantType;
import capitao.techmarket.domaine.TM_Specification;
import capitao.techmarket.domaine.TM_SpecificationAsValue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import sun.security.krb5.JavaxSecurityAuthKerberosAccess;
/**
 *
 * @author jonathan.capitao
 */
public class TM_CU_Composant extends javax.swing.JFrame {
    
    public static TM_CU_Composant MyWindows = null;
    public ArrayList<TM_Specification> alistSpec = new ArrayList();
    public ArrayList<TM_Specification> alistSpecDispo = new ArrayList<>();
    public ArrayList<TM_ComposantType> alistCt = new ArrayList<TM_ComposantType>();
    public JLabel[] labelSpecUsed = new JLabel[10];
    //public JComboBox comboSpecUsed;
    public int nbSpecUsed = 0;
    
    private TM_Composant compoCourant = new TM_Composant();
    private TableModel compoTableModel;
    public int row;
    public boolean premier = true;
    public static TM_CU_Composant getInstance(TM_Composant composant) {
        if (MyWindows == null){
            MyWindows = new TM_CU_Composant(composant);
        }
        return MyWindows;    
    }   
    
    /**
     * Creates new form TM_CU_Composant
     */
    
    public void selectCompoType(TM_ComposantType ct){
        listSpecUsed.removeAll();
        alistSpecDispo.removeAll(alistSpecDispo);
        listSpecDispo.removeAll();
        alistSpecDispo = SpecificationDao.getListeSpec(compoCourant.getCompoType());
        for (TM_SpecificationAsValue spv : compoCourant.getSpecifications()){
            alistSpec.add(spv.getSpec());
        }
        for (TM_Specification s : alistSpecDispo){
            if (alistSpec.contains(s))
                listSpecUsed.add(s.toString());
            listSpecDispo.add(s.toString());
        }
    }
    
    private TM_CU_Composant(TM_Composant composant) {
        initComponents();
        initTypeCt();
        this.setLocationRelativeTo(null);
    }   
    
    public void initTypeCt(){
        alistCt = ComposantTypeDao.getListeCatCompo();
        for (TM_ComposantType ct : alistCt){
            listTypeCompo.add(ct.toString());
        } 
    }
     
    public void initGen(TM_Composant composant, boolean mod){
        //principal
        if (composant != null && mod){
            compoCourant = composant;
            ComposantDao.setSpecAsValue(compoCourant);
            //System.out.println(composant.toString());
            selectCompoType(compoCourant.getCompoType());
            /* for(TM_SpecificationAsValue s: composant.getSpecifications()) {
                listSpecUsed.add(s.toString());
            }*/
            int i = (compoCourant.getCompoType()).getId()-1;
            listTypeCompo.select(i);
            this.setTitle("Modifier le composant : " +compoCourant.getNom());
            tfName.setText(compoCourant.getNom());
        }else{
            compoCourant = new TM_Composant();
            listSpecUsed.removeAll();
            this.setTitle("Ajout de composant");
            tfName.setText(null);
            listTypeCompo.select(-1);
        }
        
        
        jtableSpecUsed.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jtableSpecUsed.getSelectedRow() != -1){
                    //System.out.println(jtableSpecUsed.getSelectedRow());
                    row = jtableSpecUsed.getSelectedRow();
                    reloadSpecSelect();
                }
            }
        });        
        compoTableModel = compoCourant;
        jtableSpecUsed.setModel(compoTableModel);
        btAddNewSpec.setEnabled(listSpecDispo.getSelectedIndexes().length > 0);
        btRemoveSpec.setEnabled(listSpecUsed.getSelectedIndexes().length > 0);
    }
    
    public void reloadSpecSelect(){
        //System.out.println("Cellule selectionnée !");
        TM_Specification specSelect = ((TM_SpecificationAsValue) jtableSpecUsed.getValueAt(row, 0)).getSpec();
    }
    
    public void createMod(){
        return;
    }
    
    public void loadlistSpec(){
        for (int i = 0; i < alistSpecDispo.size(); i++) {
            listSpecDispo.add(alistSpecDispo.get(i).toString());
        }
    }
    
    public void reloadTable(){
        TableModel temps = new TM_Composant();
        jtableSpecUsed.setModel(temps);
        temps = compoCourant;
        jtableSpecUsed.setModel(temps);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public boolean isNotEmpty (){
        return !(compoCourant.getSpecifications().size() < 1);
    }
    
    public void addnewSpec(){
        TM_Specification specSelect = alistSpecDispo.get(listSpecDispo.getSelectedIndex());
        listSpecUsed.add(specSelect.getNom());
        ArrayList valpos = specSelect.getValpos();
        TM_SpecificationAsValue specAsValue = (TM_SpecificationAsValue)specSelect.getValpos().get(0) ;    
        if (compoCourant.getSpecifications().size() <= 10){
            compoCourant.addSpecification(specAsValue);
            TM_Specification SpecInlist = ((TM_SpecificationAsValue) jtableSpecUsed.getValueAt
                (compoCourant.getSpecifications().size()-1, 0)).getSpec();
            row = (compoCourant.getSpecifications().size()-1);
            reloadSpecSelect();          
            nbSpecUsed++;
        }
        this.pack();
        this.validate();  
        isAddDispo();
    }
    public void removeSpec(){
        if (isNotEmpty()){
            int idselect = listSpecUsed.getSelectedIndex();
            TM_Specification specSelect = (TM_Specification)alistSpec.get(idselect);
            listSpecUsed.remove(idselect);
            ArrayList valpos = specSelect.getValpos();
            TM_SpecificationAsValue specAsValue = compoCourant.getSpecification(idselect);    
            compoCourant.removeSpecification(specAsValue);
            if (isNotEmpty()){
                TM_Specification SpecInlist = ((TM_SpecificationAsValue) jtableSpecUsed.getValueAt
                    (compoCourant.getSpecifications().size()-1, 0)).getSpec();
                row = (compoCourant.getSpecifications().size()-1);
                reloadTable();
                reloadSpecSelect();
            }
            btRemoveSpec.setEnabled(listSpecUsed.getSelectedIndexes().length > 0);
            alistSpec.remove(idselect);
        }
        this.pack();
        this.validate(); 
        isAddDispo();
    }
    

    public void isAddDispo(){
        boolean doNotContains = true;    
        //System.out.println(listSpecUsed.getItems().length);
        if (listSpecUsed.getItems().length > 0){
            for (int i = 0; i < listSpecUsed.getItems().length;i++){
                //System.out.println(listSpecUsed.getItem(i));
                if (listSpecDispo.getSelectedItem().equals(listSpecUsed.getItem(i))){
                    doNotContains = false;
                }
            }
        }
        btAddNewSpec.setEnabled(listSpecDispo.getSelectedIndexes().length > 0 && doNotContains);       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panAddMod = new javax.swing.JPanel();
        btValider = new javax.swing.JButton();
        tfName = new java.awt.TextField();
        lbName = new java.awt.Label();
        lbTypeAssoc = new java.awt.Label();
        listTypeCompo = new java.awt.List();
        listSpecDispo = new java.awt.List();
        listSpecUsed = new java.awt.List();
        lbSpecDispo = new java.awt.Label();
        lbSpecUsed = new java.awt.Label();
        btAddNewSpec = new javax.swing.JButton();
        btRemoveSpec = new javax.swing.JButton();
        jPanSpecDet = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableSpecUsed = new javax.swing.JTable();
        lbSpcsChoosed = new java.awt.Label();
        listEmplac = new java.awt.List();
        lbEmplacementDeStockChoisie = new java.awt.Label();
        label1 = new java.awt.Label();
        listEmplacUsed = new java.awt.List();
        btRemoveSpec1 = new javax.swing.JButton();
        btAddNewSpec1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMain = new javax.swing.JMenu();
        menuFermer = new javax.swing.JMenuItem();
        men_help = new javax.swing.JMenu();
        men_help_apropos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(655, 550));
        setResizable(false);
        setSize(new java.awt.Dimension(655, 550));

        btValider.setText("Valider");
        btValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btValiderActionPerformed(evt);
            }
        });

        lbName.setText("Libellé du composant");

        lbTypeAssoc.setText("Types de composants");

        listTypeCompo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listTypeCompoItemStateChanged(evt);
            }
        });

        listSpecDispo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listSpecDispoItemStateChanged(evt);
            }
        });

        listSpecUsed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listSpecUsedItemStateChanged(evt);
            }
        });

        lbSpecDispo.setText("Spécifications");

        lbSpecUsed.setText("Possédées");

        btAddNewSpec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capitao/techmarket/view/image/green_globe_right_arrow_559_rs.jpg"))); // NOI18N
        btAddNewSpec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddNewSpecActionPerformed(evt);
            }
        });

        btRemoveSpec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capitao/techmarket/view/image/green_globe_left_arrow_558_rs.jpg"))); // NOI18N
        btRemoveSpec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveSpecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panAddModLayout = new javax.swing.GroupLayout(panAddMod);
        panAddMod.setLayout(panAddModLayout);
        panAddModLayout.setHorizontalGroup(
            panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btValider)
                .addGap(111, 111, 111))
            .addGroup(panAddModLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                        .addComponent(listSpecDispo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btAddNewSpec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btRemoveSpec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(listSpecUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                        .addComponent(lbSpecDispo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbSpecUsed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(listTypeCompo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panAddModLayout.createSequentialGroup()
                        .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTypeAssoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSpecUsed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSpecDispo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panAddModLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(listSpecDispo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(listSpecUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panAddModLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btAddNewSpec, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRemoveSpec, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTypeAssoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(listTypeCompo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btValider)
                .addContainerGap())
        );

        lbSpecUsed.getAccessibleContext().setAccessibleName("Possedées");

        jtableSpecUsed.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jtableSpecUsed);

        lbSpcsChoosed.setText("Valeurs des spécifications choisient");

        lbEmplacementDeStockChoisie.setText("Emplacement dans le stocks");

        label1.setText("Utilisés");

        btRemoveSpec1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capitao/techmarket/view/image/green_globe_left_arrow_558_rs.jpg"))); // NOI18N
        btRemoveSpec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveSpec1ActionPerformed(evt);
            }
        });

        btAddNewSpec1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capitao/techmarket/view/image/green_globe_right_arrow_559_rs.jpg"))); // NOI18N
        btAddNewSpec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddNewSpec1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanSpecDetLayout = new javax.swing.GroupLayout(jPanSpecDet);
        jPanSpecDet.setLayout(jPanSpecDetLayout);
        jPanSpecDetLayout.setHorizontalGroup(
            jPanSpecDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanSpecDetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanSpecDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanSpecDetLayout.createSequentialGroup()
                        .addComponent(lbSpcsChoosed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanSpecDetLayout.createSequentialGroup()
                        .addGroup(jPanSpecDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmplacementDeStockChoisie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanSpecDetLayout.createSequentialGroup()
                                .addComponent(listEmplac, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addGroup(jPanSpecDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btAddNewSpec1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btRemoveSpec1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanSpecDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listEmplacUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanSpecDetLayout.setVerticalGroup(
            jPanSpecDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanSpecDetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSpcsChoosed, javax.swing.GroupLayout.PREFERRED_SIZE, 11, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanSpecDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanSpecDetLayout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(listEmplacUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanSpecDetLayout.createSequentialGroup()
                        .addComponent(lbEmplacementDeStockChoisie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanSpecDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanSpecDetLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(listEmplac, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanSpecDetLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(btAddNewSpec1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btRemoveSpec1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        lbEmplacementDeStockChoisie.getAccessibleContext().setAccessibleParent(panAddMod);

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
                .addComponent(panAddMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanSpecDet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panAddMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanSpecDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btValiderActionPerformed
        createMod();
    }//GEN-LAST:event_btValiderActionPerformed

    private void btAddNewSpecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddNewSpecActionPerformed
        addnewSpec();
    }//GEN-LAST:event_btAddNewSpecActionPerformed

    private void btRemoveSpecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveSpecActionPerformed
        removeSpec();
    }//GEN-LAST:event_btRemoveSpecActionPerformed

    private void listSpecDispoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listSpecDispoItemStateChanged
        isAddDispo();
    }//GEN-LAST:event_listSpecDispoItemStateChanged

    private void listSpecUsedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listSpecUsedItemStateChanged
        btRemoveSpec.setEnabled(listSpecUsed.getSelectedIndexes().length > 0);
    }//GEN-LAST:event_listSpecUsedItemStateChanged

    private void menuFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFermerActionPerformed
        this.dispose();
    }//GEN-LAST:event_menuFermerActionPerformed

    private void men_help_aproposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_men_help_aproposActionPerformed
        TM_APropos.getInstance().setVisible(true);
    }//GEN-LAST:event_men_help_aproposActionPerformed

    private void btRemoveSpec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveSpec1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btRemoveSpec1ActionPerformed

    private void btAddNewSpec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddNewSpec1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btAddNewSpec1ActionPerformed

    private void listTypeCompoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listTypeCompoItemStateChanged
        compoCourant.setCompoType(alistCt.get(listTypeCompo.getSelectedIndex()));
        selectCompoType(alistCt.get(listTypeCompo.getSelectedIndex()));
       //System.out.println(alistCt.get(listTypeCompo.getSelectedIndex()).toString());
    }//GEN-LAST:event_listTypeCompoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddNewSpec;
    private javax.swing.JButton btAddNewSpec1;
    private javax.swing.JButton btRemoveSpec;
    private javax.swing.JButton btRemoveSpec1;
    private javax.swing.JButton btValider;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanSpecDet;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtableSpecUsed;
    private java.awt.Label label1;
    private java.awt.Label lbEmplacementDeStockChoisie;
    private java.awt.Label lbName;
    private java.awt.Label lbSpcsChoosed;
    private java.awt.Label lbSpecDispo;
    private java.awt.Label lbSpecUsed;
    private java.awt.Label lbTypeAssoc;
    private java.awt.List listEmplac;
    private java.awt.List listEmplacUsed;
    private java.awt.List listSpecDispo;
    private java.awt.List listSpecUsed;
    private java.awt.List listTypeCompo;
    private javax.swing.JMenu men_help;
    private javax.swing.JMenuItem men_help_apropos;
    private javax.swing.JMenuItem menuFermer;
    private javax.swing.JMenu menuMain;
    private javax.swing.JPanel panAddMod;
    private java.awt.TextField tfName;
    // End of variables declaration//GEN-END:variables
}
