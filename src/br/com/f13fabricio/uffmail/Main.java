package br.com.f13fabricio.uffmail;

public class Main {

	public static void main(String[] args) {
		/*Pega a instancia �nica da classe ProfileManager*/
		ProfileManager manager = ProfileManager.getProfileManager();
		
		/*Vincula a base de dados ao manager*/
		manager.setDataBase("datasets/alunos.csv");
		
		/*teste do m�todo mapProfile*/
		try {
			StudentProfile profile = manager.mapProfile("105473");
			System.out.print(profile.getEnrollment());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
