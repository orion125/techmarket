-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- Script: 08_grantsequence_TechMarket.sql 
-- Objet : Partage les droits sur les sequences de la base TechMarket 
-- 
-- Auteur : CAPITAO Jonathan
-- Mise à jour des versions
-- Version  Visa   Date       Commentaires
-- -----  ------  --------    ------------------------------------------------------------------------------------------------------------------------
-- 1.0       GA   25.11.15    Donner les droits sur les sequences pour l'utilisateur TechMarket_user
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
--<ScriptOptions statementTerminator=";"/>

-- Don des droits sur les sequence de l'utilisateur TechMarket_data
GRANT ALL ON seq_cha_id TO TechMarket_user;
GRANT ALL ON seq_cli_id TO TechMarket_user;
GRANT ALL ON seq_com_id TO TechMarket_user;
GRANT ALL ON seq_emp_id TO TechMarket_user;
GRANT ALL ON seq_man_id TO TechMarket_user;
GRANT ALL ON seq_spc_id TO TechMarket_user;
GRANT ALL ON seq_spv_id TO TechMarket_user;
GRANT ALL ON seq_cmp_id TO TechMarket_user;
GRANT ALL ON seq_mar_id TO TechMarket_user;
GRANT ALL ON seq_cot_id TO TechMarket_user;
