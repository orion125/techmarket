/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.techmarket.domaine;

import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.JOptionPane;
/**
 *
 * @author jonathan.capitao
 */
public class TM_Composant extends AbstractTableModel {
    private int id ;
    private String nom;
    private TM_Marque marque;
    private TM_ComposantType compoType;
    private double prix;
    private ArrayList<TM_SpecificationAsValue> specifications = new ArrayList<>();
    private final ArrayList<TableModelListener> compoTabModeListener = new ArrayList<>();


    public TM_Composant() {
        this("", new TM_ComposantType(0, ""), new TM_Marque(0, "", new ArrayList()), 0.0);
    }
    
    public TM_Composant(String nom, TM_ComposantType compoType){
        this(nom, compoType, new TM_Marque(0, "", new ArrayList()), 0.0);
    }     
    

    public TM_Composant(String nom, TM_ComposantType compoType, TM_Marque marque, double prix){
        this.nom = nom;
        this.compoType = compoType;  
        this.marque = marque; 
        this.prix = prix;
    }
    
    /**
     * @return the compoType
     */
    public TM_ComposantType getCompoType() {
        return compoType;
    }

    /**
     * @param compoType the compoType to set
     */
    public void setCompoType(TM_ComposantType compoType) {
        this.compoType = compoType;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the marqueId
     */
    public TM_Marque getMarque() {
        return marque;
    }

    /**
     * @param marqueId the marqueId to set
     */
    public void setMarque(TM_Marque marque) {
        this.marque = marque;
    }
    
  
    public ArrayList<TM_SpecificationAsValue> getSpecifications() {
        return specifications;
    }
    
    public TM_SpecificationAsValue getSpecification(int i) {
        return specifications.get(i);
    }
    
    public void addSpecification(TM_SpecificationAsValue spec) {
        specifications.add(spec);
        fireTableRowsInserted(specifications.size(), specifications.size());
    }
    
    public void removeSpecification(TM_SpecificationAsValue spec){
        specifications.remove(spec);
    }
    public void removeSpecification(int i){
        specifications.remove(i);
        fireTableRowsDeleted(specifications.size(), specifications.size());
    }

    private void setSpecifications(ArrayList<TM_SpecificationAsValue> specifications) {
        this.specifications = specifications;
    }  
    
    @Override
    public int getRowCount() {
        return specifications.size();
    }

    @Override
    public int getColumnCount() {
       return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String returnName;
       
        switch (columnIndex){
            case 0 : returnName = "Spécification" ;break;
            case 1 : returnName = "Valeur"; break;
            default : returnName = "";
        }
        return returnName;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class returnClass;
        switch (columnIndex){
            case 0 : returnClass = TM_SpecificationAsValue.class ;break;
            case 1 : returnClass = String.class ; break;
            default : returnClass = null;
        }
        return returnClass;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0 : return false;
            case 1 : return true;
            default : return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      switch(columnIndex){
            case 0 : return ((TM_SpecificationAsValue)specifications.get(rowIndex));
            case 1 : return ((TM_SpecificationAsValue)specifications.get(rowIndex)).getValue();
            default : return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        boolean isPossible;
        switch(columnIndex){
            case 0 : return;
            case 1 : 
                TM_SpecificationAsValue specAsValue = (TM_SpecificationAsValue) getValueAt(rowIndex, 0);
                if (specAsValue.getSpec().hasValPos(aValue.toString())){
                    specAsValue.setValue(aValue.toString());
                }else{
                    JOptionPane.showMessageDialog(null, 
                            "Veuillez remplir le champs avec une valeur contenue dans {" +
                            specAsValue.getSpec().printValPos()+"]",
                    "Erreur : aucune valeur remplie", JOptionPane.ERROR_MESSAGE); 
                }
                return;
            default : return;
        }
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
