package ar.com.nssa.monitoreo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.microcks.model.NodeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.nssa.monitoreo.domain.Endpoint;
import ar.com.nssa.monitoreo.utils.HTTPClientBean;
import ar.com.nssa.monitoreo.utils.UriFactoryBean;

@Component
@Scope("singleton")
public class OCP3xServiceBean {
	
	static final Logger LOG = LoggerFactory.getLogger(OCP3xServiceBean.class);
	
	static String OCP_API_PATH = "%s/oapi/v1/namespaces/%s/routes";
	static String OCP_API_DC = "%s/oapi/v1/namespaces/%s/deploymentconfigs/%s";
	
	@Autowired
	private PropertyServiceBean propertyService;
	
	@Autowired
	private HTTPClientBean httpClient;

	private ObjectMapper mapper = new ObjectMapper(new JsonFactory());
	
	public List<Endpoint> scan() throws Exception {
		String[] namespaces = new String[]{};
		List<Endpoint> endpoints = new ArrayList<Endpoint>();		

		List ocpConfig = propertyService.getOCP3x();
		
		for (Object obj1 : ocpConfig) {
			Entry entry = (Entry) obj1;
			
			NodeMap conf = new NodeMap(entry.getValue());
			
			namespaces = conf.get("namespaces").str().split(",");

			for (String namespace : namespaces) {
				
				String ocpUrl = conf.get("uri").str();
				String token = conf.get("token").str();
				String uriRoutes = String.format(OCP_API_PATH, ocpUrl,namespace);
				
				String jsonRoutes = httpClient.get(uriRoutes,token);
				HashMap<String,Map> metadata = jsonToMap(jsonRoutes);
				
				NodeMap nMap = new NodeMap(metadata);
				
				for(Object obj2 : nMap.get("items").list()) {
					
					NodeMap item = new NodeMap((Map) obj2);
					
					String host = item.get("spec").get("host").str();
					String tls = item.get("spec").get("tls").str(); tls = tls.isEmpty() ? "" : "s";
					//--------------------------------------------------
					String uri = String.format("http%s://%s", tls,host);
					
					System.out.println("host."+host+".visible=false");
					
					NodeMap nHost = propertyService.getHost(host);
					
					Endpoint e = new Endpoint();
					e.setOpenshift(entry.getKey() + " - OCP v3.x");
					
					String[] labels = null;
					if(nHost.r() != null) {
						String bv = nHost.get("visible").str(); 
						Boolean visible = isNil(bv) ? true : "true".equalsIgnoreCase(bv);
						e.setVisible(visible);
						if(visible) {
							String f = nHost.get("file").str();
							String file = f != null && !f.isEmpty() ? f : null;
							uri = UriFactoryBean.create(uri,nHost.get("uri").str());
							String description = nHost.get("description").toString();
							
							String sl = nHost.get("labels").str();
							if(sl == null || (sl != null && sl.isEmpty())) {
								labels = procesarEtiquetas(item,ocpUrl,namespace,token);
							}else{
								labels = sl.split(",");							
							}
							
							e.setProp(nHost);
							e.setUri(uri);
							e.setFile(file);
							e.setDescription(description);
							e.setLabels(labels);
						}
						
					}else{
						labels = procesarEtiquetas(item,ocpUrl,namespace,token);
						e.setLabels(labels);
						e.setVisible(true);
						e.setUri(uri);
					}
					
					if(e.getVisible()) {
						endpoints.add(e);						
					}
					
				}
				
			}
		}
			
		return endpoints;
	}

	private HashMap jsonToMap(String jsonRoutes) throws IOException, JsonParseException, JsonMappingException {
		return mapper.readValue(jsonRoutes.getBytes(), HashMap.class);
	}
	
	private String[] procesarEtiquetas(NodeMap item, String ocpUrl, String namespace, String token) throws Exception {
		String dcName = item.get("metadata").get("name").str();
		String uriDc = String.format(OCP_API_DC, ocpUrl,namespace,dcName);
		String jsonDc = httpClient.get(uriDc, token);
		//---------------------------------------------
		HashMap<String,Map> metadata = jsonToMap(jsonDc);
		NodeMap conf = new NodeMap(metadata);
		List listLabel = conf.get("spec").get("template").get("metadata").get("labels").list();
		List<String> labels = new ArrayList<String>();
		for (Object obj : listLabel) {
			Entry entry = (Entry) obj;
			labels.add(entry.getKey()+":"+entry.getValue());
		}
		return labels.toArray(new String[]{});
	}

	private boolean isNil(String v) {
		
		boolean b = false;
		
		if(v == null) {
			b = true;
		}else {
			b = v.isEmpty();
		}
		
		return b;
		
	}
	
	public static void main(String[] args) throws Exception {
		OCP3xServiceBean pc = new OCP3xServiceBean();
		pc.scan();
	}
	
}