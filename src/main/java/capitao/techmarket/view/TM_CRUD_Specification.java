package capitao.techmarket.view;

import capitao.base.ComposantTypeDao;
import capitao.base.SpecificationDao;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import capitao.techmarket.domaine.TM_ComposantType;
import capitao.techmarket.domaine.TM_Specification;
import capitao.techmarket.domaine.TM_SpecificationAsValue;
import java.util.ArrayList;
/**
 *
 * @author jonathan.capitao
 */
public class TM_CRUD_Specification extends javax.swing.JFrame {
    ArrayList<TM_Specification> spcs;
    ArrayList<TM_ComposantType> ct;
    String mod = "Add";
    TM_Specification specActu;   
    boolean typeIsInt = false;
    
    private static TM_CRUD_Specification MyWindows = null;
    
    public static TM_CRUD_Specification getInstance(){
        if (MyWindows == null){
            MyWindows = new TM_CRUD_Specification();
        }
        return MyWindows;
    }    
        
    /**
     * Creates new form TM_CRUD_Specialisatio
     */
    private TM_CRUD_Specification() {
        initComponents();
        this.setLocationRelativeTo(null);
           initList();
    }

    // Initialise les ArrayListes
    public void initList(){
        ct = ComposantTypeDao.getListeCatCompo();
        spcs = SpecificationDao.getListeSpec();
        loadList();
    }
    
    // charge la liste des spécifications
    public void loadList(){
        listSpec.removeAll();
        for (TM_Specification s : spcs){
            listSpec.add(s.toString());
        }
    }

    // RécupÃ¨re les types de composant utiliser
    public ArrayList<TM_ComposantType> getCompoTypeUtilis(){
        String[] st = listTypeCompoUsed.getItems();
        ArrayList<TM_ComposantType> ctUsed = new ArrayList<TM_ComposantType>();
        for (int i= 0; i< st.length ; i++){
            for (int j = 0; j < ct.size(); j++){
                if (st[i].equals(ct.get(j).getNom())){
                    ctUsed.add(ct.get(j));
                }
            }
        }
        return ctUsed;
    }    
    
    // Nétoie les listes
    public void clean(){
        listTypeCompo.removeAll();
        listTypeCompoUsed.removeAll();
        listValeurSpec.removeAll();
    }
    
    // Passe la fenÃ¨tre en modification
    public void mod(){
        clean();
        btRemoveType.setEnabled(false);
        jbtValDown.setEnabled(false);
        jbtValUp.setEnabled(false);
        listValeurSpec.setEnabled(false);
        btReiniVal.setEnabled(false);
        resize(true);
        mod = "Mod";
        specActu = spcs.get(listSpec.getSelectedIndex());
        specActu.setValpos(SpecificationDao.getSpecAsValue(specActu));
        tfName.setText(specActu.toString());
        actu();
    }
    
    // Passe la fenÃ¨tre en ajout
    public void add(){
        clean();
        btRemoveType.setEnabled(true);
        jbtValDown.setEnabled(true);
        jbtValUp.setEnabled(true);
        listValeurSpec.setEnabled(true);
        btReiniVal.setEnabled(true);
        resize(true);
        mod = "Add";
        tfName.setText("");
        actu();
    }
    
    // Actualise les données
    public void actu(){
        clean();
        if (mod.equals("Mod")){
            for (TM_ComposantType compT : ct){
                if (specActu.getCompoTypes().contains(compT)) listTypeCompoUsed.add(compT.toString());
                else listTypeCompo.add(compT.toString()); 
            }
            for (TM_SpecificationAsValue spv : specActu.getValpos()){
                listValeurSpec.add(spv.getValue());
            }
        }
        if (mod.equals("Add")){
            for (TM_ComposantType compT : ct) listTypeCompo.add(compT.toString());
        }
    }
    
    // Gestion des boutons flÃ¨cher pour les catégories de composants
    public void selectActu(){
        if (listTypeCompo.getItemCount() > 0)
            btAddNewType.setEnabled(listTypeCompo.getSelectedIndex()>-1); 
        else
            btAddNewType.setEnabled(false);
            
        if (listTypeCompoUsed.getItemCount() > 0 && !mod.equals("Mod"))
            btRemoveType.setEnabled(listTypeCompoUsed.getSelectedIndex()>-1);
        else
            btRemoveType.setEnabled(false);
    } 
    
