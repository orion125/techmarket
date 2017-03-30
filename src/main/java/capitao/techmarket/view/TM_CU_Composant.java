package capitao.techmarket.view;

import capitao.base.ComposantDao;
import capitao.base.ComposantTypeDao;
import capitao.base.MarqueDao;
import capitao.base.SpecificationDao;
import capitao.techmarket.domaine.TM_Composant;
import capitao.techmarket.domaine.TM_ComposantType;
import capitao.techmarket.domaine.TM_Marque;
import capitao.techmarket.domaine.TM_Specification;
import capitao.techmarket.domaine.TM_SpecificationAsValue;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
/**
 *
 * @author jonathan.capitao
 */
public class TM_CU_Composant extends javax.swing.JFrame {
    
    private static TM_CU_Composant MyWindows = null;
    public ArrayList<TM_Specification> alistSpec = new ArrayList();
    public ArrayList<TM_Specification> alistSpecDispo = new ArrayList<>();
    public ArrayList<TM_Marque> alistMarqueDispo = new ArrayList<TM_Marque>();
    public ArrayList<TM_Marque> alistMarques = new ArrayList<TM_Marque>();
    public ArrayList<TM_ComposantType> alistCt = new ArrayList<TM_ComposantType>();
    public JLabel[] labelSpecUsed = new JLabel[10];
    TM_RD_Composant parent;
    public int nbSpecUsed = 0;
    
    private TM_Composant compoCourant = new TM_Composant();
    private TableModel compoTableModel;
    public int row;
    public boolean premier = true;
    public boolean mod = false;
    
       
    public static TM_CU_Composant getInstance(TM_RD_Composant parent,TM_Composant composant) {
        if (MyWindows == null){
            MyWindows = new TM_CU_Composant(composant);
        }
        MyWindows.parent = parent;
        return MyWindows;    
    }   
    
    /**
     * Creates new form TM_CU_Composant
     */
    
    // Sélectionner un type de composant et initialise les listes
    public void selectCompoType(TM_ComposantType ct){
        listSpecUsed.removeAll();
        listMarque.removeAll();
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
        alistMarqueDispo = MarqueDao.getListeMarque(ct);
        for (TM_Marque m : alistMarqueDispo){
            listMarque.add(m.toString());
        }
    }
    
    private TM_CU_Composant(TM_Composant composant) {
        initComponents();
        initTypeCt();
        initMarque();
        this.setLocationRelativeTo(null);
    }   
    
