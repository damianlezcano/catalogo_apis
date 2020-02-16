package ar.com.nssa.monitoreo.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ar.com.nssa.monitoreo.domain.Prop;

@Component
@Scope("singleton")
public class PropertyDaoBean {

	final static Logger LOG = LoggerFactory.getLogger(PropertyDaoBean.class);

	private Map mProp;
	
	@PostConstruct
	public void init() {
		Properties prop = loadProp();
		mProp = converToMap(prop);
	}

	private Map converToMap(Properties prop) {
		Map<String,Map> map = new HashMap();
		List<Prop> result = new ArrayList<Prop>();
		Set<Entry<Object, Object>> keys = prop.entrySet();
		
		for (Entry<Object, Object> entry : keys) {
			String key = entry.getKey().toString();
			String[] arr = key.split("\\.");
						
			if(entry.getKey().toString().startsWith("host.")) {
				
				Map host = new HashMap();
				if(map.containsKey(arr[0])) {
					host = (Map) map.get(arr[0]);
				}else{
					map.put(arr[0],host);
				}
				
				Object value = entry.getValue();
				
				int md = arr.length - 1;
				
				if("field".equalsIgnoreCase(arr[arr.length - 2])) {
					md = arr.length - 2;
					Map vm = new HashMap();
					vm.put(arr[arr.length - 1],entry.getValue());
					value = vm;
				}
				
				String uri = key.substring((arr[0]).length()+1, key.lastIndexOf(arr[md])-1);
				
				Map item = new HashMap();
				if(host.containsKey(uri)) {
					item = (Map) host.get(uri);
				}else{
					host.put(uri, item);
				}
				
				item.put(arr[md],value);
				
				host.put(uri,item);
				
			}else{
				converToMap(arr, entry.getValue(), 0, map);
			}
						
		}
		
		return map;
	}
	
	private void converToMap(String[] arr, Object value, int index, Map map){
		if(map.containsKey(arr[index])) {
			if(index < arr.length) {
				converToMap(arr, value, index+1, (Map) map.get(arr[index]));
			}else {
				map.put(arr[index], value);
			}
		}else{
			map.put(arr[index],new HashMap());
			if(index < arr.length-1) {
				converToMap(arr, value, index+1, (Map) map.get(arr[index]));
			}else {
				map.put(arr[index], value);
			}
		}
	}

	private Properties loadProp() {
		Properties properties = new Properties();
		LOG.debug("Iniciando la carga de la properties de mensajes");
	    try {
	    	String filename = System.getProperty("ext.properties.file");
	    	String replaceFilename = filename.replaceAll("file:","");
	    	LOG.info("Prop: Filename: " + filename + " / " + replaceFilename);
	    	FileInputStream in = new FileInputStream(replaceFilename);
	    	properties.load(in);
	    	in.close();
	    } catch (IOException e) {
	        LOG.error("Error cargando el archivo de properties",e);
	    }
	    return properties;
	}

	public Map getmProp() {
		return mProp;
	}
	
}