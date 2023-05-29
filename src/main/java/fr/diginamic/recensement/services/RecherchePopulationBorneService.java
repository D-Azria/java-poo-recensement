package fr.diginamic.recensement.services;

import java.util.*;

import fr.diginamic.recensement.entites.Departement;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.*;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws minIntException, maxIntException, minMaxComp, unknownDpt, allExceptions {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();

		List<Ville> villesForDepts = rec.getVilles();
		Map<String, String> mapDepts = new HashMap<>();
		for (Ville ville : villesForDepts) {
			String departement = mapDepts.get(ville.getCodeDepartement());
			if (departement == null) {
				departement = ville.getCodeDepartement();
				mapDepts.put(ville.getCodeDepartement(), departement);
			}
		}
		if(!mapDepts.containsValue(choix)){
			throw new allExceptions("Ce code de département est inconnu");
		}

		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();

		boolean isNumericMin = saisieMin.chars().allMatch( Character::isDigit);
		if (!isNumericMin){
			throw new allExceptions("Veuillez saisir un chiffre et non une lettre pour la population minimale");
		}

		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		boolean isNumericMax = saisieMax.chars().allMatch(Character::isDigit);
		if (!isNumericMax){
			throw new allExceptions("Veuillez saisir un chiffre et non une lettre pour la population maximale");
		}
		if (Integer.parseInt(saisieMin) < 0 || Integer.parseInt(saisieMax) < 0 || Integer.parseInt(saisieMax) < Integer.parseInt(saisieMin) ){
			throw new allExceptions("Les valeurs min et/ou max saisies ne sont pas cohérentes");
		}

		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;
		
		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
			}
		}
	}

}
