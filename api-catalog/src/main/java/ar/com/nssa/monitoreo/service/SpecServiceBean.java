package ar.com.nssa.monitoreo.service;

import java.util.List;

import org.microcks.model.node.NodeRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.nssa.monitoreo.dao.SpecDaoBean;
import ar.com.nssa.monitoreo.domain.Prop;
import ar.com.nssa.monitoreo.utils.PropertyUtilBean;

@Component
public class SpecServiceBean {
	
	static final Logger LOG = LoggerFactory.getLogger(SpecServiceBean.class);
	
	@Autowired
	private PropertyUtilBean prop;
	
	@Autowired
	private SpecDaoBean dao;

	public void scan() throws Exception {
		List<Prop> endpoints = prop.list("spec.endpoint");
		for (Prop prop : endpoints) {
			dao.save(prop.getUrl());
		}
	}
	
	public NodeRoot getNodeRoot() {
		return dao.getNodeRoot();
	}
	
}