import java.util.ArrayList;

import br.com.f13fabricio.uffmail.*;

public class Main {

	public static void main(String[] args) {
		/*Pega a instancia única da classe ProfileManager*/
		ProfileManager manager = ProfileManager.getProfileManager();
		
		/*Vincula a base de dados ao manager*/
		manager.setDataBase("datasets/alunos.csv");
		
		/*teste do método mapProfile*/
		try {
			StudentProfile profile = manager.mapProfile("105457");
			System.out.print(profile.getUffmail());
			/*teste do método validateUffmailCreationRequest()*/
			System.out.print(manager.validateUffmailCreationRequest(profile));
			/*testar geração de sugestoes */
			System.out.println("por favor escolha uma das opções abaixo para o seu UFFMail");
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
