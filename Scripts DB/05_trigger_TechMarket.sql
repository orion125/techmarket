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
  AFTER UPDATE OF cha_etat  
  ON tm_changestockenatt
  FOR EACH ROW
  WHEN (new.cha_etat = 0)
DECLARE
  qteto_update NUMBER;
  v_stockphy NUMBER;
BEGIN
  SELECT cos_nbstockphysique into v_stockphy 
  FROM tm_compoasstock 
  WHERE cos_cmp_id = :new.cha_cos_cmp_id;
  qteto_update := :new.cha_qte + v_stockphy;
  UPDATE tm_compoasstock SET cos_nbstockphysique = qtetoupdate 
  WHERE cos_cmp_id = :new.cha_cos_cmp_id;
END;
/

CREATE TRIGGER tr_com_changeStockAtt 
  AFTER INSERT
  ON tm_lignecommande
  FOR EACH ROW
DECLARE
  EXCEPTION no_cmp_found;
BEGIN
  IF (:NEW.lco_cmp_id > 0) THEN
    INSERT INTO vw_changstockatt (cha_id, cha_commentaire, cha_qte, cha_etat,cha_cmp_id) VALUES 
    (cha_id_seq.nextval,'Commande n°' || :new.lco_com_id, :new.lco_qte , 1, :new.lco_cmp_id);
  ELSE
    RAISE no_cmp_found;
  END IF;
EXCEPTION
  WHEN no_cmp_found THEN
    RAISE_APPLICATION_ERROR(-20111, 'Erreur : Un article éronné est attaché à la commande!');
  WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20122, 'Erreur non-supportée pour la commande :' || TO_CHAR(:new.com_id));
END;
/
