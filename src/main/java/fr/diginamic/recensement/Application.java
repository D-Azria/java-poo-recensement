package fr.diginamic.recensement;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.exceptions.*;
import fr.diginamic.recensement.services.*;
import fr.diginamic.recensement.utils.RecensementUtils;

import java.util.Scanner;

/**
 * Application de traitement des données de recensement de population
 * 
 * @param args
 */
public class Application {

	/**
	 * Point d'entrée
	 * 
	 * @param args arguments (non utilisés ici)
	 */
	public static void main(String[] args) throws allExceptions, maxIntException, minIntException, minMaxComp, unknownDpt {
		Scanner scanner = new Scanner(System.in);

		String filePath = ClassLoader.getSystemClassLoader().getResource("recensement.csv").getFile();
		Recensement recensement = RecensementUtils.lire(filePath);

		if (recensement == null) {
			System.out.println("L'application doit s'arrétée en raison d'une erreur d'exécution.");
			System.exit(-1);
		}

		// Menu
		int choix = 0;
		do {
			try {
				// Affichage du menu
				afficherMenu();

				// Poser une question à l'utilisateur
				String choixMenu = scanner.nextLine();

				// Conversion du choix utilisateur en int
				choix = Integer.parseInt(choixMenu);

				// On exécute l'option correspondant au choix de l'utilisateur
				switch (choix) {
					case 1:
						RecherchePopulationVilleService rechercheVille = new RecherchePopulationVilleService();
						rechercheVille.traiter(recensement, scanner);
						break;
					case 2:
						RecherchePopulationDepartementService rechercheDept = new RecherchePopulationDepartementService();
						rechercheDept.traiter(recensement, scanner);
						break;
					case 3:
						RecherchePopulationRegionService rechercheRegion = new RecherchePopulationRegionService();
						rechercheRegion.traiter(recensement, scanner);
						break;
					case 4:
						RecherchePopulationBorneService recherchePopBorne = new RecherchePopulationBorneService();
						recherchePopBorne.traiter(recensement, scanner);
						break;
					case 5:
						RechercheVillesPlusPeupleesDepartement rechercheVillesPlusPeupleesDepartement = new RechercheVillesPlusPeupleesDepartement();
						rechercheVillesPlusPeupleesDepartement.traiter(recensement, scanner);
						break;
					case 6:
						RechercheVillesPlusPeupleesRegion rechercheVillesPlusPeupleesRegion = new RechercheVillesPlusPeupleesRegion();
						rechercheVillesPlusPeupleesRegion.traiter(recensement, scanner);
						break;
					case 7:
						RechercheDepartementsPlusPeuplees rechercherDepartementsPlusPeuplees = new RechercheDepartementsPlusPeuplees();
						rechercherDepartementsPlusPeuplees.traiter(recensement, scanner);
						break;
					case 8:
						RechercheRegionsPlusPeuplees rechercheRegionsPlusPeuplees = new RechercheRegionsPlusPeuplees();
						rechercheRegionsPlusPeuplees.traiter(recensement, scanner);
						break;
					case 9:
						RechercheVillesPlusPeupleesFrance rechercheVillesPlusPeupleesFrance = new RechercheVillesPlusPeupleesFrance();
						rechercheVillesPlusPeupleesFrance.traiter(recensement, scanner);
						break;
				}
			}
			catch(allExceptions exception) {
				System.out.println(exception.getMessage());
			}
		} while (choix != 99) ;

			scanner.close();

	}

	/**
	 * Affichage du menu
	 */
	private static void afficherMenu() {
		System.out.println("***** Recensement population *****");
		System.out.println("1. Rechercher la population d'une ville");
		System.out.println("2. Rechercher la population d'un département");
		System.out.println("3. Rechercher la population d'une région");
		System.out.println("4. Rechercher la population des villes d'un dept entre min et max");
		System.out.println("5. Rechercher les N plus grandes villes d'un département.");
		System.out.println("6. Rechercher les N plus grandes villes d'une région.");
		System.out.println("7. Rechercher les N plus grands départements de France.");
		System.out.println("8. Rechercher les N plus grandes régions de France.");
		System.out.println("9. Rechercher les N plus grandes villes de France.");
		System.out.println("99. Sortir");
	}
}
