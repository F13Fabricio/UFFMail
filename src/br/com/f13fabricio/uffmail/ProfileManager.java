package br.com.f13fabricio.uffmail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* 
 * O aluno pode est� com o status Ativo ou Inativo.
 * Somente o status como Ativo permite ao estudante solicite a cria��o de um UFFMail.
 */

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
		/*Se a base de dados n�o informada � lan�ada uma NullPoiterException*/
		if (dataBase == null) 
			throw new NullPointerException("Profile Manager n�o vinculado com uma base de dados.");
		
		try (BufferedReader br = new BufferedReader(new FileReader(dataBase))) {
			
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(csvRegex);
				return new StudentProfile(fields);
			}
		}
	}

}
