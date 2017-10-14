import java.util.ArrayList;

import br.com.f13fabricio.uffmail.*;

public class Main {

	public static void main(String[] args) {
		/*Pega a instancia �nica da classe ProfileManager*/
		ProfileManager manager = ProfileManager.getProfileManager();
		
		/*Vincula a base de dados ao manager*/
		manager.setDataBase("datasets/alunos.csv");
		
		/*teste do m�todo mapProfile*/
		try {
			StudentProfile profile = manager.mapProfile("105457");
			System.out.print(profile.getUffmail());
			/*teste do m�todo validateUffmailCreationRequest()*/
			System.out.print(manager.validateUffmailCreationRequest(profile));
			/*testar gera��o de sugestoes */
			System.out.println("por favor escolha uma das op��es abaixo para o seu UFFMail");
			profile.setUsernameSuggestion(manager.getUsernameSuggestions(profile.getName()));
			for (UsernameSuggestion s : profile.getUsernameSuggestion()) {
				System.out.println(s.getSuggestion());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
