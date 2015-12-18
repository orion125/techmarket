-- --------------------------------------------------------------------------------------------------------------------------------------------------
-- Script : 03_create_TechMarket.sql
-- Objet  : Création des tables et BDD TechMarket sur SGBD Oracle en Local (HEGLOCAL)
--
-- Auteur : CAPITAO Jonathan
-- Mise à jour des versions
-- Version  Visa   Date       Commentaires
-- -----  ------  --------    ------------------------------------------------------------------------------------------------------------------------
-- 1.0	     GA   21.11.15    Création des Tables
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
--<ScriptOptions statementTerminator=;/>


-- Néttoyage -----------------------------------------------------------------------------------------------------------------------------------------

-- Suppression tables
DROP TABLE tm_changestockenatt CASCADE CONSTRAINTS ;
DROP TABLE tm_client CASCADE CONSTRAINTS ;
DROP TABLE tm_commande CASCADE CONSTRAINTS ;
DROP TABLE tm_compoasspecvalue CASCADE CONSTRAINTS ;
DROP TABLE tm_composant CASCADE CONSTRAINTS ;
DROP TABLE tm_compoasstock CASCADE CONSTRAINTS ;
DROP TABLE tm_composanttype CASCADE CONSTRAINTS ;
DROP TABLE tm_compotypeasmarque CASCADE CONSTRAINTS ;
DROP TABLE tm_compotypeasspec CASCADE CONSTRAINTS ;
DROP TABLE tm_emplacement CASCADE CONSTRAINTS ;
DROP TABLE tm_lignecommande CASCADE CONSTRAINTS ;
DROP TABLE tm_manager CASCADE CONSTRAINTS ;
DROP TABLE tm_marque CASCADE CONSTRAINTS ;
DROP TABLE tm_specification CASCADE CONSTRAINTS ;
DROP TABLE tm_specasvalue CASCADE CONSTRAINTS ;

-- Création des Tables  ------------------------------------------------------------------------------------------------------------------------------	
	
CREATE TABLE tm_changestockenatt (
		cha_comment VARCHAR2(500),
		cha_etat NUMBER(1 , 0) NOT NULL,
		cha_qte NUMBER(3 , 0) NOT NULL,
		cha_id NUMBER(9 , 0) NOT NULL,
		cha_datechange DATE DEFAULT SYSDATE,
		cha_cos_cmp_id NUMBER(6 , 0) NOT NULL,
		cha_cos_emp_id NUMBER(3 , 0)
	);

CREATE TABLE tm_client (
		cli_nom VARCHAR2(125),
		cli_prenom VARCHAR2(50),
		cli_telephone VARCHAR2(50),
		cli_address VARCHAR2(200),
		cli_mail VARCHAR2(125),
		cli_id NUMBER(5 , 0) NOT NULL
	);

CREATE TABLE tm_commande (
		com_id NUMBER(8 , 0) NOT NULL,
		com_cli_id NUMBER(5 , 0) NOT NULL
	);


CREATE TABLE tm_compoasspecvalue (
		cov_spv_id NUMBER(7 , 0) NOT NULL,
		cov_cmp_id NUMBER(6 , 0) NOT NULL
	);

CREATE TABLE tm_compoasstock (
		cos_nbstockvirtuel NUMBER(4 , 0) DEFAULT 0 NOT NULL,
		cos_nbstockphysique NUMBER(4 , 0) DEFAULT 0 NOT NULL,
		cos_emp_id NUMBER(3 , 0) NOT NULL,
		cos_cmp_id NUMERIC(6 , 0) NOT NULL
	);

CREATE TABLE tm_composant (
		cmp_id NUMBER(6 , 0) NOT NULL,
		cmp_nom VARCHAR2(200),
		cmp_prix NUMBER(7 , 2),
		cmp_mar_id NUMBER(3 , 0) NOT NULL,
		cmp_cot_id NUMBER(2 , 0) NOT NULL
	);

CREATE TABLE tm_composanttype (
		cot_id NUMBER(2 , 0) NOT NULL,
		cot_nom VARCHAR2(125)
	);

CREATE TABLE tm_compotypeasmarque (
		ctm_mar_id NUMBER(3 , 0) NOT NULL,
		ctm_cot_id NUMBER(2 , 0) NOT NULL
	);

CREATE TABLE tm_compotypeasspec (
		cts_cot_id NUMBER(2 , 0) NOT NULL,
		cts_spc_id NUMBER(6 , 0) NOT NULL
	);

CREATE TABLE tm_emplacement (
		emp_id NUMBER(3 , 0) NOT NULL,
		emp_nom VARCHAR2(200)
	);

CREATE TABLE tm_lignecommande (
		lco_cmp_id NUMBER(6 , 0) NOT NULL,
		lco_com_id NUMBER(8 , 0) NOT NULL,
		lco_qte NUMBER(3 , 0) DEFAULT 1 NOT NULL
	);

CREATE TABLE tm_manager (
		man_id NUMBER(2 , 0) NOT NULL,
		man_username VARCHAR2(50),
		man_password VARCHAR2(50)
	);

CREATE TABLE tm_marque (
		mar_id NUMBER(3 , 0) NOT NULL,
		mar_nom VARCHAR2(125)
	);

CREATE TABLE tm_specasvalue (
		spv_id NUMBER(7 , 0) NOT NULL,
		spv_spc_id NUMBER(6 , 0) NOT NULL,
		spv_value VARCHAR2(50)
	);

CREATE TABLE tm_specification (
		spc_nom VARCHAR2(125),
		spc_id NUMBER(6 , 0) NOT NULL
	);

