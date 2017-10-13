package br.com.f13fabricio.uffmail;

public class ProfileManager {
	
	private static ProfileManager profileManager;
	
	private ProfileManager() { }
	
	public static ProfileManager getProfileManager() {
		if (profileManager == null)
			profileManager = new ProfileManager();
		
		return profileManager;
	}

}
