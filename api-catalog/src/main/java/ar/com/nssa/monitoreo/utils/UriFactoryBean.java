package ar.com.nssa.monitoreo.utils;

public class UriFactoryBean {
	
	public static String create(String sUri, String sUri2) {
		if(sUri2.startsWith("http://") || sUri2.startsWith("https://")) {
			return sUri2;
		}else {
			return sUri + sUri2;
		}
	}

}
