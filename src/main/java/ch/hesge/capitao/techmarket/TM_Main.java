package ch.hesge.capitao.techmarket;

import ch.hesge.capitao.techmarket.view.TM_Login;
import org.joda.time.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jonathan.capitao
 */
public class TM_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Logger log = LoggerFactory.getLogger(TM_Main.class);
        log.info("L'application à démarrer à "+DateTime.now());
        TM_Login.getInstance().setVisible(true);
    } // main
    
} // TM_Main
