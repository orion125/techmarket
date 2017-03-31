-- --------------------------------------------------------------------------------------------------------------------------------------------------
-- Script: 06_vues_TechMarket.sql 
-- Objet : Création des vues schéma de la base TechMarket
-- Objectif : créer des vues et tester l'interrogation et la mise-à-jour à travers ces vues. 
--
-- Auteur : CAPITAO Jonathan
-- Mise à jour des versions
-- Version  Visa   Date       Commentaires
-- -----  ------  --------    ------------------------------------------------------------------------------------------------------------------------
-- 1.0       GA   25.11.15    Création des vues
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
--<ScriptOptions statementTerminator=";"/>

-- Création des VUES ---------------------------------------------------------------------------------------------------------------------------------

-- Vue d'affichage des fluctuations de stocks

CREATE OR REPLACE VIEW vw_changstockatt AS
       SELECT cha_id, cha_comment,cha_etat,cha_qte,cha_datechange, 
				cha_cos_cmp_id
       FROM tm_changestockenatt ;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_changstockatt TO TechMarket_user;

-- Vue d'affichage des clients

CREATE OR REPLACE VIEW vw_client AS
       SELECT cli_id,cli_nom,cli_prenom, cli_telephone, cli_address,cli_mail
       FROM tm_client ;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_client TO TechMarket_user;

-- Vue d'affichage des commandes
CREATE OR REPLACE VIEW vw_commande AS
	SELECT com_id, com_cli_id
	FROM tm_commande;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_commande TO TechMarket_user;

-- Vue d'affichage des relations entre specification et des composants (on lit une valeur à un composant)
CREATE OR REPLACE VIEW vw_valeur_spec_as_compo AS
	SELECT cov_spv_id, cov_cmp_id
	FROM tm_compoasspecvalue;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_valeur_spec_as_compo TO TechMarket_user;

-- Vue d'affichage du stock
CREATE OR REPLACE VIEW vw_stock AS
	SELECT cos_nbstockvirtuel, cos_nbstockphysique, cos_emp_id,cos_cmp_id
	FROM tm_compoasstock;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_stock TO TechMarket_user;

-- Vue d'affichage des composants
CREATE OR REPLACE VIEW vw_composant AS
	SELECT cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id
	FROM tm_composant;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_composant TO TechMarket_user;

-- Vue d'affichage des categories de composants
CREATE OR REPLACE VIEW vw_categorie_compo AS
	SELECT cot_id, cot_nom
	FROM tm_composanttype;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_categorie_compo TO TechMarket_user;

-- Vue d'affichage des categories concernant chaque marque
CREATE OR REPLACE VIEW vw_marque_as_categorie AS
	SELECT ctm_mar_id, ctm_cot_id
	FROM tm_compotypeasmarque;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_marque_as_categorie TO TechMarket_user;

-- Vue d'affichage des categories concernant chaque specification
CREATE OR REPLACE VIEW vw_spec_as_categorie AS
	SELECT cts_spc_id, cts_cot_id
	FROM tm_compotypeasspec;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_spec_as_categorie TO TechMarket_user;

-- Vue d'affichage des emplacments dans le stocks (on suppose le hangar ou l'entrepôt ici)
CREATE OR REPLACE VIEW vw_emplacement AS
	SELECT emp_id, emp_nom
	FROM tm_emplacement;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_emplacement TO TechMarket_user;

-- Vue d'affichage des composants concernants une commande (et la quantité à laquel il ont été commandé)
CREATE OR REPLACE VIEW vw_ligne_commande AS
	SELECT lco_cmp_id, lco_com_id, lco_qte
	FROM tm_lignecommande;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_ligne_commande TO TechMarket_user;

-- Vue d'affichage des utilisateurs ayant droit de se connecter sur la partie manager de l'application
CREATE OR REPLACE VIEW vw_manager AS
	SELECT man_id, man_username, man_password
	FROM tm_manager;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_manager TO TechMarket_user;

-- Vue d'affichage des marques
CREATE OR REPLACE VIEW vw_marque AS
	SELECT mar_id, mar_nom
	FROM tm_marque;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_marque TO TechMarket_user;

-- Vue d'affichage des valeurs possibles concernant les specifications
CREATE OR REPLACE VIEW vw_valeur_spec AS
	SELECT spv_id, spv_spc_id, spv_value
	FROM tm_specasvalue;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_valeur_spec TO TechMarket_user;

-- Vue d'affichage des specifications
CREATE OR REPLACE VIEW vw_spec AS
	SELECT spc_id, spc_nom
	FROM tm_specification;
GRANT SELECT, INSERT, UPDATE, DELETE ON vw_spec TO TechMarket_user;


-- Tester l'interrogation des vues  ------------------------------------------------------------------------------------------------------------------

-- SELECT * FROM vw_changstockatt;
-- SELECT * FROM vw_categorie_compo;
-- SELECT * FROM vw_client;
-- SELECT * FROM vw_commande;
-- SELECT * FROM vw_composant;
-- SELECT * FROM vw_emplacement;
-- SELECT * FROM vw_ligne_commande;
-- SELECT * FROM vw_manager;
-- SELECT * FROM vw_marque;
-- SELECT * FROM vw_marque_as_categorie;
-- SELECT * FROM vw_spec;
-- SELECT * FROM vw_spec_as_categorie;
-- SELECT * FROM vw_stock;
-- SELECT * FROM vw_valeur_spec;
-- SELECT * FROM vw_valeur_spec_as_compo;
  
