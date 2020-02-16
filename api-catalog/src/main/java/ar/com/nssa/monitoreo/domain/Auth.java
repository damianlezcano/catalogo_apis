package ar.com.nssa.monitoreo.domain;

public abstract class Auth {

	protected String username;
	protected String password;
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public abstract MyAuthenticator buildAuthenticator();
}