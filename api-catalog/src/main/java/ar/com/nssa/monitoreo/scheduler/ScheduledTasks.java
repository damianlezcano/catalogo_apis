package ar.com.nssa.monitoreo.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ar.com.nssa.monitoreo.service.SpecServiceBean;

@Component
public class ScheduledTasks {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    static boolean r = false;
    
    @Autowired
    private SpecServiceBean specService;

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
    	try {
    		if(!r) {
    			LOG.info("Start {}", dateFormat.format(new Date()));
    			specService.scan();
    			LOG.info("End {}", dateFormat.format(new Date()));
    			r = true;
    		}
		} catch (Exception e) {
			LOG.error("Error al ejecutar scheduler",e);
		}
    }
}