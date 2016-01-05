-- --------------------------------------------------------------------
-- Script : 04_Insertion_TechMarket.sql
-- Objet  : Générer des données sur la BDD TechMarket sur SGBD Oracle en Local (HEGLOCAL)
--
-- Auteur : CAPITAO Jonathan
-- Mise à jour des versions
-- Version  Visa   Date       Commentaires
-- -----  ------  --------    ------------------------------------------
-- 1.0	     GA   21.11.15    Générer les données (Insert)
-- --------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------
--<ScriptOptions statementTerminator=';'/>


--- Séquences ------------------------------------------------------------------------------------------------------

-- Suppression Séquences ---------------------------------------------------------------------------------------------------
DROP SEQUENCE seq_cha_id;
DROP SEQUENCE seq_cli_id;
DROP SEQUENCE seq_com_id;
DROP SEQUENCE seq_emp_id;
DROP SEQUENCE seq_man_id;
DROP SEQUENCE seq_spc_id;
DROP SEQUENCE seq_spv_id;
DROP SEQUENCE seq_cmp_id;
DROP SEQUENCE seq_mar_id;
DROP SEQUENCE seq_cot_id;

-- Création des Séquences  -------------------------------------------------------------------------------------------------
CREATE SEQUENCE seq_cha_id
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 99999999
  START WITH 1;
CREATE SEQUENCE seq_cli_id
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 99999
  START WITH 1;
CREATE SEQUENCE seq_com_id
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 99999999
  START WITH 1;
CREATE SEQUENCE seq_emp_id
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 999
  START WITH 1;
CREATE SEQUENCE seq_man_id
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 99
  START WITH 1;
CREATE SEQUENCE seq_spc_id
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 999999
  START WITH 1;
CREATE SEQUENCE seq_spv_id
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 9999999
  START WITH 1;
CREATE SEQUENCE seq_cmp_id
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 999999
  START WITH 1; 
CREATE SEQUENCE seq_mar_id
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 999
  START WITH 1;    
CREATE SEQUENCE seq_cot_id
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 999
  START WITH 1; 

COMMIT;  


-- Suppression données -----------------------------------------------------------------------------------------------------
DELETE FROM tm_changestockenatt;
DELETE FROM tm_compoasstock;
DELETE FROM tm_compoasspecvalue;
DELETE FROM tm_specasvalue;
DELETE FROM tm_compotypeasmarque;
DELETE FROM tm_compotypeasspec;
DELETE FROM tm_lignecommande;
DELETE FROM tm_commande;
DELETE FROM tm_composant;
DELETE FROM tm_client;
DELETE FROM tm_emplacement;
DELETE FROM tm_marque;
DELETE FROM tm_specification;
DELETE FROM tm_composanttype;
DELETE FROM tm_manager;
COMMIT;

-- Insertion des données ---------------------------------------------------------------------------------------------------
-- tm_manager

INSERT INTO tm_manager (man_id, man_username, man_password) VALUES (seq_man_id.NEXTVAL,'Admin','Carrer1234');
INSERT INTO tm_manager (man_id, man_username, man_password) VALUES (seq_man_id.NEXTVAL,'VendeurA','Test5151');
INSERT INTO tm_manager (man_id, man_username, man_password) VALUES (seq_man_id.NEXTVAL,'VendeurB','Tarte2Test');
COMMIT;
	
