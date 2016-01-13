package capitao.techmarket.domaine;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
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
    private double prix = 0.00;
    private ArrayList<TM_SpecificationAsValue> specifications = new ArrayList<>();
    private final ArrayList<TableModelListener> compoTabModeListener = new ArrayList<>();

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    // Pour un composant on ne vérifie que le prix et le nom
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TM_Composant other = (TM_Composant) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        return true;
    } // equals


    public TM_Composant() {
        this("", new TM_ComposantType(0, ""), new TM_Marque(0, "", new ArrayList()), 0.0);
    }
    
    public TM_Composant(String nom, TM_ComposantType compoType){
        this(nom, compoType, new TM_Marque(0, "", new ArrayList()), 0.0);
    }     
    

    public TM_Composant(String nom, TM_ComposantType compoType, TM_Marque marque, double prix){
        this(-1,nom, compoType,marque,prix);
    }
    
    public TM_Composant(int id, String nom, TM_ComposantType compoType, TM_Marque marque, double prix){
        this.id = id;
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TM_Marque getMarque() {
        return marque;
    }

    public void setMarque(TM_Marque marque) {
        this.marque = marque;
    }
    
  
    public ArrayList<TM_SpecificationAsValue> getSpecifications() {
        return specifications;
    }
    
    public TM_SpecificationAsValue getSpecification(int i) {
        return specifications.get(i);
    }
    
    private void setSpecifications(ArrayList<TM_SpecificationAsValue> specifications) {
        this.specifications = specifications;
    }  
    
    @Override
    public String toString() {
        Locale caLoc = new Locale("fr","CH");
        // Crée un format de money pour le prix
        NumberFormat money = NumberFormat.getCurrencyInstance(caLoc); 
        return nom + " " + money.format(prix);
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    // Méthodes utilisés pour le AbstractTableModel
    
    public void addSpecification(TM_SpecificationAsValue spec) {
        specifications.add(spec);
        fireTableRowsInserted(specifications.size(), specifications.size());
    } // addSpecification
    
    public void removeSpecification(TM_SpecificationAsValue spec){
        specifications.remove(spec);
    }//removeSpecification 
    
    public void removeSpecification(int i){
        specifications.remove(i);
        fireTableRowsDeleted(specifications.size(), specifications.size());
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
                // Génère le message d'erreur contenant les valeurs possibles.
                    JOptionPane.showMessageDialog(null, 
                            "Veuillez remplir le champs avec une valeur contenue dans {" +
                            specAsValue.getSpec().printValPos()+"]",
                    "Erreur : aucune valeur remplie", JOptionPane.ERROR_MESSAGE); 
                }
                return;
            default : return;
        }
    }
}
