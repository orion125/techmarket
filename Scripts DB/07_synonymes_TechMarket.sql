-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- Script: 07_synonymes_TechMarket.sql 
-- Objet : Création des synonymes de la base TechMarket 
-- 
-- Auteur : CAPITAO Jonathan
-- Mise à jour des versions
-- Version  Visa   Date       Commentaires
-- -----  ------  --------    ------------------------------------------------------------------------------------------------------------------------
-- 1.0       GA   25.11.15    Création des synonymes
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
--<ScriptOptions statementTerminator=";"/>


-- Création des synonyme sur les vues ----------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE SYNONYM TechMarket_user.vw_changstockatt FOR TechMarket_data.vw_changstockatt;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_categorie_compo FOR TechMarket_data.vw_categorie_compo;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_client FOR TechMarket_data.vw_client;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_commande FOR TechMarket_data.vw_commande;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_composant FOR TechMarket_data.vw_composant;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_emplacement FOR TechMarket_data.vw_emplacement;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_ligne_commande FOR TechMarket_data.vw_ligne_commande;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_manager FOR TechMarket_data.vw_manager;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_marque FOR TechMarket_data.vw_marque;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_marque_as_categorie FOR TechMarket_data.vw_marque_as_categorie;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_spec FOR TechMarket_data.vw_spec;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_spec_as_categorie FOR TechMarket_data.vw_spec_as_categorie;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_stock FOR TechMarket_data.vw_stock;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_valeur_spec FOR TechMarket_data.vw_valeur_spec;
CREATE OR REPLACE SYNONYM TechMarket_user.vw_valeur_spec_as_compo FOR TechMarket_data.vw_valeur_spec_as_compo;

-- Création des synonyme sur les sequences -----------------------------------------------------------------------------------------------------------
CREATE OR REPLACE SYNONYM TechMarket_user.seq_cha_id FOR TechMarket_data.seq_cha_id;
CREATE OR REPLACE SYNONYM TechMarket_user.seq_cli_id FOR TechMarket_data.seq_cli_id;
CREATE OR REPLACE SYNONYM TechMarket_user.seq_com_id FOR TechMarket_data.seq_com_id;
CREATE OR REPLACE SYNONYM TechMarket_user.seq_emp_id FOR TechMarket_data.seq_emp_id;
CREATE OR REPLACE SYNONYM TechMarket_user.seq_man_id FOR TechMarket_data.seq_man_id;
CREATE OR REPLACE SYNONYM TechMarket_user.seq_spc_id FOR TechMarket_data.seq_spc_id;
CREATE OR REPLACE SYNONYM TechMarket_user.seq_spv_id FOR TechMarket_data.seq_spv_id;
CREATE OR REPLACE SYNONYM TechMarket_user.seq_cmp_id FOR TechMarket_data.seq_cmp_id;
CREATE OR REPLACE SYNONYM TechMarket_user.seq_mar_id FOR TechMarket_data.seq_mar_id;
CREATE OR REPLACE SYNONYM TechMarket_user.seq_cot_id FOR TechMarket_data.seq_cot_id;