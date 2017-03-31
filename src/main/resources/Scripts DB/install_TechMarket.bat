REM Fichier		: install_TechMarket.bat
REM Objet		: Création de la BDD TechMarket
REM Author		: CAPITAO Jonathan

mkdir .\Logs

REM Lancement du script 01_BDD_TechMarket.sql dans SQL*PLUS
REM le %1 permet de demander le mot de passe lors du lancement du script
sqlplus SYSTEM/manager@HEGLOCAL @01_BDD_TechMarket.sql