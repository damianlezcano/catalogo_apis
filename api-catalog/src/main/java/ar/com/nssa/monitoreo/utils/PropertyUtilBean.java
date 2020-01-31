package ar.com.nssa.monitoreo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.nssa.monitoreo.domain.Prop;

@Component
@Scope(value="application")
public class PropertyUtilBean {
	
	final static Logger LOG = LoggerFactory.getLogger(PropertyUtilBean.class);

	private Properties properties;
	
	public PropertyUtilBean() {
		LOG.debug("Iniciando la carga de la properties de mensajes");
	    try {
	    	String filename = System.getProperty("ext.properties.file");
	    	String replaceFilename = filename.replaceAll("file:","");
	    	LOG.info("Prop: Filename: " + filename + " / " + replaceFilename);
	    	properties = new Properties();
	    	FileInputStream in = new FileInputStream(replaceFilename);
	    	properties.load(in);
	    	in.close();
	    } catch (IOException e) {
	        LOG.error("Error cargando el archivo de properties",e);
	    }
	}
	
	public String get(String key){
		return properties.getProperty(key);
	}

	public String get(String key, String def){
		String val = get(key);
		if(val == null){
			return def;
		}else{
			return val;
		}
	}
	
	public List<Prop> list(String key){
		List<Prop> result = new ArrayList<Prop>();
		Enumeration<Object> keys = properties.keys();
		while (keys.hasMoreElements()) {
			Object object = (Object) keys.nextElement();
			if(object.toString().indexOf(key) != -1){
				String value = object.toString();
				String name = value.replaceAll(key + ".", "");
				String url = properties.getProperty(value);
				String cx = "";
				if(name.indexOf("[") != -1){
					cx = name.substring(name.indexOf("[")+1, name.indexOf("]"));
					name = name.substring(0, name.indexOf("["));
				}
				result.add(new Prop(name,url,cx));
			}
		}
		return result;
	}
	
}