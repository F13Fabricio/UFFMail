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
	
	public StudentProfile mapProfile(String enrollment) 
			throws NullPointerException, IllegalArgumentException, IOException {
		String line = "";
		/* Se a base de dados n�o informada � lan�ada uma NullPoiterException*/
		if (dataBase == null) 
			throw new NullPointerException("Profile Manager n�o vinculado com uma base de dados.");
		/* Verifica se a string corresponte a uma poss�vel matr�cula */
		if (enrollment == "" || !enrollment.matches("^[0-9]*$"))
			throw new IllegalArgumentException("Mat�cula inv�lida.");
		
		try (BufferedReader br = new BufferedReader(new FileReader(dataBase))) {
			
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(csvRegex);
				/*A posi��o 1 do array corresponte a matr�cula*/
				if (enrollment.equals(fields[1]))
					return new StudentProfile(fields);
			}
		}
		/* Caso a matr�cula informada n�o seje encontrada retorna null*/
		return null;
	}
	
	/* O estudante s� pode requisitar a cria��o de um uffmail se ainda n�o posui um uffmail e
	 * seu status est� com Ativo.
	 */
	public boolean validateUffmailCreationRequest(StudentProfile profile) {
		return (profile.getUffmail().equals("")) && (profile.getStatus().equals(StudentProfile.ACTIVE));
	}
	
	public ArrayList<UsernameSuggestion> getUsernameSuggestions(String name) 
			throws IllegalArgumentException, IOException{
		String _name;
		String words[];
		String pattern = "(?i)(\\w)(\\s+)(e|do|da|dos|das|de|di|du)(\\s+)(\\w)";
		ArrayList<UsernameSuggestion> suggestions;
		
		if (name == "")
			throw new IllegalArgumentException("O nome n�o existe.");
		_name = name.replaceAll(pattern, "$1 $5");
		_name = _name.toLowerCase();
		words = _name.split(" ");
		if (words.length < 2)
			throw new IllegalArgumentException("O nome: " + name + " � um nome inv�lido.");
		
		/* Gerar as 5 sugest�es de email */
		
		suggestions = new ArrayList<>();
		
		/* primeiro nome + "_" + segundo nome */
		suggestions.add(new UsernameSuggestion(words[0] + "_" + words[1]));
		/* primeironome + primeira letra do do segundo + primeira letra do �ltimo */
		suggestions.add(new UsernameSuggestion(words[0] + words[1].substring(0, 1) + words[words.length - 1].substring(0, 1)));
		/* primeiro nome + �ltimo nome */
		suggestions.add(new UsernameSuggestion(words[0] + words[words.length - 1]));
		/* primeira letra do primeiro nome + segundo nome */
		suggestions.add(new UsernameSuggestion(words[0].substring(0, 1) + words[1]));
		/* primeira letra do primeiro nome + segundo nome + �ltimo nome */
		suggestions.add(new UsernameSuggestion(words[0].substring(0, 1) + words[1] + words[words.length - 1]));
		
		/* retorna as sugest�es j� atualizadas */
		return updateSuggestions(suggestions);
		
	}
	
	public ArrayList<UsernameSuggestion> updateSuggestions(ArrayList<UsernameSuggestion> s) 
			throws IOException{
		
		if (s == null)
			return null;
		ArrayList<UsernameSuggestion> suggestions = new ArrayList<>(s);
		ArrayList<UsernameSuggestion> validSuggestions = new ArrayList<>();
		ArrayList<UsernameSuggestion> invalidSuggestions = new ArrayList<>();
		String line;
		
		do {
			/* Atualizar sugest�es inv�lidas, se existir */
			for (int i = 0; i < invalidSuggestions.size(); i++) {
				invalidSuggestions.get(i).nextSuggestion();
				suggestions.add(invalidSuggestions.get(i));
			}
			invalidSuggestions.clear();
			
			/* Verifica se alguma sugest�o corresponde a um uffmail j� existente. */
			try (BufferedReader br = new BufferedReader(new FileReader(dataBase))) {
				
				while (!suggestions.isEmpty() && (line = br.readLine()) != null) {
					String[] fields = line.split(csvRegex);
					for (int i = 0; i < suggestions.size(); i++) {
						if (suggestions.get(i).getSuggestion().equals(fields[4])) {
							invalidSuggestions.add(suggestions.remove(i));
						}
					}
				}
				/* Adiciona todas as sugest�es que passaram no teste na lsta de v�lidos 
				 * para que n�o pricisem ser mais testadas
				 */
				validSuggestions.addAll(suggestions);
				suggestions.clear();
			}
			
		}
		while (!invalidSuggestions.isEmpty());
		
		return validSuggestions;
	}

}
