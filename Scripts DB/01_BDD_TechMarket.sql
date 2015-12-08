-- -----------------------------------------------------------------------------------------------------------------------------------------------
-- Script : 01_TechMarket.sql
-- Objet  : Cr�ation BDD TechMarket sur SGBD Oracle en Local (HEGLOCAL) 
--
-- Auteur : CAPITAO Jonathan
-- Mise � jour des versions
-- Version  Visa   Date      Commentaires
-- -----  ------  --------    ---------------------------------------------------------------------------------------------------------------------
-- 1.0	     GA   21.11.15    Cr�ation utilisateurs
-- ------------------------------------------------------------------------------------------------------------------------------------------------

SET ECHO ON
SET LINESIZE 200
SET PAGESIZE 100

-- Cr�ation des utilisateurs en tant que System
SPOOL .\Logs\02_security_TechMarket.log
@.\02_security_TechMarket.sql
SPOOL OFF

-- Connexion � HEGLOCAL en tant que TechMarket_data (propir�taires des objets de sch�ma)
CONNECT TechMarket_data/TechMarket_data@HEGLOCAL

-- Cr�ation des tables
SPOOL .\Logs\03_create_TechMarket.log
@.\03_create_TechMarket.sql
SPOOL OFF

-- Chargement des donn�es
SPOOL .\Logs\04_insert_TechMarket.log
@.\04_insert_TechMarket.sql;
SPOOL OFF

-- Cr�ation des triggers
SPOOL .\Logs\05_trigger_TechMarket.log
@.\05_trigger_TechMarket.sql
SPOOL OFF

-- Cr�ation des vues
SPOOL .\Logs\06_vues_TechMarket.log
@.\06_vues_TechMarket.sql
SPOOL OFF

-- Connexion � HEGLOCAL en tant que system
CONNECT system/manager@HEGLOCAL

-- Cr�ation des synonymes
SPOOL .\Logs\07_synonymes_TechMarket.log
@.\07_synonymes_TechMarket.sql;
SPOOL OFF

-- Partage des droits sur les s�quences
SPOOL .\Logs\08_grantsequence_TechMarket.log
@.\08_grantsequence_TechMarket.sql
SPOOL OFF

SET ECHO OFF

EXIT;
