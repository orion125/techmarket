****************************************************************************************************************************************
* Auteur   : Capitao Jonathan                                                                                                          *
* Date     : 31.10.2015                                                                                                                *
* Version  : 1.2                                                                                                                       *
* Language : JAVA (JDK 8)                                                                                                              *
* IDE	   : Netbeans                                                                                                                  *
****************************************************************************************************************************************

// Le nom des champs, fk et table dont le nom est trop long a �t� abr�g� dans le Script SQL correspondant.

Pour lancer l'application aller dans la dossier "dist" et double-cliquer sur CAPITAO_TechMarket.jar
Pour installer la Base de donn�es oracle, lancer le serveur oracle puis aller dans le dossier "Scripts DB" et lancer install_TechMarket.bat

Ceci est la feuille de route permettant d'utiliser le prototype de l'application TekMarket.

Celle-ci est r�partie en 2 sections :
	Section Client
	Section Vendeur/Manager

L'acc�s client ne demande aucune connexion. Il suffit de cliquer sur le bouton "Acc�s client" pour entrer

dans l'application client.
	Les menus :
		Fichier
			-> Fermer : Permet de quitter l'application (raccourci indiqu� � c�t�)
		Types de composants : Tous les sous-menus ici pr�sents permettent de changer le filtre sur la recherche de la page.
					(ces sous-menus sont automatiquement g�n�r�s)
		Aide 
			-> sous-menu � propos : Permet d'acc�der aux informations relatives � la cr�ation de l'application.


Pour les autres pages de la section client, il n'y a que les menus (sauf pour le r�sum� de la facture) :

		Fichier
			-> Fermer : Ferme la fen�tre courante. (raccourci indiqu� � c�t�)
		Aide 
			-> sous-menu � propos : Permet d'acc�der aux informations relatives � la cr�ation de l'application.


La navigation dans la partie client :

	Le bouton "Ajouter au panier" ajoute le composant choisi au panier (liste de composants)
	Le bouton "Panier" ouvre la page contenant le r�sum� de la commande (le panier).

	Sur la page "�tat du panier" :
		En appuyant sur le bouton "Supprimer du panier", on peut supprimer un composant du panier.
		En appuyant sur le bouton "Valider le panier", l'application ouvre la page de recherche de client.
		Si le client est d�j� enregistr� il appara�t dans la liste et peut �tre s�lectionn� sinon il faut 
		appuyer sur le bouton "Nouveau Client".
			Ce bouton ouvre une page permettant de cr�er un nouveau client.
		Il faut s�lectionner le client voulant passer commande dans la liste avec le bouton "Valider".
			La page "R�sum� de la facture" s'ouvre alors. Celle-ci permet d'imprimer la facture avec le bouton
			"Imprimer". (cette page n'a pas de menu volontairement)



L'acc�s vendeur demande d'utiliser la fonction connexion (avec le bouton "Connexion") de la page d'accueil au lancement 
de l'application. Les identifiants sont "Admin" et mot de passe "1234".

	Les menus :
		Administration 

			-> sous-menu Import : Permet d'importer par la suite une base de donn�es. (Un explorateur de fichiers s'ouvre pour s�lectionner le fichier)

			-> sous-menu Fermer : Permet de quitter l'application (raccourci indiqu� � c�t�)

		Gestions des donn�es  // Permet d'acc�der � la gestion des diff�rentes donn�es

			-> sous-menu Gestion des composants 	: Permet d'acc�der � la page de gestion des composants.
									En utilisant le bouton "Ajouter" ou "Modifier", la page 
									d'�dition de composants s'ouvre permettant de respectivement
									cr�er ou modifier un composant.

			-> sous-menu Gestion des marques 	: Permet d'acc�der � la page de gestion des marques.
									Appuyer sur le bouton "Ajouter" ou "Modifier" n'ouvre pas 
									de page suppl�mentaire mais affiche un panel lat�ral qui 
									permet de cr�er ou modifier une marque.

			-> sous-menu Gestion des sp�cifications : Permet d'acc�der � la page de gestion des sp�cifications
									Appuyer sur le bouton "Ajouter" ou "Modifier" n'ouvre pas 
									de page suppl�mentaire mais affiche un panel lat�ral qui 
									permet de cr�er ou modifier une sp�cification.

			-> sous-menu Gestion des cat�gories de composants : Permet d'acc�der � la page de gestion des cat�gories de composants.
									Appuyer sur le bouton "Ajouter" ou "Modifier" n'ouvre pas 
									de page suppl�mentaire mais affiche un panel lat�ral qui 
									permet de cr�er ou modifier une cat�gorie de composants.

			-> sous-menu Gestion du stock		:  Permet d'acc�der aux stocks par composant.
				Cette page permet de savoir et/ou de d�finir l'emplacement des composants dans le stock.
				Cette page permet de demander un approvisionnement de stock pour un composant (Bouton "Commander")
					Une demande d'approvisionnement change le stock virtuel mais pas le stock physique. 
					Le stock physique sera chang� � l'aide de la gestion des Approvisionnements et Livraisons 
					en attente.

				Le bouton "Livraisons et Approvisionnments" ouvre la page donnant acc�s � la fluctuation du stock physique.
					Cette page contient la liste des approvisionnements et des livraisons en attente de
					validation. Quand on appuie sur le bouton "Confirmer" la ligne s�lectionn�e dans la 
					liste dispara�t et le stock physique du composant concern� est modifi�. (par rapport
					� l'apprivisionnement ou la livraison s�lectionn�e)
		Aide 
			-> sous-menu � propos : Permet d'acc�der aux informations relatives � la cr�ation de l'application.
	La fen�tre elle-m�me permet d'�diter la configuration de l'application.
	
Sur les fen�tres autres que la fen�tre principale de l'application du vendeur/manager, seulement les menus suivants sont pr�sents.
	Fichier 
		-> sous-menu Fermer   : Ferme la fen�tre courante. (raccourci indiqu� � c�t�)
	Aide
		-> sous-menu � propos : Permet d'acc�der aux informations relatives � la cr�ation de l'application.
	
				

