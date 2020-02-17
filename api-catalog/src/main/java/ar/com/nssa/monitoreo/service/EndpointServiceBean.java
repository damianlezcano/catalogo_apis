package ar.com.nssa.monitoreo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.microcks.model.node.NodeMethod;
import org.microcks.model.node.NodeRoot;
import org.microcks.model.node.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.nssa.monitoreo.dao.EndpointDaoBean;
import ar.com.nssa.monitoreo.domain.Auth;
import ar.com.nssa.monitoreo.domain.Endpoint;
import ar.com.nssa.monitoreo.utils.FileUtilBean;
import ar.com.nssa.monitoreo.utils.HTTPClientBean;

@Component
@Scope("singleton")
public class EndpointServiceBean {
	
	static final Logger LOG = LoggerFactory.getLogger(EndpointServiceBean.class);
	
    @Autowired
    private WsServiceBean wsService;

    @Autowired
    private OCP3xServiceBean ocp3xService;
	
	@Autowired
	private EndpointDaoBean dao;
	
	@Autowired
	private HTTPClientBean httpClient;
	
	private NodeRoot nodeRoot = new NodeRoot();
	
	public void scan() throws Exception {
		
		//Endpoint ocp
		List<Endpoint> lo = ocp3xService.scan();
		
		//Endpoint harcord
		List<Endpoint> lws = wsService.scan() /*new ArrayList()*/;
		
		//---------------------------------------
		
		lws.addAll(lo);
		
		for (Endpoint e : lws) {
			dao.save(e);
		}
		
		//---------------------------------------
		
		List<Endpoint> le = dao.list();
		
		for (Endpoint ep : le) {
			String content = null;
			try {
				if(ep.getFile() != null) {
					content = FileUtilBean.read(ep.getFile());
				}else{
					Auth auth = ep.getAuth();
					if(auth != null) {
						content = httpClient.get(ep.getUri(),ep.getAuth());					
					}else{
						content = httpClient.get(ep.getUri());	
					}					
				}
			} catch (Exception e) {
				ep.setError(e.getMessage());
				LOG.error("Error obteniendo el documento {} -> {}",ep,e);
			}
			try {
				NodeService ns = nodeRoot.load(content);
				ep.setNs(ns);
			} catch (Exception e) {
				ep.setError("Error de procesamiento en la definición de la API");
				LOG.error("Error procesando el documento {} -> {}",ep,e);
			}
		}

	}

	public List<Endpoint> list(){
		List<Endpoint> l = new ArrayList<Endpoint>();
		for(Endpoint e : dao.list()) {
			if(e.getVisible() == null || e.getVisible() == true) {
				l.add(e);
			}
		}
		return l;
	}

	public List<Endpoint> list(String f) {
		List<Endpoint> l = new ArrayList<Endpoint>();
		if( f == null ) {
			l = list();
		}else{
			for(Endpoint e : list()) {
				String s = e.getUri() + e.getDescription() + e.getOpenshift() + e.getType() + Arrays.toString(e.getLabels()) + e.getProp();
				
				for(NodeMethod nm : e.getAllNodeMethod()) {
					s += nm.getPath();
				}
				
				if(s.toLowerCase().contains(f.toLowerCase())) {
					l.add(e);
				}
			}
		}
		return l;
	}	
}