    // Détermine le type d'action a effectué et vérifie si les champs son remplie
    // puis appel le DAO pour l'ajout ou la modification des données
    public void valider(){
        ArrayList<TM_ComposantType> ctUsed = getCompoTypeUtilis();
        if (mod.equals("Add")){
            if (!(tfName.getText().equals("")) &&
                    (listTypeCompoUsed.getItemCount() > 0) &&
                    (listValeurSpec.getItemCount() > 0)){
                specActu = new TM_Specification(0,tfName.getText(), ctUsed);
                setSpv(specActu);
                SpecificationDao.insert(specActu);
                spcs.add(specActu);
            } else {
                JOptionPane.showMessageDialog(this
                        , "Veuillez remplir toutes les données de la page"
                        , "Erreur : Données manquantes"
                        , JOptionPane.ERROR_MESSAGE
                );
            }
        }
        if (mod.equals("Mod")){
            if (!(tfName.getText().equals("")) && 
                    (listTypeCompoUsed.getItemCount() > 0) &&
                    (listValeurSpec.getItemCount() > 0)){
                specActu.setNom(tfName.getText());
                specActu.setCompoTypes(ctUsed);
                setSpv(specActu);
                SpecificationDao.update(specActu);
            } else {
                JOptionPane.showMessageDialog(this
                        , "Veuillez remplir toutes les données de la page"
                        , "Erreur : Données manquantes"
                        , JOptionPane.ERROR_MESSAGE
                );
            }
        }
        loadList();
    }
    
