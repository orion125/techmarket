-- -----------------------------------------------------------------------------------------------------------------------------------------------
-- Script : 02_security_TechMarket.sql
-- Objet  : Création des utilisateurs BDD TechMarket sur SGBD Oracle en Local (HEGLOCAL)
--
-- Auteur : CAPITAO Jonathan
-- Mise à jour des versions
-- Version  Visa   Date       Commentaires
-- -----  ------  --------    ---------------------------------------------------------------------------------------------------------------------
-- 1.0	     GA   18.11.15    Création utilisateurs
-- ------------------------------------------------------------------------------------------------------------------------------------------------


-- Suppression des rôles, utilisateurs et profil -------------------------------------------------------------------------------------------------- 

-- Suppression des utilisateurs
DROP USER TechMarket_data CASCADE;
DROP USER TechMarket_user CASCADE;

-- Suppression des rôles
DROP ROLE role_TechMarket_data CASCADE;
DROP ROLE role_TechMarket_user CASCADE;

-- Suppression du profil 
DROP PROFILE TechMarket_profil;

-- Création du profil TechMarket_profil -------------------------------------------------------------------------------------------------------------
CREATE PROFILE TechMarket_profil LIMIT
	SESSIONS_PER_USER 3
	FAILED_LOGIN_ATTEMPTS 3 
	PASSWORD_LOCK_TIME 1/24
	PASSWORD_LIFE_TIME 180 
	PASSWORD_REUSE_TIME 180 
	PASSWORD_REUSE_MAX UNLIMITED
	PASSWORD_GRACE_TIME 7;

-- Création du rôle role_TechMarket_data (rôle de l'utilisateur propriétaire des objets de la base) -------------------------------------------------

-- Creation du rôle role_TechMarket_data
CREATE ROLE role_TechMarket_data;

-- droit de se connecter à la BDD
GRANT CONNECT TO role_TechMarket_data;

-- droit de créer des triggers, séquence, tables, packages, ...
GRANT RESOURCE TO role_TechMarket_data;

-- droit de créer des vues
GRANT CREATE VIEW TO role_TechMarket_data;

-- droit de créer des synonymes
-- GRANT CREATE ANY SYNONYM TO role_TechMarket_data;
-- ! fonctionne mais donne trop de privilèges, à éviter

-- Création du rôle role_TechMarket_user (rôle de l'utilisateur de l'application) -------------------------------------------------------------------

-- Creation du rôle role_TechMarket_user
CREATE ROLE role_TechMarket_user;

-- droit de se connecter à la BDD
GRANT CREATE SESSION TO role_TechMarket_user;

-- ---------------------------------------------------------------------------------------------
-- Création de l'utilisateur TechMarket_data (propriétaire des objets de schéma de la base)
-- ---------------------------------------------------------------------------------------------

-- Creation de l'utilisateur TechMarket_data 
CREATE USER TechMarket_data
	PROFILE TechMarket_profil
	IDENTIFIED BY TechMarket_data
	DEFAULT TABLESPACE USERS
	TEMPORARY TABLESPACE TEMP
;
GRANT role_TechMarket_data TO TechMarket_data;
ALTER USER TechMarket_data quota unlimited ON USERS;

-- ---------------------------------------------------------------------------------------------
-- Création de l'utilisateur TechMarket_user (utilisateur de l'application)
-- ---------------------------------------------------------------------------------------------
CREATE USER TechMarket_user
	PROFILE TechMarket_profil
	IDENTIFIED BY TechMarket_user
;
GRANT role_TechMarket_user TO TechMarket_user;

COMMIT;