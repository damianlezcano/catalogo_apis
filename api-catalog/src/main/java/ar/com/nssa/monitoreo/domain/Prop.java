package ar.com.nssa.monitoreo.domain;

public class Prop {

	private String name;
	private String url;
	private String cx;
	
	public Prop() {}
	
	public Prop(String url, String cx){
		this.url = url;
		this.cx = cx;
	}

	public Prop(String name, String url, String cx){
		this.name = name;
		this.url = url;
		this.cx = cx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}
	
	@Override
	public String toString() {
		return String.format("%s[%s]", url,cx);
	}

}