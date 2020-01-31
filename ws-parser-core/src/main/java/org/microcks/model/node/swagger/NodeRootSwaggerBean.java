package org.microcks.model.node.swagger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodeRoot;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@org.springframework.stereotype.Service
public class NodeRootSwaggerBean extends NodeRoot {

//	public NodeRootSwaggerBean() {}
//	
//	public NodeRootSwaggerBean(String swagger) throws JsonParseException, JsonMappingException, IOException {
//		load("",swagger);
//	}
//	
//	public void load(String swagger) throws JsonParseException, JsonMappingException, IOException {
//		load("",swagger);
//	}
//	
//	public void load(String uri, String swagger) throws JsonParseException, JsonMappingException, IOException {
//		this.name = "/";
//		
//		ObjectMapper mapper = new ObjectMapper(new JsonFactory());
//		HashMap<String,Map> metadata = mapper.readValue(swagger.getBytes(), HashMap.class);
//		
//		NodeMap nMap = new NodeMap(metadata);
//		
//		Node nodeService = new NodeServiceSwaggerBean(this,nMap,uri,swagger);
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