package br.com.f13fabricio.uffmail;

public class StudentProfile {
	
	public static final String ACTIVE = "Ativo";
	public static final String INACTIVE = "Inativo";

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
	
	public String getEnrollment() {
		return enrollment;
	}
	
	public String getUffmail() {
		return uffmail;
	}
	
	public String getStatus() {
		return status;
	}
}
