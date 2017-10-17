import java.util.ArrayList;
import java.util.Scanner;

import br.com.f13fabricio.uffmail.*;

public class Main {

	public static void main(String[] args) {
		/*Pega a instancia �nica da classe ProfileManager*/
		ProfileManager manager = ProfileManager.getProfileManager();
		
		/*Vincula a base de dados ao manager*/
		manager.setDataBase("datasets/alunos.csv");
		Scanner scanner = new Scanner(System.in);
		
		/*teste do m�todo mapProfile*/
		try {
			System.out.println("Digite sua matr�cula:");
			/* L� a matr�cula e mapeia o perfil */ 
			StudentProfile profile = manager.mapProfile(scanner.next());
			
			if (manager.validateUffmailCreationRequest(profile)) {
				String name = profile.getName().split(" ")[0];
				System.out.printf("%s por favor escolha uma das op��es abaixo para o seu UFFMail\n", name);
				profile.setUsernameSuggestion(manager.getUsernameSuggestions(profile.getName()));
				
				ArrayList<UsernameSuggestion> suggestions = profile.getUsernameSuggestion();
				for (int i = 0; i < suggestions.size(); i++) {
					System.out.printf("%d - %s\n\n ", (i + 1), suggestions.get(i).getSuggestion());
				}
				/* Pega a do usuario a op��o de uffmail */
				profile.setUffmail(scanner.nextInt() - 1);
				/* Salva no arquivo o perfil com as modifica��es */
				manager.storeProfile(profile);
				System.out.printf("A cria��o de seu e-mail (%s) ser� feita nos pr�ximos minutos.\n", profile.getUffmail());
				System.out.printf("Um SMS foi enviado para %s com a sua senha de acesso.", profile.getPhone());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
