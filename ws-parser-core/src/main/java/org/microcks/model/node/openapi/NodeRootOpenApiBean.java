package org.microcks.model.node.openapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodeRoot;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

//@org.springframework.stereotype.Service
public class NodeRootOpenApiBean extends NodeRoot {

//	public NodeRootOpenApiBean() {}
//	
//	public NodeRootOpenApiBean(String yaml) throws JsonParseException, JsonMappingException, IOException {
//		load("",yaml);
//	}
//	
//	public void load(String yaml) throws JsonParseException, JsonMappingException, IOException {
//		load("",yaml);
//	}
//	
//	public void load(String uri, String yaml) throws JsonParseException, JsonMappingException, IOException {
//		this.name = "/";
//		
//		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
//		HashMap<String,Map> metadata = mapper.readValue(yaml.getBytes(), HashMap.class);
//		
//		NodeMap nMap = new NodeMap(metadata);
//		
//		Node nodeService = new NodeServiceOpenApiBean(this,nMap,uri,yaml);
//		
//		Node nodeServiceToRemove = find(this, nodeService.getName());
//		
//		if(nodeServiceToRemove != null) {
//			Node nodeVersionToRemove = nodeServiceToRemove.get(nodeService.getFirst().getName());
//			if(nodeVersionToRemove != null) {
//				nodeServiceToRemove.get().remove(nodeVersionToRemove);
//			}
//			nodeServiceToRemove.add(nodeService.getFirst());
//		}else {
//			add(nodeService);			
//		}
//		
//	}
	
}