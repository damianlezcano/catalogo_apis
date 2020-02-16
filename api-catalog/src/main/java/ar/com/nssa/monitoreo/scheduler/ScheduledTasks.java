package ar.com.nssa.monitoreo.scheduler;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.nssa.monitoreo.service.EndpointServiceBean;

@Component
@Scope("singleton")
public class ScheduledTasks {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private EndpointServiceBean specService;
    
    @PostConstruct
    public void init() {
    	try {
			specService.scan();
		} catch (Exception e) {
			LOG.error("Error al ejecutar scheduler",e);
		}
    }
}