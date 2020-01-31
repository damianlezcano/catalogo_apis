package ar.com.nssa.monitoreo.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Scope(value="application")
public class HTTPClientBean {

	@Autowired
	private PropertyUtilBean prop;
	
	private RestTemplate restTemplate;
	
	@PostConstruct
	public void init() {
		restTemplate = new RestTemplate();
		Integer readTimeout = Integer.valueOf(prop.get("restTemplate.readTimeout"));
		Integer connectTimeout = Integer.valueOf(prop.get("restTemplate.connectTimeout"));
		//---------------------------------------------------------------------------------------
		SimpleClientHttpRequestFactory factory = (SimpleClientHttpRequestFactory)restTemplate.getRequestFactory();
		factory.setReadTimeout(readTimeout);
		factory.setConnectTimeout(connectTimeout);
	}
	
	public String get(String url) {
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		return response.getBody();
	}
	
}
