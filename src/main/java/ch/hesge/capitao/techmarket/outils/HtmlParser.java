package ch.hesge.capitao.techmarket.outils;

import ch.hesge.capitao.techmarket.domaine.TM_LigneCommande;
import static ch.hesge.capitao.techmarket.view.TM_FactureAutoGenerer.comData;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jonathan.capitao
 */
public class HtmlParser {
    
    // Prépare un format de date
    static SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy"); 
    static Date dateActu = new Date(); 
    // Prépare un format de money
    static NumberFormat money = NumberFormat.getCurrencyInstance(); 
    
    private static double[] prix ;
    
    public static void setPrix(double[] prices){prix = prices;}
    
    public static String getFormatedEnd(){
        String endhtml = "<tr> "+"<td width=\"280px\"></td>"
                + "<td class=\"art\" width=\"280px\" align=\"right\" >"
                + "Total  : "+money.format(prix[0])+"</td></tr>";
        endhtml += "<tr> "+"<td width=\"280px\"></td>"
                + "<td class=\"art\" width=\"280px\" align=\"right\" >"
                + "TVA(8%) : "+money.format(prix[1])+"</td></tr>";
        endhtml += "<tr> "+"<td width=\"280px\"></td>"
                + "<td class=\"art\" width=\"280px\" align=\"right\" >"
                + "Total NET : "+money.format(prix[2])+"</td>"+"</tr>";
        return endhtml;
    }
    
    public static String getTitle(){
        return "Facture de la commande n°"+comData.getId();
    }
    
    public static String getHeader(String[] data){
        return 
        "<table>"
            + "<tr>"
                + "<td width=\"280px\">"+comData.getCli().getNom()+" "+comData.getCli().getPrenom()+"</td>"
                +"<td width=\"280px\" align=\"right\" >"+data[0]+"</td>"
            + "</tr>"
            + "<tr><td width=\"280px\">"+comData.getCli().getAddress()+"</td>"
                + "<td width=\"280px\" align=\"right\">"+data[1]+" "+data[2]+"</td>"
            + "<tr></tr><tr>Genève, le "+dt.format(dateActu)+"</tr><tr></tr>"
      + "</table><br>";
    }
    
    public static String getCommandContent(){
        String htmlCommandContent = "<table class=\"art\" >"
                + "<tr><th width=\"280px\">Article</th><th width=\"280px\">Prix</th>";
        // Récupère le contenu de la commande et l'écrit en html.
        for (TM_LigneCommande lc : comData.getaListComposantCommandes()){
            htmlCommandContent += "<tr>"
                    + "<td width=\"280px\" >" +lc.getCompo().getNom()+" x"+lc.getQte()+"</td>"
                    + "<td width=\"280px\" align=\"right\">"+ money.format(lc.getTot())+"</td>"
                 + "</tr>";
        }
        return htmlCommandContent;
    }
    
    public static String toHtml(String[] data){
        String html = getHeader(data);
        html += "<table><tr>"+getTitle()+"</tr>"
                + "<tr></tr></table>";
        html += getCommandContent();
        // Calcule des totaux & TVA
        html += getFormatedEnd();
        
        return html;
    }
}
