package br.com.f13fabricio.uffmail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ProfileManager {
	
	private static ProfileManager profileManager;
	
	private String dataBase;
	private String csvRegex = ",";
	
	private ProfileManager() { }
	
	public static ProfileManager getProfileManager() {
		if (profileManager == null)
			profileManager = new ProfileManager();
		
		return profileManager;
	}
	
	public void setDataBase(String csvFile) {
		dataBase = csvFile;
	}
	
	public void setCsvRegex(String regex) {
		this.csvRegex = regex;
	}
	
	public StudentProfile mapProfile(String enrollment) throws NullPointerException, IOException {
		String line = "";
		/*Se a base de dados não informada é lançada uma NullPoiterException*/
		if (dataBase == null) 
			throw new NullPointerException("Profile Manager não vinculado com uma base de dados.");
		
		try (BufferedReader br = new BufferedReader(new FileReader(dataBase))) {
			
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(csvRegex);
				/*A posição 1 do array corresponte a matrícula*/
				if (enrollment.equals(fields[1]))
					return new StudentProfile(fields);
			}
		}
		/*Caso a matrícula informada não seje encontrada retorna null*/
		return null;
	}
	
	/* O estudante só pode requisitar a criação de um uffmail se ainda não posui um uffmail e
	 * seu status está com Ativo.
	 */
	public boolean validateUffmailCreationRequest(StudentProfile profile) {
		return (profile.getUffmail().equals("")) && (profile.getStatus().equals(StudentProfile.ACTIVE));
	}
	
	public ArrayList<UsernameSuggestion> getUsernameSuggestions(String name) 
			throws IllegalArgumentException {
		String _name;
		String words[];
		String pattern = "(?i)(\\w)(\\s+)(e|do|da|do|das|de|di|du)(\\s+)(\\w)";
		
		ArrayList<UsernameSuggestion> validSuggestions;
		ArrayList<UsernameSuggestion> invalidSuggestions;
		
		if (name == "")
			throw new IllegalArgumentException("O nome não existe.");
		_name = name.replaceAll(pattern, "$1 $5"); //REVISAR
		_name = _name.toLowerCase();
		words = _name.split(" ");
		if (words.length < 2)
			throw new IllegalArgumentException("O nome: " + name + " é um nome inválido.");
		
		/* Gerar as 5 sugestões de email */
		
		validSuggestions = new ArrayList<>();
		invalidSuggestions = new ArrayList<>();
		
		/* primeiro nome + "_" + segundo nome */
		validSuggestions.add(new UsernameSuggestion(words[0] + "_" + words[1]));
		/* primeironome + primeira letra do do segundo + primeira letra do último */
		validSuggestions.add(new UsernameSuggestion(words[0] + words[1].substring(0, 1) + words[words.length - 1].substring(0, 1)));
		/* primeiro nome + segundo nome */
		validSuggestions.add(new UsernameSuggestion(words[0] + words[1]));
		/* primeira letra do primeiro nome + segundo nome */
		validSuggestions.add(new UsernameSuggestion(words[0].substring(0, 1) + words[2]));
		/* primeira letra do primeiro nome + segundo nome + último nome */
		validSuggestions.add(new UsernameSuggestion(words[0].substring(0, 1) + words[1] + words[words.length - 1]));
		
		return validSuggestions;
		
	}

}
