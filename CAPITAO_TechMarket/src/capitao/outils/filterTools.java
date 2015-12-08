/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.outils;

import java.awt.event.*;

/**
 *
 * @author jonathan.capitao
 */
public class filterTools {
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
    }
  
    public static void filterPositiveInt(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar() ;

        if (!   ((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)
           ||  (c== KeyEvent.VK_ENTER)      || (c == KeyEvent.VK_TAB)
           ||  (Character.isDigit(c))))
        {
            evt.consume() ;
        }   
    } 
}
