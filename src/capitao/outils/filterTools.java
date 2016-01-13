package capitao.outils;

import java.awt.event.*;

/**
 *
 * @author jonathan.capitao
 */
public class filterTools {
    // Prépare un filtre pour champ texte acceptant les nombres positif et négatif
    public static void filterPositiveEtNegativeInt(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar() ;

        if (!   ((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)
           ||  (c== KeyEvent.VK_ENTER)      || (c == KeyEvent.VK_TAB)
           ||  (Character.isDigit(c)) || c==KeyEvent.VK_MINUS))
        {
            evt.consume() ;
        } else {
            if ((((java.awt.TextField)evt.getSource()).getCaretPosition() > 0) &&
                c==KeyEvent.VK_MINUS){
                evt.consume();
            }
        }     
    } // filterPositiveEtNegativeInt
  
    // Prépare un filtre pour champ texte acceptant les nombres positif uniquement
    public static void filterPositiveInt(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar() ;

        if (!   ((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)
           ||  (c== KeyEvent.VK_ENTER)      || (c == KeyEvent.VK_TAB)
           ||  (Character.isDigit(c))))
        {
            evt.consume() ;
        }   
    } // filterPositiveInt
    
    // Prépare un filtre pour champ texte acceptant les chiffres et le plus comme
    // premier caractère comme un numéro de téléphone au format internationnal
    public static void filterTel(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar() ;

        if (!   ((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)
           ||  (c== KeyEvent.VK_ENTER)      || (c == KeyEvent.VK_TAB)
           ||  (Character.isDigit(c)) || c=='+'))
        {
            evt.consume() ;
        } else {
            if ((((java.awt.TextField)evt.getSource()).getCaretPosition() > 0) &&
                c=='+'){
                evt.consume();
            }
        }   
    }  // filterTel  
} // filterTools
