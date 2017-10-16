package br.com.f13fabricio.uffmail;

import java.util.ArrayList;

public class StudentProfile {
	
	public static final String ACTIVE = "Ativo";
	public static final String INACTIVE = "Inativo";
	
	private ArrayList<UsernameSuggestion> usernameSuggestions;

	private String enrollment;
	private String name;
	private String phone;
	private String email;
	private String uffmail;
	private String status;
	
	public StudentProfile(
			String name,
			String enrollment,
			String phone,
			String email,
			String uffmail,
			String status) {
		
		this.name = name;
		this.enrollment = enrollment;
		this.phone = phone;
		this.email = email;
		this.uffmail = uffmail;
		this.status = status;
	}
	
	/*Cada posição do array 'fields' corresponte a uma informação do perfil
	 * 0 -> Nome
	 * 1 -> Matrícula
	 * 2 -> telefone
	 * 3 -> email
	 * 4 -> uffmail
	 * 5 -> status
	 */
	public StudentProfile(String[] fields) {
		this(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
	}
	
	public String getName() {
		return name;
	}
	
	public String getEnrollment() {
		return enrollment;
	}
	
	public String getUffmail() {
		return uffmail;
	}
	
	public void setUffmail(int index) {
		uffmail = usernameSuggestions.get(index).getSuggestion();
	}
	
	public String getStatus() {
		return status;
	}
	
	/* Retorna uma string com os dados do perfil na formatação do aquivo csv */
	public String getFormattedProfile() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(",");
		sb.append(enrollment);
		sb.append(",");
		sb.append(phone);
		sb.append(",");
		sb.append(email);
		sb.append(",");
		sb.append(uffmail);
		sb.append(",");
		sb.append(status);
		return sb.toString();
	}
	
	public ArrayList<UsernameSuggestion> getUsernameSuggestion() {
		return usernameSuggestions;
	}
	
	public void setUsernameSuggestion(ArrayList<UsernameSuggestion> suggestion) {
		usernameSuggestions = suggestion;
	}
}
