package br.com.f13fabricio.uffmail;

public class UsernameSuggestion {
	
	public static final String DOMAIN = "@id.uff.br";
	
	private String username;
	private int count;
	
	public UsernameSuggestion(String username) {
		this.username = username;
		this.count = 0;
	}
	
	/*
	 * Retorna a sugest�o de uffmail formada pelo nome de usuario e o dominio
	 * caso count seje diferente de zero o count ser� concatenado com a sugest�o
	 */
	public String getSuggestion() {
		String aux = new String(username);
		if (count != 0)
			aux += count;
		return aux + UsernameSuggestion.DOMAIN;
	}
	 /* Retorna a pr�xima sugest�o */
	public String nextSuggestion() {
		count++;
		return getSuggestion();
	}

}