    // Initialise l'arrayliste des catégories de composants
    public void initTypeCt(){
        alistCt = ComposantTypeDao.getListeCatCompo();
        for (TM_ComposantType ct : alistCt){
            listTypeCompo.add(ct.toString());
        } 
    }    
    // Initialise l'arrayliste des marques
    public void initMarque(){
        alistMarques = MarqueDao.getListeMarque();
    }
    // Met a jour l'interface. (En fonction du mode de la fenètre (ajout ou modification)
    // mod = true : mode - modification, mod = false : mode - ajout
    public void initGen(TM_Composant composant, boolean mod){
        //principal
        listTypeCompo.setEnabled(!mod);
        if (composant != null && mod){
            this.mod = true;
            compoCourant = composant;
            ComposantDao.setSpecAsValue(compoCourant);
            //System.out.println(composant.toString());
            selectCompoType(compoCourant.getCompoType());
            int i = (compoCourant.getCompoType()).getId()-1;
            tfPrix.setText(""+compoCourant.getPrix());
            listTypeCompo.select(i);
            int j = -1;
            for (TM_Marque m : alistMarqueDispo){
                j++;
                if (m.equals(compoCourant.getMarque())){
                    System.out.println("j ="+j);
                    listMarque.select(j);
                }
            }
            this.setTitle("Modifier le composant : " +compoCourant.getNom());
            tfName.setText(compoCourant.getNom());
        }else{
            this.mod = false;
            compoCourant = new TM_Composant();
            tfPrix.setText("0.0");
            listSpecUsed.removeAll();
            this.setTitle("Ajout de composant");
            tfName.setText(" ");
            listTypeCompo.select(-1);
        }
        
        // Gestion des spécification et du table model du jtable
        jtableSpecUsed.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (jtableSpecUsed.getSelectedRow() != -1){
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
    
    // Recherche la spécification sélectionnée (pour message d'erreur des données entre autre)
    public void reloadSpecSelect(){
        //System.out.println("Cellule selectionnée !");
        TM_Specification specSelect = ((TM_SpecificationAsValue) jtableSpecUsed.getValueAt(row, 0)).getSpec();
    }
    
    // Détermine le type d'action a effectué 
    // puis appel le DAO pour l'ajout ou la modification des données
    public void createMod(){
        compoCourant.setNom(tfName.getText());
        compoCourant.setPrix(new Double(tfPrix.getText()));
        if (mod){
            ComposantDao.update(compoCourant);
        }else{
            ComposantDao.insert(compoCourant);
        }
        this.parent.initList();
    }
    
    // Charge la liste des spécification disponible dans la liste
    public void loadlistSpec(){
        for (int i = 0; i < alistSpecDispo.size(); i++) {
            listSpecDispo.add(alistSpecDispo.get(i).toString());
        }
    }
    
    // Recharge les données du jtable
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
    
    // Vérifie si la  quantitée de valeur de spécification utilisée par le composant
    // n'est pas vide
    public boolean isNotEmpty (){
        return !(compoCourant.getSpecifications().size() < 1);
    }
    
    // Ajoute une nouvelle spécification à un composant
    public void addnewSpec(){
        TM_Specification specSelect = alistSpecDispo.get(listSpecDispo.getSelectedIndex());
        listSpecUsed.add(specSelect.getNom());
        ArrayList valpos = specSelect.getValpos();
        TM_SpecificationAsValue specAsValue = (TM_SpecificationAsValue)specSelect.getValpos().get(0);
        // Préchargement de valeur
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
    
    // Enlève une spécification d'un composant
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
    
    // Vérifie si une spécification à déjà été rajoutée
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
        listMarque = new java.awt.List();
        lbMarque = new java.awt.Label();
        lbPrix = new java.awt.Label();
        tfPrix = new java.awt.TextField();
        jPanSpecDet = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableSpecUsed = new javax.swing.JTable();
        lbSpcsChoosed = new java.awt.Label();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMain = new javax.swing.JMenu();
        menuFermer = new javax.swing.JMenuItem();
        men_help = new javax.swing.JMenu();
        men_help_apropos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(655, 595));
        setResizable(false);
        setSize(new java.awt.Dimension(655, 595));

        btValider.setText("Valider");
        btValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btValiderActionPerformed(evt);
            }
        });

        lbName.setText("Libell� du composant");

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

        lbSpecDispo.setText("Sp�cifications");

        lbSpecUsed.setText("Poss�d�es");

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

        listMarque.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listMarqueItemStateChanged(evt);
            }
        });

        lbMarque.setText("Marques");

        lbPrix.setText("Prix :");

        tfPrix.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfPrixKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panAddModLayout = new javax.swing.GroupLayout(panAddMod);
        panAddMod.setLayout(panAddModLayout);
        panAddModLayout.setHorizontalGroup(
            panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAddModLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                        .addComponent(listSpecDispo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btAddNewSpec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btRemoveSpec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(listSpecUsed, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                        .addComponent(lbSpecDispo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbSpecUsed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panAddModLayout.createSequentialGroup()
                        .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listTypeCompo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTypeAssoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panAddModLayout.createSequentialGroup()
                                .addComponent(lbMarque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(listMarque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lbPrix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panAddModLayout.createSequentialGroup()
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPrix, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panAddModLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(btValider)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        panAddModLayout.setVerticalGroup(
            panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPrix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panAddModLayout.createSequentialGroup()
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
                        .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panAddModLayout.createSequentialGroup()
                                .addComponent(lbTypeAssoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(listMarque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(listTypeCompo, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)))
                            .addComponent(lbMarque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btValider))
                    .addGroup(panAddModLayout.createSequentialGroup()
                        .addComponent(tfPrix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        lbSpecUsed.getAccessibleContext().setAccessibleName("Possed�es");

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

        lbSpcsChoosed.setText("Valeurs des sp�cifications choisient");

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
                        .addGap(0, 127, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanSpecDetLayout.setVerticalGroup(
            jPanSpecDetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanSpecDetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSpcsChoosed, javax.swing.GroupLayout.PREFERRED_SIZE, 11, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(panAddMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanSpecDet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panAddMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
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

    private void listTypeCompoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listTypeCompoItemStateChanged
        compoCourant.setCompoType(alistCt.get(listTypeCompo.getSelectedIndex()));
        selectCompoType(alistCt.get(listTypeCompo.getSelectedIndex()));
       //System.out.println(alistCt.get(listTypeCompo.getSelectedIndex()).toString());
    }//GEN-LAST:event_listTypeCompoItemStateChanged

    private void tfPrixKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrixKeyPressed
        capitao.outils.filterTools.filterPositiveInt(evt);
    }//GEN-LAST:event_tfPrixKeyPressed

    private void listMarqueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listMarqueItemStateChanged
        compoCourant.setMarque(alistMarqueDispo.get(listMarque.getSelectedIndex()));
    }//GEN-LAST:event_listMarqueItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddNewSpec;
    private javax.swing.JButton btRemoveSpec;
    private javax.swing.JButton btValider;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanSpecDet;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtableSpecUsed;
    private java.awt.Label lbMarque;
    private java.awt.Label lbName;
    private java.awt.Label lbPrix;
    private java.awt.Label lbSpcsChoosed;
    private java.awt.Label lbSpecDispo;
    private java.awt.Label lbSpecUsed;
    private java.awt.Label lbTypeAssoc;
    private java.awt.List listMarque;
    private java.awt.List listSpecDispo;
    private java.awt.List listSpecUsed;
    private java.awt.List listTypeCompo;
    private javax.swing.JMenu men_help;
    private javax.swing.JMenuItem men_help_apropos;
    private javax.swing.JMenuItem menuFermer;
    private javax.swing.JMenu menuMain;
    private javax.swing.JPanel panAddMod;
    private java.awt.TextField tfName;
    private java.awt.TextField tfPrix;
    // End of variables declaration//GEN-END:variables
}
