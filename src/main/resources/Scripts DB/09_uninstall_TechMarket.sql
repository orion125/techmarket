-- -----------------------------------------------------------------------------------------------------------------------------------------------
--Script : 09_uninstall_TechMarket.sql
--Objet  : Création des synonymes de la BDD démonstration sur SGBD Oracle en Local (HEGLOCAL)
--
-- Auteur : CAPITAO Jonathan
-- Mise à jour des versions
-- Version  Visa   Date      Commentaires
-- -----  ------  --------    ---------------------------------------------------------------------------------------------------------------------
-- 1.0	     GA   21.11.15    Création utilisateurs
-- ------------------------------------------------------------------------------------------------------------------------------------------------

-- Ecriture du log pour la déinstallation
SPOOL .\Logs\09_uninstall_TechMarket.log

-- Suppression des rôles, utilisateurs et profil --------------------------------------------------------------------------------------------------

--Suppression des utilisateurs (cascade permet de supprimer les tables & contraintes créées avec l'utilisateur concerné)
DROP USER TechMarket_data CASCADE;
DROP USER TechMarket_user CASCADE;

--Suppression des rôles (permet de supprimer les droits données de façon "propre")
DROP ROLE role_TechMarket_data CASCADE;
DROP ROLE role_TechMarket_user CASCADE;

--Suppression du profil
DROP PROFILE TechMarket_profil;

SPOOL OFF

EXIT;
