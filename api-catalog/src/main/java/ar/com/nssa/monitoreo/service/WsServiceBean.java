package ar.com.nssa.monitoreo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.microcks.model.NodeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.nssa.monitoreo.domain.Endpoint;
import ar.com.nssa.monitoreo.domain.NTLMAuthBean;

@Component
@Scope("singleton")
public class WsServiceBean {
	
	static final Logger LOG = LoggerFactory.getLogger(WsServiceBean.class);
	
	@Autowired
	private PropertyServiceBean propertyService;

	public List<Endpoint> scan() {
		List<Endpoint> l = new ArrayList<Endpoint>();
		
		List conf = propertyService.getWs();
		
		for (Object obj : conf) {
			Entry entry = (Entry) obj;
			NodeMap map = new NodeMap(entry.getValue());
			
			String bv = map.get("visible").str(); 
			Boolean visible = bv == null ? true : "true".equalsIgnoreCase(bv);
						
			if(visible) {
				String uri = map.get("uri").str();
				String file = map.get("file").str();
				file = (file != null & !file.isEmpty()) ? file : null;
				String description = map.get("description").str();
				String sl = map.get("labels").str(); 
				String[] labels = (sl != null && !sl.isEmpty()) ? sl.split(",") : null;
				NodeMap auth = map.get("auth");

				Endpoint e = new Endpoint();
				e.setUri(uri);
				e.setVisible(visible);
				e.setFile(file);
				e.setDescription(description);
				e.setLabels(labels);
				e.setProp(map);
				
				if(auth.r() != null) {
					if(auth.get("ntlm").r() != null) {
						NTLMAuthBean ntlm = new NTLMAuthBean();
						ntlm.setUsername(auth.get("ntlm").get("username").str());
						ntlm.setPassword(auth.get("ntlm").get("password").str());
						e.setAuth(ntlm);						
					}
				}
				
				l.add(e);				
			}
		}
		
		return l;
	}
	
}