-- Création des Contraintes  ------------------------------------------------------------------------------------------------------------------------------	
ALTER TABLE tm_changestockenatt ADD CONSTRAINT tm_changestockenatt_pk PRIMARY KEY(cha_id);
ALTER TABLE tm_client ADD CONSTRAINT tm_client_pk PRIMARY KEY (cli_id);
ALTER TABLE tm_commande ADD CONSTRAINT tm_commande_pk PRIMARY KEY (com_id);
ALTER TABLE tm_compoasspecvalue ADD CONSTRAINT tm_comp_as_spec_value_pk PRIMARY KEY (cov_spv_id, cov_cmp_id);
ALTER TABLE tm_compoasstock ADD CONSTRAINT tm_composantasstock_pk PRIMARY KEY (cos_cmp_id, cos_emp_id);
ALTER TABLE tm_composant ADD CONSTRAINT tm_composant_pk PRIMARY KEY (cmp_id);
ALTER TABLE tm_composanttype ADD CONSTRAINT tm_composanttype_pk PRIMARY KEY (cot_id);
ALTER TABLE tm_compotypeasmarque ADD CONSTRAINT tm_compotype_as_marque_pk PRIMARY KEY (ctm_mar_id, ctm_cot_id);
ALTER TABLE tm_compotypeasspec ADD CONSTRAINT tm_compotype_as_spec_pk PRIMARY KEY (cts_cot_id, cts_spc_id);
ALTER TABLE tm_emplacement ADD CONSTRAINT tm_emplacement_pk PRIMARY KEY (emp_id);
ALTER TABLE tm_lignecommande ADD CONSTRAINT tm_ligne_commande_pk PRIMARY KEY (lco_cmp_id, lco_com_id);
ALTER TABLE tm_manager ADD CONSTRAINT tm_manager_pk PRIMARY KEY (man_id);
ALTER TABLE tm_marque ADD CONSTRAINT tm_marque_pk PRIMARY KEY (mar_id);
ALTER TABLE tm_specasvalue ADD CONSTRAINT tm_specificationasvalue_pk PRIMARY KEY (spv_id);
ALTER TABLE tm_specification ADD CONSTRAINT tm_specification_pk PRIMARY KEY (spc_id);
ALTER TABLE tm_changestockenatt ADD CONSTRAINT tm_changenatt_compostock_FK FOREIGN KEY (cha_cos_cmp_id, cha_cos_emp_id)
	REFERENCES tm_compoasstock (cos_cmp_id,cos_emp_id) ON DELETE CASCADE;
ALTER TABLE tm_commande ADD CONSTRAINT tm_commande_tm_client_FK FOREIGN KEY (com_cli_id)
	REFERENCES tm_client (cli_id) ON DELETE CASCADE;
ALTER TABLE tm_compoasspecvalue ADD CONSTRAINT tm_compasspecvalue_compo_FK FOREIGN KEY (cov_cmp_id)
	REFERENCES tm_composant (cmp_id) ON DELETE CASCADE;
ALTER TABLE tm_compoasspecvalue ADD CONSTRAINT tm_compspecvalue_specvalue_FK FOREIGN KEY (cov_spv_id)
	REFERENCES tm_specasvalue (spv_id)ON DELETE CASCADE;
ALTER TABLE tm_compoasstock ADD CONSTRAINT tm_compoasstock_compo_FK FOREIGN KEY (cos_cmp_id)
	REFERENCES tm_composant (cmp_id) ON DELETE CASCADE;
ALTER TABLE tm_compoasstock ADD CONSTRAINT tm_compstock_tm_emplac_FK FOREIGN KEY (cos_emp_id)
	REFERENCES tm_emplacement (emp_id) ON DELETE CASCADE;
ALTER TABLE tm_composant ADD CONSTRAINT tm_compo_tm_compotype_FK FOREIGN KEY (cmp_cot_id)
	REFERENCES tm_composanttype (cot_id) ON DELETE CASCADE;
ALTER TABLE tm_composant ADD CONSTRAINT tm_compo_tm_marque_FK FOREIGN KEY (cmp_mar_id)
	REFERENCES tm_marque (mar_id) ON DELETE CASCADE;
ALTER TABLE tm_compotypeasmarque ADD CONSTRAINT tm_compotypemark_compotype_FK FOREIGN KEY (ctm_cot_id)
	REFERENCES tm_composanttype (cot_id) ON DELETE CASCADE;
ALTER TABLE tm_compotypeasmarque ADD CONSTRAINT tm_compotypemark_tm_marque_FK FOREIGN KEY (ctm_mar_id)
	REFERENCES tm_marque (mar_id) ON DELETE CASCADE;
ALTER TABLE tm_compotypeasspec ADD CONSTRAINT tm_compotypespec_compotype_FK FOREIGN KEY (cts_cot_id)
	REFERENCES tm_composanttype (cot_id) ON DELETE CASCADE;
ALTER TABLE tm_compotypeasspec ADD CONSTRAINT tm_compotypespec_spec_FK FOREIGN KEY (cts_spc_id)
	REFERENCES tm_specification (spc_id) ON DELETE CASCADE;
ALTER TABLE tm_lignecommande ADD CONSTRAINT tm_ligne_commande_commande_FK FOREIGN KEY (lco_com_id)
	REFERENCES tm_commande (com_id) ON DELETE CASCADE;
ALTER TABLE tm_lignecommande ADD CONSTRAINT tm_ligne_commande_tm_compo_FK FOREIGN KEY (lco_cmp_id)
	REFERENCES tm_composant (cmp_id) ON DELETE CASCADE;
ALTER TABLE tm_specasvalue ADD CONSTRAINT tm_specasvalue_spec_FK FOREIGN KEY (spv_spc_id)
	REFERENCES tm_specification (spc_id) ON DELETE CASCADE;
	
	
