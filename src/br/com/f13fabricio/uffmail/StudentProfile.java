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
	
	public StudentProfile(String enrollment,
						  String name,
						  String phone,
						  String email,
						  String uffmail,
						  String status) {
		
		this.enrollment = enrollment;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.uffmail = uffmail;
		this.status = status;
	}
	
	public String getEnrollment() {
		return enrollment;
	}
	
	public String getUffmail() {
		return enrollment;
	}
	
	public String getStatus() {
		return status;
	}
}
