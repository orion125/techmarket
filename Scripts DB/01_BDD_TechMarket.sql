-- -----------------------------------------------------------------------------------------------------------------------------------------------
-- Script : 01_TechMarket.sql
-- Objet  : Création BDD TechMarket sur SGBD Oracle en Local (HEGLOCAL) 
--
-- Auteur : CAPITAO Jonathan
-- Mise à jour des versions
-- Version  Visa   Date      Commentaires
-- -----  ------  --------    ---------------------------------------------------------------------------------------------------------------------
-- 1.0	     GA   21.11.15    Création utilisateurs
-- ------------------------------------------------------------------------------------------------------------------------------------------------

SET ECHO ON
SET LINESIZE 200
SET PAGESIZE 100

-- Création des utilisateurs en tant que System
SPOOL .\Logs\02_security_TechMarket.log
@.\02_security_TechMarket.sql
SPOOL OFF

-- Connexion à HEGLOCAL en tant que TechMarket_data (propirétaires des objets de schéma)
CONNECT TechMarket_data/TechMarket_data@HEGLOCAL

-- Création des tables
SPOOL .\Logs\03_create_TechMarket.log
@.\03_create_TechMarket.sql
SPOOL OFF

-- Chargement des données
SPOOL .\Logs\04_insert_TechMarket.log
@.\04_insert_TechMarket.sql;
SPOOL OFF

-- Création des triggers
SPOOL .\Logs\05_trigger_TechMarket.log
@.\05_trigger_TechMarket.sql
SPOOL OFF

-- Création des vues
SPOOL .\Logs\06_vues_TechMarket.log
@.\06_vues_TechMarket.sql
SPOOL OFF

-- Connexion à HEGLOCAL en tant que system
CONNECT system/manager@HEGLOCAL

-- Création des synonymes
SPOOL .\Logs\07_synonymes_TechMarket.log
@.\07_synonymes_TechMarket.sql;
SPOOL OFF

-- Partage des droits sur les séquences
SPOOL .\Logs\08_grantsequence_TechMarket.log
@.\08_grantsequence_TechMarket.sql
SPOOL OFF

SET ECHO OFF

EXIT;
