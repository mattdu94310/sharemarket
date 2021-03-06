package fr.dauphine.sharemarket.error;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MessagesDErreurs {
	private static final Map<String, String> map;
	static
	{
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("0", "Code réservé");
		errorMap.put("1", "Un membre société avec le même login existe déjà");
		errorMap.put("2", "Une société du même nom existe déjà");
		errorMap.put("3", "Merci de renseigner tous les champs");
		errorMap.put("4", "Identifiant / Mot de passe incorrect");
		errorMap.put("5", "Votre compte n'a pas encore été validé");
		errorMap.put("6", "Un problème est survenu lors de la récupération des informations de votre société");
		errorMap.put("7", "Votre société n'a pas été trouvé, contactez Hervé");
		errorMap.put("8", "Société correctement modifié");
		errorMap.put("9", "Impossible de charger la liste des secteurs");
		errorMap.put("10", "Erreur de requete veuillez recommencer ou contactez l'administrateur");
		errorMap.put("11", "Impossible de retrouver le secteur demandé");
		errorMap.put("11", "Impossible de retrouver la société demandée");
		errorMap.put("12", "Impossible de charger la liste des types de contrat");
		errorMap.put("13", "Pb type de contrat");
		errorMap.put("14", "Contrat ajouté");
		errorMap.put("15", "Impossible de retrouver le contrat");
		errorMap.put("16", "Contrat modifié");

		map = (Map<String, String>) Collections.unmodifiableMap(errorMap);
	}
	
	public static String getMessageDerreur(String numero){
		return map.get(numero);
	}
}