-- tm_composanttype
INSERT INTO tm_composanttype (cot_id, cot_nom) VALUES (seq_cot_id.NEXTVAL,'Carte graphiques');
INSERT INTO tm_composanttype (cot_id, cot_nom) VALUES (seq_cot_id.NEXTVAL,'Carte mères');
INSERT INTO tm_composanttype (cot_id, cot_nom) VALUES (seq_cot_id.NEXTVAL,'Casques');
INSERT INTO tm_composanttype (cot_id, cot_nom) VALUES (seq_cot_id.NEXTVAL,'Claviers');
INSERT INTO tm_composanttype (cot_id, cot_nom) VALUES (seq_cot_id.NEXTVAL,'Disque Dur');
INSERT INTO tm_composanttype (cot_id, cot_nom) VALUES (seq_cot_id.NEXTVAL,'Ecrans');
INSERT INTO tm_composanttype (cot_id, cot_nom) VALUES (seq_cot_id.NEXTVAL,'Mémoire vive (RAM)');
INSERT INTO tm_composanttype (cot_id, cot_nom) VALUES (seq_cot_id.NEXTVAL,'Souris');
INSERT INTO tm_composanttype (cot_id, cot_nom) VALUES (seq_cot_id.NEXTVAL,'Imprimante');
INSERT INTO tm_composanttype (cot_id, cot_nom) VALUES (seq_cot_id.NEXTVAL,'Processeur');
COMMIT;

-- tm_specification
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Fréquence mémoire'); 
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Mémoire RAM'); 
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Type de disque');
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Mémoire vidéo'); 
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Mémoire GHz'); 
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Socket'); 
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Taille mémoire'); 
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Taille écran'); 
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Gamer');
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Son Surround'); 
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Nombre de Coeur'); 
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Processeur'); 
INSERT INTO tm_specification (spc_id, spc_nom) VALUES (seq_spc_id.NEXTVAL,'Résolution'); 
COMMIT;
	
-- tm_marque
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'AMD'); 
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'Intel'); 
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'Toshiba'); 
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'Samsung');
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'Corsair');
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'ASUS');
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'Gainward');
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'Evga');
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'Kingston');
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'Mad Catz');
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'Logitech');
INSERT INTO tm_marque (mar_id,mar_nom) VALUES (seq_mar_id.NEXTVAL,'Razer') ;
COMMIT;
		
-- tm_emplacement
-- C'est le nom (ou la désignation) des différents emplacement du stock 
-- (leur taille peut être variable selon le plan du propriétaire)
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'A1');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'A2');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'A3');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'A4');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'A5');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'B1');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'B2');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'B3');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'C1');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'C2');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'C3');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'C4');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'D1');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'E1');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'E2');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'E3');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'E4');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'E5');
INSERT INTO tm_emplacement (emp_id, emp_nom) VALUES (seq_emp_id.NEXTVAL,'E6');
COMMIT;
	
-- tm_client
-- Client complêtement fictif
INSERT INTO tm_client (cli_id, cli_nom, cli_prenom, cli_telephone, cli_address, cli_mail) VALUES (seq_cli_id.NEXTVAL,'Vega','Carlos','+334012231','16 Rue du Test','Vega.Carlos@gmail.com');
INSERT INTO tm_client (cli_id, cli_nom, cli_prenom, cli_telephone, cli_address, cli_mail) VALUES (seq_cli_id.NEXTVAL,'Cally','Klain','+41223442212','22 Rue de Bel-air','CalKla40@gmail.com');
INSERT INTO tm_client (cli_id, cli_nom, cli_prenom, cli_telephone, cli_address, cli_mail) VALUES (seq_cli_id.NEXTVAL,'Karmin','Diego','+334221231','11 Rue du Test','KarminDiego.du12@hotmail.fr');
INSERT INTO tm_client (cli_id, cli_nom, cli_prenom, cli_telephone, cli_address, cli_mail) VALUES (seq_cli_id.NEXTVAL,'Dalas','Jean-Luc','+41223115533','12 Rue des Bastion','Dalas.Jean-Luc@gmail.com');
INSERT INTO tm_client (cli_id, cli_nom, cli_prenom, cli_telephone, cli_address, cli_mail) VALUES (seq_cli_id.NEXTVAL,'Duroi','Paul','+41233401212','16 Rue de Chauvinson','DuroiPaul@yahoo.com');
COMMIT;
		
-- tm_composant

INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'Mad Catz S.T.R.I.K.E 7',280.0,seq_mar_id.CURRVAL-2,seq_cot_id.CURRVAL-6);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'Logitech G19',160.0,seq_mar_id.CURRVAL-1,seq_cot_id.CURRVAL-6);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'Logitech G35',172.0,seq_mar_id.CURRVAL-1,seq_cot_id.CURRVAL-7);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'ASUS STRIX GeForce GTX980TI',839.70,seq_mar_id.CURRVAL-6,seq_cot_id.CURRVAL-9);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'GAINWARD GTX TITANX',1119.50,seq_mar_id.CURRVAL-5,seq_cot_id.CURRVAL-9);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'GAINWARD GTX 980 Phantom',545.70,seq_mar_id.CURRVAL-5,seq_cot_id.CURRVAL-9);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'EVGA GeForce GTX 980 Superclocke',606.70,seq_mar_id.CURRVAL-4,seq_cot_id.CURRVAL-9);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'ASUS GTX970',396.60,seq_mar_id.CURRVAL-6,seq_cot_id.CURRVAL-9);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'VGA GeForce GTX 960',291.90,seq_mar_id.CURRVAL-4,seq_cot_id.CURRVAL-9);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'ASUS STRIX-GTX950',196.10,seq_mar_id.CURRVAL-6,seq_cot_id.CURRVAL-9);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'ASUS GT610',56.0,seq_mar_id.CURRVAL-6,seq_cot_id.CURRVAL-9);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'ASUS Z10PE-D16 WS',560.80,seq_mar_id.CURRVAL-6,seq_cot_id.CURRVAL-8);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'ASUS X99-E WS',536.30,seq_mar_id.CURRVAL-6,seq_cot_id.CURRVAL-8);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'ASUS P8C WS',249.0,seq_mar_id.CURRVAL-6,seq_cot_id.CURRVAL-8);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'ASUS Crosshair V FORMULA-Z ROG',249.0,seq_mar_id.CURRVAL-6,seq_cot_id.CURRVAL-8);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'ASUS KGPE-D16',553.50,seq_mar_id.CURRVAL-6,seq_cot_id.CURRVAL-8);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'INTEL Xeon (14) E5-2697v3',2985.60,seq_mar_id.CURRVAL-10,seq_cot_id.CURRVAL);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'INTEL Xeon (12) Dodeca Core E5-2697v2',2888.80,seq_mar_id.CURRVAL-10,seq_cot_id.CURRVAL);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'INTEL Xeon (10) Déca Core E5-2687Wv3',2376.50,seq_mar_id.CURRVAL-10,seq_cot_id.CURRVAL);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'AMD FX 8-Core FX-8350',193.70,seq_mar_id.CURRVAL-11,seq_cot_id.CURRVAL);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'INTEL Pentium G2120 3.1GHz',91.70,seq_mar_id.CURRVAL-10,seq_cot_id.CURRVAL);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'AMD Opteron 12-Core 6348',700.40,seq_mar_id.CURRVAL,seq_cot_id.CURRVAL);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'SATA600 - 7200 TOSHIBA',150.60,seq_mar_id.CURRVAL-9,seq_cot_id.CURRVAL-5);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'SSD Drive 2.5 SATA INTEL DC S3610 Series',1388.90,seq_mar_id.CURRVAL-8,seq_cot_id.CURRVAL-5);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'SSD Drive 2.5 SATA SAMSUNG 850 Pro Series',926.90,seq_mar_id.CURRVAL-8,seq_cot_id.CURRVAL-5);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'SSD Drive INTEL SSD DC P3700 SERIES PCI-Express',5249.20,seq_mar_id.CURRVAL-8,seq_cot_id.CURRVAL-5);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'DDR4 [8x16GB] C14 - CORSAIR Dominator Platinum ',1679.30,seq_mar_id.CURRVAL-7,seq_cot_id.CURRVAL-3);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'DDR4 [8x16GB] C14 - CORSAIR Dominator Platinum ',1392.50,seq_mar_id.CURRVAL-7,seq_cot_id.CURRVAL-3);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'DDR4 [8x16GB] C14 - CORSAIR Dominator Platinum ',1281.70,seq_mar_id.CURRVAL-7,seq_cot_id.CURRVAL-3);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'DDR4 [4x4GB] C16 - CORSAIR Vengeance LPX Black ',276.90,seq_mar_id.CURRVAL-7,seq_cot_id.CURRVAL-3);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'DDR4 [2x4GB] C16 - CORSAIR Dominator Platinum ',110.80,seq_mar_id.CURRVAL-7,seq_cot_id.CURRVAL-3);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'DDR3 [4x8GB] DDR2400 (PC3-19200) - CORSAIR Dominator Platinum ',397.60,seq_mar_id.CURRVAL-7,seq_cot_id.CURRVAL-3);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'CORSAIR clavier Gaming K70 RGB Mechanical - Cherry MX Brown',201.0,seq_mar_id.CURRVAL-7,seq_cot_id.CURRVAL-6);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'LOGITECH clavier CH Wireless All-in-One Keyboard TK820',90.40,seq_mar_id.CURRVAL-1,seq_cot_id.CURRVAL-6);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'Souris RAZER Imperator Mouse 2012',77.80,seq_mar_id.CURRVAL,seq_cot_id.CURRVAL-2);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'LOGITECH Stereo Headset H111',20.90,seq_mar_id.CURRVAL-1,seq_cot_id.CURRVAL-7);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'Ecran 23,6 LED SAMSUNG TC241W SMART Cloud Display ',1019.40,seq_mar_id.CURRVAL-8,seq_cot_id.CURRVAL-4);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'Ecran 27 LED ASUS PA279Q',762.60,seq_mar_id.CURRVAL-7,seq_cot_id.CURRVAL-4);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'Ecran 32 LED ASUS PB328Q',669.0,seq_mar_id.CURRVAL-7,seq_cot_id.CURRVAL-4);
INSERT INTO tm_composant (cmp_id, cmp_nom, cmp_prix, cmp_mar_id, cmp_cot_id) VALUES (seq_cmp_id.NEXTVAL,'Ecran 55 LCD ASUS SD554-YB',1705.70,seq_mar_id.CURRVAL-7,seq_cot_id.CURRVAL-4);
COMMIT;
-- tm_commande

