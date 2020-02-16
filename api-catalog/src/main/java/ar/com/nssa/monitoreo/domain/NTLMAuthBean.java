package ar.com.nssa.monitoreo.domain;

public class NTLMAuthBean extends Auth {

	@Override
	public MyAuthenticator buildAuthenticator() {
		return new MyAuthenticator(username, password);
	}

}