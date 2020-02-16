package ar.com.nssa.monitoreo.domain;

public class Uri {
	
	protected String value;

	public Uri() {}
	
	public Uri(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean isFile(String url) {
		return false;
	}
}