INSERT INTO tm_commande (com_id, com_cli_id) VALUES (seq_com_id.NEXTVAL,seq_cli_id.CURRVAL-4);
INSERT INTO tm_commande (com_id, com_cli_id) VALUES (seq_com_id.NEXTVAL,seq_cli_id.CURRVAL-3);
INSERT INTO tm_commande (com_id, com_cli_id) VALUES (seq_com_id.NEXTVAL,seq_cli_id.CURRVAL-2);
INSERT INTO tm_commande (com_id, com_cli_id) VALUES (seq_com_id.NEXTVAL,seq_cli_id.CURRVAL-1);
INSERT INTO tm_commande (com_id, com_cli_id) VALUES (seq_com_id.NEXTVAL,seq_cli_id.CURRVAL);
COMMIT;
-- tm_lignecommande
INSERT INTO tm_lignecommande (lco_cmp_id, lco_com_id, lco_qte) VALUES (seq_cmp_id.CURRVAL-39,seq_com_id.CURRVAL-4,1);
INSERT INTO tm_lignecommande (lco_cmp_id, lco_com_id, lco_qte) VALUES (seq_cmp_id.CURRVAL-37,seq_com_id.CURRVAL-4,2);
INSERT INTO tm_lignecommande (lco_cmp_id, lco_com_id, lco_qte) VALUES (seq_cmp_id.CURRVAL-1,seq_com_id.CURRVAL-3,1);
INSERT INTO tm_lignecommande (lco_cmp_id, lco_com_id, lco_qte) VALUES (seq_cmp_id.CURRVAL-35,seq_com_id.CURRVAL-2,1);
INSERT INTO tm_lignecommande (lco_cmp_id, lco_com_id, lco_qte) VALUES (seq_cmp_id.CURRVAL-10,seq_com_id.CURRVAL-1,3);
INSERT INTO tm_lignecommande (lco_cmp_id, lco_com_id, lco_qte) VALUES (seq_cmp_id.CURRVAL-13,seq_com_id.CURRVAL,1);
INSERT INTO tm_lignecommande (lco_cmp_id, lco_com_id, lco_qte) VALUES (seq_cmp_id.CURRVAL-16,seq_com_id.CURRVAL,1);
COMMIT;

