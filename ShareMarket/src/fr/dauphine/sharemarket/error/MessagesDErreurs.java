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
		map = (Map<String, String>) Collections.unmodifiableMap(errorMap);
	}
	
	public static String getMessageDerreur(String numero){
		return map.get(numero);
	}
}