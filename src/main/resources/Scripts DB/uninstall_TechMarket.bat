REM Fichier		: uninstall_TechMarket.bat
REM Objet		: Désinstallation de la BDD TechMarket 
REM Author		: CAPITAO Jonathan

mkdir .\Logs

REM Lancement du script uninstall_TechMarket.sql dans SQL*PLUS
REM le %1 permet de demander le mot de passe lors du lancement du script
sqlplus SYSTEM/manager@HEGLOCAL @10_uninstall_TechMarket.sql