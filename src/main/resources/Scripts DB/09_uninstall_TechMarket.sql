-- -----------------------------------------------------------------------------------------------------------------------------------------------
--Script : 09_uninstall_TechMarket.sql
--Objet  : Cr�ation des synonymes de la BDD d�monstration sur SGBD Oracle en Local (HEGLOCAL)
--
-- Auteur : CAPITAO Jonathan
-- Mise � jour des versions
-- Version  Visa   Date      Commentaires
-- -----  ------  --------    ---------------------------------------------------------------------------------------------------------------------
-- 1.0	     GA   21.11.15    Cr�ation utilisateurs
-- ------------------------------------------------------------------------------------------------------------------------------------------------

-- Ecriture du log pour la d�installation
SPOOL .\Logs\09_uninstall_TechMarket.log

-- Suppression des r�les, utilisateurs et profil --------------------------------------------------------------------------------------------------

--Suppression des utilisateurs (cascade permet de supprimer les tables & contraintes cr��es avec l'utilisateur concern�)
DROP USER TechMarket_data CASCADE;
DROP USER TechMarket_user CASCADE;

--Suppression des r�les (permet de supprimer les droits donn�es de fa�on "propre")
DROP ROLE role_TechMarket_data CASCADE;
DROP ROLE role_TechMarket_user CASCADE;

--Suppression du profil
DROP PROFILE TechMarket_profil;

SPOOL OFF

EXIT;
