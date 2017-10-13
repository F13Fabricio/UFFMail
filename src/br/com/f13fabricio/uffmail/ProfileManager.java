package br.com.f13fabricio.uffmail;

/* 
 * O aluno pode est� com o status Ativo ou Inativo.
 * Somente o status como Ativo permite ao estudante solicite a cria��o de um UFFMail.
 */

public class ProfileManager {
	
	private static ProfileManager profileManager;
	
	private String dataBase;
	
	private ProfileManager() { }
	
	public static ProfileManager getProfileManager() {
		if (profileManager == null)
			profileManager = new ProfileManager();
		
		return profileManager;
	}
	
	public void setDataBase(String csvFile) {
		dataBase = csvFile;
	}

}
