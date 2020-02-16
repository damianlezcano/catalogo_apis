package ar.com.nssa.monitoreo.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.nssa.monitoreo.domain.Auth;
import ar.com.nssa.monitoreo.domain.MyAuthenticator;

@Component
@Scope(value="singleton")
public class HTTPClientBean {

	@Value("${restTemplate.readTimeout}")
	private Integer readTimeout;

	@Value("${restTemplate.connectTimeout}")
	private Integer connectTimeout;
		
	@PostConstruct
	public void init(){
		try {
		
	        // Create a trust manager that does not validate certificate chains
	        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
	                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                    return null;
	                }
	                public void checkClientTrusted(X509Certificate[] certs, String authType) {
	                }
	                public void checkServerTrusted(X509Certificate[] certs, String authType) {
	                }
	            }
	        };
	 
	        // Install the all-trusting trust manager
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	 
	        // Create all-trusting host name verifier
	        HostnameVerifier allHostsValid = new HostnameVerifier() {
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        };
	 
	        // Install the all-trusting host verifier
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	        

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public String get(String path) throws Exception {
		return get(path, null, null);
	}

	public String get(String path, Auth auth) throws Exception {
		MyAuthenticator authenticator = auth.buildAuthenticator();
		return get(path,null,authenticator);
	}
	
	public String get(String path, String token) throws Exception {
		return get(path,token,null);
	}
	
	public String get(String path, String token, MyAuthenticator auth) throws Exception {
		
		Authenticator.setDefault(auth);			
		
		String response = "";
		try {
			URL url = new URL(path);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setRequestMethod("GET");
			if(token != null) {
				conn.setRequestProperty("Authorization", "Bearer " + token);				
			}
			conn.setDoOutput(true);
			
			int statusCode = conn.getResponseCode();
			if (statusCode >= 200 && statusCode < 500) {
				response = extract(conn.getInputStream());
			}else {
				response = extract(conn.getErrorStream());
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error en la llamada GET");
		}
		return response;
	}

	private String extract(InputStream is) throws Exception {
		Reader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		StringBuilder data = new StringBuilder();
		for (int c; (c = in.read()) >= 0;) {
			data.append((char) c);
		}
		return data.toString();
	}
	
}
