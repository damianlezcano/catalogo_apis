package ar.com.nssa.monitoreo.domain;

public class Clave {

	private String key;
	private String value;
	
	public Clave() {}
	
	public Clave(String key, String value){
		this.setKey(key);
		this.setValue(value);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public Double getValueAsDouble() {
		try {
			return Double.valueOf(value);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Integer getValueAsInteger() {
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			return null;
		}
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("%s: %s",key,value);
	}
	
}