-- tm_compotypeasspec 
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-5,seq_spc_id.CURRVAL-10);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-9,seq_spc_id.CURRVAL-9);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-9,seq_spc_id.CURRVAL-8);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-8,seq_spc_id.CURRVAL-7);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL,seq_spc_id.CURRVAL-7);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-5,seq_spc_id.CURRVAL-6);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-4,seq_spc_id.CURRVAL-5);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-2,seq_spc_id.CURRVAL-4);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-6,seq_spc_id.CURRVAL-4);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-7,seq_spc_id.CURRVAL-4);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-7,seq_spc_id.CURRVAL-3);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL,seq_spc_id.CURRVAL-2);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL,seq_spc_id.CURRVAL-1);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-4,seq_spc_id.CURRVAL);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-3,seq_spc_id.CURRVAL-11);
INSERT INTO tm_compotypeasspec (cts_cot_id, cts_spc_id) VALUES (seq_cot_id.CURRVAL-3,seq_spc_id.CURRVAL-12);
COMMIT;
	
-- tm_compotypeasmarque
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-5,seq_mar_id.CURRVAL-3);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-5,seq_mar_id.CURRVAL-7);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-5,seq_mar_id.CURRVAL-8);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-5,seq_mar_id.CURRVAL-9);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-4,seq_mar_id.CURRVAL-6);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-4,seq_mar_id.CURRVAL-8);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-3,seq_mar_id.CURRVAL-3);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-3,seq_mar_id.CURRVAL-7);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL,seq_mar_id.CURRVAL-7);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-9,seq_mar_id.CURRVAL-4);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-9,seq_mar_id.CURRVAL-5);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-9,seq_mar_id.CURRVAL-6);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-8,seq_mar_id.CURRVAL-11);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-8,seq_mar_id.CURRVAL-10);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-8,seq_mar_id.CURRVAL-6);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-6,seq_mar_id.CURRVAL-1);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-6,seq_mar_id.CURRVAL-2);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-6,seq_mar_id.CURRVAL-7);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-7,seq_mar_id.CURRVAL-1);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-2,seq_mar_id.CURRVAL);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-2,seq_mar_id.CURRVAL-1);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL-2,seq_mar_id.CURRVAL-2);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL,seq_mar_id.CURRVAL-11);
INSERT INTO tm_compotypeasmarque (ctm_cot_id, ctm_mar_id) VALUES (seq_cot_id.CURRVAL,seq_mar_id.CURRVAL-10);
COMMIT;
		
-- tm_specasvalue
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-12,'2400 MHZ');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-12,'2666 MHZ');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-12,'2800 MHZ');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-12,'3200 MHZ');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-11,'1GB');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-11,'2GB');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-11,'4GB');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-11,'8GB');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-11,'16GB');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-11,'32GB');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-11,'64GB');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-11,'128GB');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-10,'7200 Tours/minutes');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-10,'10000 Tours/minutes');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-10,'SSD');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-10,'SSD PCI-express');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-9,'1 GO'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-9,'2 GO'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-9,'4 GO');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-9,'6 GO'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-9,'12 GO');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-8,'1.2 GHz'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-8,'3.5 GHZ'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-8,'7.0 GHz'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-7,'AMD Socket AM3'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-7,'AMD Socket FM2'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-7,'AMD Socket G34'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-7,'Intel Socket 1155'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-7,'Intel Socket 2011'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-6,'100 Go');  
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-6,'120 Go');  
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-6,'256 Go'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-6,'480 Go'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-6,'500 Go'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-6,'750 Go');  
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-6,'1 To');  
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-6,'1.5 To');  
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-6,'2 To');  
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-6,'4 To'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-5,'17-19'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-5,'20-24'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-5,'25-29'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-5,'30+'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-4,'Normal'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-4,'Optimisé pour le jeu'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-3,'Normal');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-3,'Surround 5.1'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-3,'Surround 7.1'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-2,'Dual Core');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-2,'Quadra Core');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-2,'Six Core');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-2,'Octa Core');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-2,'Déca Core');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-2,'Doceda Core');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-2,'Tetradeca Core');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-1,'AMD A8'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-1,'AMD A10'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-1,'Intel Core I5'); 
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL-1,'Intel Core I7');  
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL,'1600X900');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL,'1680X1050');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL,'1920X1080');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL,'2160X1440');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL,'2560X1440');  	
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL,'2560X1600');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL,'3440X1440');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL,'3840X2160');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL,'4096X2160');
INSERT INTO tm_specasvalue (spv_id, spv_spc_id, spv_value) VALUES (seq_spv_id.NEXTVAL,seq_spc_id.CURRVAL,'5120X2880');
COMMIT;
		
