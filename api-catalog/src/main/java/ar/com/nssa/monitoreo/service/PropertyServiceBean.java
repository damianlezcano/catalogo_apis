
package ar.com.nssa.monitoreo.service;

import java.util.List;

import org.microcks.model.NodeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.nssa.monitoreo.dao.PropertyDaoBean;

@Component
@Scope("singleton")
public class PropertyServiceBean {
	
	static final Logger LOG = LoggerFactory.getLogger(PropertyServiceBean.class);
	
	@Autowired
	private PropertyDaoBean dao;

	public List getWs() {
		NodeMap nMap = new NodeMap(dao.getmProp());
		return nMap.get("ws").list();
	}
		
	public NodeMap getHost(String name) {
		NodeMap nMap = new NodeMap(dao.getmProp());
		return nMap.get("host").get(name);
	}
	
	public List getOCP3x() {
		NodeMap nMap = new NodeMap(dao.getmProp());
		return nMap.get("ocp").get("3x").list();
	}
}