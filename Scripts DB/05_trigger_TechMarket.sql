-- --------------------------------------------------------------------------------------------------------------------------------------------------
-- Script : 05_trigger_TechMarket.sql
-- Objet  : Création des triggers BDD TechMarket sur SGBD Oracle en Local (HEGLOCAL)
--
-- Auteur : CAPITAO Jonathan
-- Mise à jour des versions
-- Version  Visa   Date       Commentaires
-- -----  ------  --------    ------------------------------------------------------------------------------------------------------------------------
-- 1.0       GA   21.11.15    Création des triggers
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
--<ScriptOptions statementTerminator=;/>

-- Suppression Triggers -------------------------------------------------------------------------------------------------------------------------------
DROP TRIGGER tr_cha_valider;
DROP TRIGGER tr_com_changeStockAtt;

-- Création des Triggers ------------------------------------------------------------------------------------------------------------------------------ 
CREATE TRIGGER tr_cha_valider 
  AFTER UPDATE OF cha_etat ON tm_changestockenatt
  FOR EACH ROW
DECLARE
   qteto_update number;
BEGIN
  IF (:NEW.cha_etat = 0)
      SELECT cos_nbstockphysique into v_stockphy
      FROM tm_compoasstock
      WHERE cos_cmp_id = :new.cha_cos_cmp_id AND cos_emp_id = :new.cha_cos_emp_id;
      dbms_output.put_line(v_stockphy);  
      qteto_update := :NEW.cha_qte +v_stockphy;
      
      dbms_output.put_line(qteto_update);  
      UPDATE tm_compoasstock SET cos_nbstockphysique = qtetoupdate
      WHERE cos_cmp_id = :new.cha_cos_cmp_id AND cos_emp_id = :new.cha_cos_emp_id;
  END IF;
END;
/

CREATE TRIGGER tr_com_changeStockAtt
  AFTER INSERT ON tm_lignecommande
BEGIN
     IF (:NEW.lco_cmp_id > 0) THEN
		 INSERT INTO tm_changestockenatt (cha_id, cha_commentaire, cha_qte, cha_etat,cmp_id) )VALUES 
		 (cha_id_seq.nextval,'Commande n°' || :NEW.lco_com_id, :NEW.lco_qte , 1, :NEW.lco_cmp_id)
     ELSE
      RAISE no_cmp_found;
        CLOSE cur_get_ligncom;
     END IF;
EXCEPTION
    WHEN no_cmp_found THEN
       RAISE_APPLICATION_ERROR(-20111, 'Erreur : aucun article attaché à la commande..');
    WHEN OTHERS THEN
       RAISE_APPLICATION_ERROR(-20122, 'Erreur non-supportée pour la commande :' || TO_CHAR(:NEW.com_id));
END;
/