-- tm_compoasspecvalue
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-24, seq_cmp_id.CURRVAL-39);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-24, seq_cmp_id.CURRVAL-38);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-24, seq_cmp_id.CURRVAL-37);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-21, seq_cmp_id.CURRVAL-37);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-47, seq_cmp_id.CURRVAL-36);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-49, seq_cmp_id.CURRVAL-36);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-46, seq_cmp_id.CURRVAL-35);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-48, seq_cmp_id.CURRVAL-35);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-46, seq_cmp_id.CURRVAL-34);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-50, seq_cmp_id.CURRVAL-34);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-45, seq_cmp_id.CURRVAL-33);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-50, seq_cmp_id.CURRVAL-33);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-47, seq_cmp_id.CURRVAL-32);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-50, seq_cmp_id.CURRVAL-32);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-45, seq_cmp_id.CURRVAL-31);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-50, seq_cmp_id.CURRVAL-31);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-46, seq_cmp_id.CURRVAL-30);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-50, seq_cmp_id.CURRVAL-30);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-47, seq_cmp_id.CURRVAL-29);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-51, seq_cmp_id.CURRVAL-29);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-40, seq_cmp_id.CURRVAL-28);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-40, seq_cmp_id.CURRVAL-27);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-41, seq_cmp_id.CURRVAL-26);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-44, seq_cmp_id.CURRVAL-25);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-42, seq_cmp_id.CURRVAL-24);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-40, seq_cmp_id.CURRVAL-23);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-14, seq_cmp_id.CURRVAL-23);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-40, seq_cmp_id.CURRVAL-22);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-15, seq_cmp_id.CURRVAL-22); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-40, seq_cmp_id.CURRVAL-21);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-16, seq_cmp_id.CURRVAL-21);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-44, seq_cmp_id.CURRVAL-20);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-17, seq_cmp_id.CURRVAL-20); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-41, seq_cmp_id.CURRVAL-19);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-20, seq_cmp_id.CURRVAL-19); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-42, seq_cmp_id.CURRVAL-18);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-15, seq_cmp_id.CURRVAL-18); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-30, seq_cmp_id.CURRVAL-17); --
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-56, seq_cmp_id.CURRVAL-17); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-32, seq_cmp_id.CURRVAL-16);  
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-54, seq_cmp_id.CURRVAL-16); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-31, seq_cmp_id.CURRVAL-15);  
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-54, seq_cmp_id.CURRVAL-15);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-31, seq_cmp_id.CURRVAL-14); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-53, seq_cmp_id.CURRVAL-14); --
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-66, seq_cmp_id.CURRVAL-13); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-57, seq_cmp_id.CURRVAL-13); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-67, seq_cmp_id.CURRVAL-12);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-57, seq_cmp_id.CURRVAL-12); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-68, seq_cmp_id.CURRVAL-11); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-57, seq_cmp_id.CURRVAL-11); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-65, seq_cmp_id.CURRVAL-10); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-60, seq_cmp_id.CURRVAL-10);  
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-66, seq_cmp_id.CURRVAL-9); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-61, seq_cmp_id.CURRVAL-9); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-68, seq_cmp_id.CURRVAL-8); 
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-59, seq_cmp_id.CURRVAL-8);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-24, seq_cmp_id.CURRVAL-7);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-24, seq_cmp_id.CURRVAL-6);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-24, seq_cmp_id.CURRVAL-5);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-25, seq_cmp_id.CURRVAL-4);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-28, seq_cmp_id.CURRVAL-3);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-7, seq_cmp_id.CURRVAL-3);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-27, seq_cmp_id.CURRVAL-2);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-5, seq_cmp_id.CURRVAL-2);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-26, seq_cmp_id.CURRVAL-1);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-5, seq_cmp_id.CURRVAL-1);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-26, seq_cmp_id.CURRVAL);
INSERT INTO tm_compoasspecvalue (cov_spv_id, cov_cmp_id) VALUES (seq_spv_id.CURRVAL-7, seq_cmp_id.CURRVAL); 
COMMIT;

