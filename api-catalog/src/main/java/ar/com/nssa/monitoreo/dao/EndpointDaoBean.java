package ar.com.nssa.monitoreo.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.nssa.monitoreo.domain.Endpoint;

@Component
@Scope("singleton")
public class EndpointDaoBean {

	static final Logger LOG = LoggerFactory.getLogger(EndpointDaoBean.class);

	private List<Endpoint> l = new ArrayList<Endpoint>();
	
	public void save(Endpoint e) {
		l.add(e);
	}
	
	public List<Endpoint> list(){
		return l;
	}
	
}