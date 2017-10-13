package br.com.f13fabricio.uffmail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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

}
