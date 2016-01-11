****************************************************************************************************************************************
* Auteur   : Capitao Jonathan                                                                                                          *
* Date     : 31.10.2015                                                                                                                *
* Version  : 1.2                                                                                                                       *
* Language : JAVA (JDK 8)                                                                                                              *
* IDE	   : Netbeans                                                                                                                  *
****************************************************************************************************************************************

// Le nom des champs, fk et table dont le nom est trop long a été abrégé dans le Script SQL correspondant.

Pour lancer l'application aller dans la dossier "dist" et double-cliquer sur CAPITAO_TechMarket.jar
Pour installer la Base de données oracle, lancer le serveur oracle puis aller dans le dossier "Scripts DB" et lancer install_TechMarket.bat

Ceci est la feuille de route permettant d'utiliser le prototype de l'application TekMarket.

Celle-ci est répartie en 2 sections :
	Section Client
	Section Vendeur/Manager

L'accès client ne demande aucune connexion. Il suffit de cliquer sur le bouton "Accès client" pour entrer

dans l'application client.
	Les menus :
		Fichier
			-> Fermer : Permet de quitter l'application (raccourci indiqué à côté)
		Types de composants : Tous les sous-menus ici présents permettent de changer le filtre sur la recherche de la page.
					(ces sous-menus sont automatiquement générés)
		Aide 
			-> sous-menu À propos : Permet d'accéder aux informations relatives à la création de l'application.


Pour les autres pages de la section client, il n'y a que les menus (sauf pour le résumé de la facture) :

		Fichier
			-> Fermer : Ferme la fenètre courante. (raccourci indiqué à côté)
		Aide 
			-> sous-menu À propos : Permet d'accéder aux informations relatives à la création de l'application.


La navigation dans la partie client :

	Le bouton "Ajouter au panier" ajoute le composant choisi au panier (liste de composants)
	Le bouton "Panier" ouvre la page contenant le résumé de la commande (le panier).

	Sur la page "État du panier" :
		En appuyant sur le bouton "Supprimer du panier", on peut supprimer un composant du panier.
		En appuyant sur le bouton "Valider le panier", l'application ouvre la page de recherche de client.
		Si le client est déjà enregistré il apparaît dans la liste et peut être sélectionné sinon il faut 
		appuyer sur le bouton "Nouveau Client".
			Ce bouton ouvre une page permettant de créer un nouveau client.
		Il faut sélectionner le client voulant passer commande dans la liste avec le bouton "Valider".
			La page "Résumé de la facture" s'ouvre alors. Celle-ci permet d'imprimer la facture avec le bouton
			"Imprimer". (cette page n'a pas de menu volontairement)



L'accès vendeur demande d'utiliser la fonction connexion (avec le bouton "Connexion") de la page d'accueil au lancement 
de l'application. Les identifiants sont "Admin" et mot de passe "1234".

	Les menus :
		Administration 

			-> sous-menu Import : Permet d'importer par la suite une base de données. (Un explorateur de fichiers s'ouvre pour sélectionner le fichier)

			-> sous-menu Fermer : Permet de quitter l'application (raccourci indiqué à côté)

		Gestions des données  // Permet d'accéder à la gestion des différentes données

			-> sous-menu Gestion des composants 	: Permet d'accéder à la page de gestion des composants.
									En utilisant le bouton "Ajouter" ou "Modifier", la page 
									d'édition de composants s'ouvre permettant de respectivement
									créer ou modifier un composant.

			-> sous-menu Gestion des marques 	: Permet d'accéder à la page de gestion des marques.
									Appuyer sur le bouton "Ajouter" ou "Modifier" n'ouvre pas 
									de page supplémentaire mais affiche un panel latéral qui 
									permet de créer ou modifier une marque.

			-> sous-menu Gestion des spécifications : Permet d'accéder à la page de gestion des spécifications
									Appuyer sur le bouton "Ajouter" ou "Modifier" n'ouvre pas 
									de page supplémentaire mais affiche un panel latéral qui 
									permet de créer ou modifier une spécification.

			-> sous-menu Gestion des catégories de composants : Permet d'accéder à la page de gestion des catégories de composants.
									Appuyer sur le bouton "Ajouter" ou "Modifier" n'ouvre pas 
									de page supplémentaire mais affiche un panel latéral qui 
									permet de créer ou modifier une catégorie de composants.

			-> sous-menu Gestion du stock		:  Permet d'accéder aux stocks par composant.
				Cette page permet de savoir et/ou de définir l'emplacement des composants dans le stock.
				Cette page permet de demander un approvisionnement de stock pour un composant (Bouton "Commander")
					Une demande d'approvisionnement change le stock virtuel mais pas le stock physique. 
					Le stock physique sera changé à l'aide de la gestion des Approvisionnements et Livraisons 
					en attente.

				Le bouton "Livraisons et Approvisionnments" ouvre la page donnant accès à la fluctuation du stock physique.
					Cette page contient la liste des approvisionnements et des livraisons en attente de
					validation. Quand on appuie sur le bouton "Confirmer" la ligne sélectionnée dans la 
					liste disparaît et le stock physique du composant concerné est modifié. (par rapport
					à l'apprivisionnement ou la livraison sélectionnée)
		Aide 
			-> sous-menu À propos : Permet d'accéder aux informations relatives à la création de l'application.
	La fenètre elle-même permet d'éditer la configuration de l'application.
	
Sur les fenètres autres que la fenètre principale de l'application du vendeur/manager, seulement les menus suivants sont présents.
	Fichier 
		-> sous-menu Fermer   : Ferme la fenètre courante. (raccourci indiqué à côté)
	Aide
		-> sous-menu À propos : Permet d'accéder aux informations relatives à la création de l'application.
	
				