-- tm_compoasstock
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-18,seq_cmp_id.CURRVAL-39,4,4);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-18,seq_cmp_id.CURRVAL-38,4,4);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-18,seq_cmp_id.CURRVAL-37,4,4);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-18,seq_cmp_id.CURRVAL-7,4,4); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-18,seq_cmp_id.CURRVAL-6,4,4);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-18,seq_cmp_id.CURRVAL-5,4,4);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-18,seq_cmp_id.CURRVAL-4,4,4);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-17,seq_cmp_id.CURRVAL-36,4,4);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-17,seq_cmp_id.CURRVAL-35,4,4); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-17,seq_cmp_id.CURRVAL-34,3,3); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-17,seq_cmp_id.CURRVAL-33,4,4); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-17,seq_cmp_id.CURRVAL-32,5,5); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-17,seq_cmp_id.CURRVAL-31,4,4); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-17,seq_cmp_id.CURRVAL-30,4,4); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-17,seq_cmp_id.CURRVAL-29,1,1);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-16,seq_cmp_id.CURRVAL-28,1,1);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-16,seq_cmp_id.CURRVAL-27,3,3); 	
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-16,seq_cmp_id.CURRVAL-26,1,1); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-16,seq_cmp_id.CURRVAL-25,5,5); 	
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-16,seq_cmp_id.CURRVAL-24,2,2);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-15,seq_cmp_id.CURRVAL-23,3,3);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-15,seq_cmp_id.CURRVAL-22,3,3); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-14,seq_cmp_id.CURRVAL-21,2,2); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-14,seq_cmp_id.CURRVAL-20,3,3); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-14,seq_cmp_id.CURRVAL-19,3,3); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-14,seq_cmp_id.CURRVAL-18,3,3);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-12,seq_cmp_id.CURRVAL-17,1,1);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-12,seq_cmp_id.CURRVAL-16,2,2); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-12,seq_cmp_id.CURRVAL-15,3,3); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-12,seq_cmp_id.CURRVAL-14,1,1);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-10,seq_cmp_id.CURRVAL-13,6,6);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-11,seq_cmp_id.CURRVAL-12,2,2); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-10,seq_cmp_id.CURRVAL-11,3,3); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-11,seq_cmp_id.CURRVAL-10,3,3); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-11,seq_cmp_id.CURRVAL-9,3,3); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-11,seq_cmp_id.CURRVAL-8,3,3);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-9,seq_cmp_id.CURRVAL-3,3,3);
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-9,seq_cmp_id.CURRVAL-2,3,3); 
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-8,seq_cmp_id.CURRVAL-1,1,1);  
INSERT INTO tm_compoasstock (cos_emp_id, cos_cmp_id, cos_nbstockvirtuel, cos_nbstockphysique) VALUES (seq_emp_id.CURRVAL-7,seq_cmp_id.CURRVAL,1,1);
COMMIT;

-- tm_changestockenatt (état 0 ou 1)
INSERT INTO tm_changestockenatt (cha_id, cha_cos_cmp_id, cha_datechange,  cha_qte, cha_etat, cha_comment)
VALUES  (seq_cha_id.NEXTVAL,seq_cmp_id.CURRVAL-1,TO_DATE('05-12-2015','dd-mm-yyyy'),1,1,'Apprivionnement d''écran 32 d''ASUS'); 
COMMIT;