    public void setSpv(TM_Specification specActu){
        specActu.setValpos(new ArrayList<TM_SpecificationAsValue>());
        for (int i = 0; i < listValeurSpec.getItemCount(); i++)
            specActu.addValpos(
                new TM_SpecificationAsValue(-1,specActu, listValeurSpec.getItem(i))
            );
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listSpec = new java.awt.List();
        btAdd = new javax.swing.JButton();
        btMod = new javax.swing.JButton();
        btSuppr = new javax.swing.JButton();
        panAddMod = new javax.swing.JPanel();
        btValider = new javax.swing.JButton();
        tfName = new java.awt.TextField();
        lbName = new java.awt.Label();
        lbTypeAssoc = new java.awt.Label();
        listTypeCompo = new java.awt.List();
        listTypeCompoUsed = new java.awt.List();
        lbTypeUsed = new java.awt.Label();
        btAddNewType = new javax.swing.JButton();
        btRemoveType = new javax.swing.JButton();
        listValeurSpec = new java.awt.List();
        lbVal = new java.awt.Label();
        tfNewVal = new java.awt.TextField();
        lbValNew = new java.awt.Label();
        btAddNewVal = new javax.swing.JButton();
        btReiniVal = new javax.swing.JButton();
        jbtValUp = new javax.swing.JButton();
        jbtValDown = new javax.swing.JButton();
        lbListCompoType = new java.awt.Label();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMain = new javax.swing.JMenu();
        menuFermer = new javax.swing.JMenuItem();
        men_help = new javax.swing.JMenu();
        men_help_apropos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion des spécifications");
        setPreferredSize(new java.awt.Dimension(295, 527));
        setResizable(false);

        listSpec.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listSpecItemStateChanged(evt);
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

        btSuppr.setText("Supprimer");
        btSuppr.setEnabled(false);
        btSuppr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSupprActionPerformed(evt);
            }
        });

        btValider.setText("Valider");
        btValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btValiderActionPerformed(evt);
            }
        });

        lbName.setText("Libellé de la spécification");

        lbTypeAssoc.setText("Types de composants");

        listTypeCompo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listTypeCompoItemStateChanged(evt);
            }
        });

        listTypeCompoUsed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listTypeCompoUsedItemStateChanged(evt);
            }
        });

        lbTypeUsed.setText("Utilisés");

        btAddNewType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capitao/techmarket/view/image/green_globe_right_arrow_559_rs.jpg"))); // NOI18N
        btAddNewType.setEnabled(false);
        btAddNewType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddNewTypeActionPerformed(evt);
            }
        });

        btRemoveType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capitao/techmarket/view/image/green_globe_left_arrow_558_rs.jpg"))); // NOI18N
        btRemoveType.setEnabled(false);
        btRemoveType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveTypeActionPerformed(evt);
            }
        });

        listValeurSpec.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listValeurSpecItemStateChanged(evt);
            }
        });

        lbVal.setText("Valeurs");

        lbValNew.setText("Entrer une nouvelle valeur");

        btAddNewVal.setText("Ajouter une valeur");
        btAddNewVal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddNewValActionPerformed(evt);
            }
        });

        btReiniVal.setText("Réinitialiser les valeurs");
        btReiniVal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReiniValActionPerformed(evt);
            }
        });

        jbtValUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capitao/techmarket/view/image/green_globe_up_arrow_560.jpg"))); // NOI18N
        jbtValUp.setEnabled(false);
        jbtValUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtValUpActionPerformed(evt);
            }
        });

        jbtValDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capitao/techmarket/view/image/green_globe_down_arrow_557.jpg"))); // NOI18N
        jbtValDown.setEnabled(false);
        jbtValDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtValDownActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panAddModLayout = new javax.swing.GroupLayout(panAddMod);
        panAddMod.setLayout(panAddModLayout);
        panAddModLayout.setHorizontalGroup(
            panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAddModLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                        .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panAddModLayout.createSequentialGroup()
                                .addComponent(listTypeCompo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btAddNewType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btRemoveType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panAddModLayout.createSequentialGroup()
                                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tfNewVal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbValNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btAddNewVal)
                                        .addComponent(btReiniVal))
                                    .addComponent(lbTypeAssoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(listTypeCompoUsed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTypeUsed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbVal, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(listValeurSpec, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtValUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtValDown, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btValider)
                .addGap(128, 128, 128))
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
                    .addComponent(lbVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbValNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panAddModLayout.createSequentialGroup()
                        .addComponent(tfNewVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(btAddNewVal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btReiniVal))
                    .addComponent(listValeurSpec, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                        .addComponent(jbtValUp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtValDown, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panAddModLayout.createSequentialGroup()
                        .addComponent(lbTypeUsed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(listTypeCompoUsed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panAddModLayout.createSequentialGroup()
                        .addComponent(lbTypeAssoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panAddModLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panAddModLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(listTypeCompo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddModLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btAddNewType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btRemoveType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btValider)
                .addContainerGap())
        );

        lbListCompoType.setText("Liste des spécifications");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btMod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSuppr))
                    .addComponent(listSpec, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbListCompoType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panAddMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panAddMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbListCompoType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(listSpec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSuppr)
                            .addComponent(btMod)
                            .addComponent(btAdd))
                        .addGap(10, 10, 10))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        add();
    }//GEN-LAST:event_btAddActionPerformed

    private void btModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModActionPerformed
        mod();
    }//GEN-LAST:event_btModActionPerformed

    private void btValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btValiderActionPerformed
        resize(false);
        valider();
    }//GEN-LAST:event_btValiderActionPerformed

    private void menuFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFermerActionPerformed
        this.dispose();
    }//GEN-LAST:event_menuFermerActionPerformed

    private void jbtValUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtValUpActionPerformed
        upDown(-1);
    }//GEN-LAST:event_jbtValUpActionPerformed

    private void men_help_aproposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_men_help_aproposActionPerformed
        TM_APropos.getInstance().setVisible(true);
    }//GEN-LAST:event_men_help_aproposActionPerformed

    private void btAddNewTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddNewTypeActionPerformed
        listTypeCompoUsed.add(listTypeCompo.getSelectedItem());
        listTypeCompo.remove(listTypeCompo.getSelectedItem());
        selectActu();
    }//GEN-LAST:event_btAddNewTypeActionPerformed

    private void btRemoveTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveTypeActionPerformed
        listTypeCompo.add(listTypeCompoUsed.getSelectedItem());
        listTypeCompoUsed.remove(listTypeCompoUsed.getSelectedItem());
        selectActu();
    }//GEN-LAST:event_btRemoveTypeActionPerformed

    private void listTypeCompoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listTypeCompoItemStateChanged
        btAddNewType.setEnabled(listTypeCompo.getSelectedIndex()>=0);
    }//GEN-LAST:event_listTypeCompoItemStateChanged

    private void listTypeCompoUsedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listTypeCompoUsedItemStateChanged
        btRemoveType.setEnabled(listTypeCompoUsed.getSelectedIndex()>=0);
    }//GEN-LAST:event_listTypeCompoUsedItemStateChanged

    private void btAddNewValActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddNewValActionPerformed
        listValeurSpec.add(tfNewVal.getText().trim());
    }//GEN-LAST:event_btAddNewValActionPerformed

    private void btReiniValActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReiniValActionPerformed
        listValeurSpec.removeAll();
        jbtValUp.setEnabled(false);
        jbtValDown.setEnabled(false);
    }//GEN-LAST:event_btReiniValActionPerformed

    private void jbtValDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtValDownActionPerformed
        upDown(1);
    }//GEN-LAST:event_jbtValDownActionPerformed

    private void listValeurSpecItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listValeurSpecItemStateChanged
        btvalStat ();
    }//GEN-LAST:event_listValeurSpecItemStateChanged

    private void btSupprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSupprActionPerformed
        suppr(spcs.get(listSpec.getSelectedIndex()));
    }//GEN-LAST:event_btSupprActionPerformed

    private void listSpecItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listSpecItemStateChanged
        btMod.setEnabled(true);
        btSuppr.setEnabled(true);
    }//GEN-LAST:event_listSpecItemStateChanged
    // Suppression d'une spécification de la liste
    public void suppr(TM_Specification s){
        SpecificationDao.delete(s);
        spcs.remove(s);
        initList();
    }
    // Vérification des bouton
    public void btvalStat (){
        jbtValUp.setEnabled((listValeurSpec.getSelectedIndex() > 0));
        jbtValDown.setEnabled((listValeurSpec.getSelectedIndex() < listValeurSpec.getItemCount()-1));
    }
    
    // Déplace les valeurs de spécifications dans la liste
    public void upDown (int val){                                     
        String[] valList = listValeurSpec.getItems();
        int aMove = listValeurSpec.getSelectedIndex();
        listValeurSpec.removeAll();
        for (int i = 0; i < valList.length; i++){
            if (i == aMove+val){
                String temp = valList[aMove+val];
                valList[aMove+val] = valList[aMove];
                valList[aMove] = temp;
            }
        }
        for (int i = 0; i < valList.length;i++){
            listValeurSpec.add(valList[i]);
        }
        listValeurSpec.select(aMove+val);
        btvalStat();
    }
    
    // Détermine le type d'action a effectué et vérifie si les champs son remplie
    // puis appel le DAO pour l'ajout ou la modification des données
    public void resize(boolean minmax){
        int width = 0;
        if (minmax){
            width = 675;
        }else{
            width = 295;
        }    
        Dimension dt = new Dimension(width, 527);
        this.setPreferredSize(dt);
        this.pack();
        this.validate();
    }        
        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(TM_CRUD_Specification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TM_CRUD_Specification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TM_CRUD_Specification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TM_CRUD_Specification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TM_CRUD_Specification().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btAddNewType;
    private javax.swing.JButton btAddNewVal;
    private javax.swing.JButton btMod;
    private javax.swing.JButton btReiniVal;
    private javax.swing.JButton btRemoveType;
    private javax.swing.JButton btSuppr;
    private javax.swing.JButton btValider;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JButton jbtValDown;
    private javax.swing.JButton jbtValUp;
    private java.awt.Label lbListCompoType;
    private java.awt.Label lbName;
    private java.awt.Label lbTypeAssoc;
    private java.awt.Label lbTypeUsed;
    private java.awt.Label lbVal;
    private java.awt.Label lbValNew;
    private java.awt.List listSpec;
    private java.awt.List listTypeCompo;
    private java.awt.List listTypeCompoUsed;
    private java.awt.List listValeurSpec;
    private javax.swing.JMenu men_help;
    private javax.swing.JMenuItem men_help_apropos;
    private javax.swing.JMenuItem menuFermer;
    private javax.swing.JMenu menuMain;
    private javax.swing.JPanel panAddMod;
    private java.awt.TextField tfName;
    private java.awt.TextField tfNewVal;
    // End of variables declaration//GEN-END:variables
}




 /*       if (tfNewVal.getText().length() > 0){
            String newVal = tfNewVal.getText();
            int valNum = Integer.parseInt(newVal);
            if (valNum > -1){
                if (typeIsInt==false){
                    JOptionPane.showMessageDialog(this, "Vous avez choisi une valeur numérique, "+
                        "vous ne pouvez plus entrer de valeur texte Ã  moins de réinitialiser les valeurs",
                        "Attention : Valeur numérique choisie", JOptionPane.WARNING_MESSAGE);
                    typeIsInt = true;
                }
                return;
            }else {
                if (valNum <= -1)
                JOptionPane.showMessageDialog(this, "Les valeures numériques doivent Ãªtre positives",
                    "Erreur : Valeur négative", JOptionPane.ERROR_MESSAGE);
            }
            listValeurSpec.add(tfNewVal.getText());
        }else{
            JOptionPane.showMessageDialog(this, "Veuillez remplir le champs avec une nouvelle valeur",
                "Erreur : Aucune valeur remplie", JOptionPane.ERROR_MESSAGE);
        }*/