package ar.com.nssa.monitoreo.dao;

import org.microcks.model.node.NodeRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.nssa.monitoreo.utils.HTTPClientBean;

@Component
@Scope("singleton")
public class SpecDaoBean {

	static final Logger LOG = LoggerFactory.getLogger(SpecDaoBean.class);

	@Autowired
	private HTTPClientBean httpClient;
	
	private NodeRoot nodeRoot = new NodeRoot();
	
	public void save(String uri) throws Exception {
		try {
			String content = httpClient.get(uri);
			nodeRoot.load(uri,content);
		} catch (Exception e) {
			LOG.error("Error al procesando -> " + uri);
		}
	}

	public NodeRoot getNodeRoot() {
		return nodeRoot;
	}
	
}