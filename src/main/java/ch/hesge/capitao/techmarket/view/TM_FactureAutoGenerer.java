package ch.hesge.capitao.techmarket.view;

import ch.hesge.capitao.techmarket.outils.FileToStr;
import ch.hesge.capitao.techmarket.domaine.TM_Commande;
import ch.hesge.capitao.techmarket.domaine.TM_LigneCommande;
import ch.hesge.capitao.techmarket.outils.HtmlParser;
import java.awt.print.PrinterException;
import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author jonathan.capitao
 */
public class TM_FactureAutoGenerer extends javax.swing.JFrame {
    
    private static TM_FactureAutoGenerer MyPanWindows = null;
    public static TM_Commande comData;
       
    public static TM_FactureAutoGenerer getInstance(TM_Commande com){
        if (MyPanWindows == null){
            MyPanWindows = new TM_FactureAutoGenerer(com);

        }
        return MyPanWindows;
    }
    /**
     * Creates new form TM_FactureAutoGenerer
     */
    private TM_FactureAutoGenerer(TM_Commande com) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.comData = com;
        genFact();
    }
    // Génère la facture par rapport à la commande.
    private void genFact(){
        // Ouvre le fichier de config et le stock dans data
        String conf = FileToStr.read(new File("").getAbsolutePath() +"/config.cfg");
        StringTokenizer stLigne = new StringTokenizer(conf,";");
        String[] data = new String [3];
        int i = 0;
        while (stLigne.hasMoreTokens()) {
            StringTokenizer stData = new StringTokenizer(stLigne.nextToken(),":");
            while (stData.hasMoreTokens()){
                 stData.nextToken();
                 data[i] = stData.nextToken();
            }
            i++;
        }
        // Prépare un kit CSS pour le jEditorPane
        HTMLEditorKit kit = new HTMLEditorKit();
        jEdpan_facture.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".art table {border-collapse: collapse;}") ;
        styleSheet.addRule(".art td{border : 1px solid #black;}");
        Document doc = kit.createDefaultDocument();
        jEdpan_facture.setDocument(doc);
        
        double[] prix = new double[3];          
        prix[0] = comData.getValTotCommande();
        prix[1] = comData.getValTva()-comData.getValTotCommande();
        prix[2] = comData.getValTva();
        HtmlParser.setPrix(prix);
        // Génère la facture en html (utile pour les allignements entre autre.
        String htmlFact = HtmlParser.toHtml(data);
        jEdpan_facture.setText(htmlFact);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtPrint = new javax.swing.JButton();
        jbtClose = new javax.swing.JButton();
        jScrPanMain = new javax.swing.JScrollPane();
        jEdpan_facture = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Résumé de la facture");

        jbtPrint.setText("Imprimer");
        jbtPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPrintActionPerformed(evt);
            }
        });

        jbtClose.setText("Fermer");
        jbtClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCloseActionPerformed(evt);
            }
        });

        jEdpan_facture.setContentType("text/html"); // NOI18N
        jScrPanMain.setViewportView(jEdpan_facture);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addComponent(jbtPrint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtClose)
                .addGap(252, 252, 252))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrPanMain)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrPanMain, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtPrint)
                    .addComponent(jbtClose))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbtCloseActionPerformed

    private void jbtPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPrintActionPerformed
        try {
            jEdpan_facture.print();
        } catch (PrinterException e) {
            System.err.println("Erreur d'impression de la facture"+e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbtPrintActionPerformed

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
            java.util.logging.Logger.getLogger(TM_FactureAutoGenerer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TM_FactureAutoGenerer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TM_FactureAutoGenerer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TM_FactureAutoGenerer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TM_FactureAutoGenerer(null).setVisible(true);
            }
        });
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEdpan_facture;
    private javax.swing.JScrollPane jScrPanMain;
    private javax.swing.JButton jbtClose;
    private javax.swing.JButton jbtPrint;
    // End of variables declaration//GEN-END:variables
}
