package org.microcks.model.node.swagger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodeMethod;

public class NodeMethodSwaggerBean extends NodeMethod {

	private Map<String,Object> requestBody = new HashMap<String,Object>();
	private Map<String,Object> responses = new HashMap<String,Object>();
	private List parameters;
	
	public NodeMethodSwaggerBean(Node parent, Entry entry) {
		this.parent = parent;
		this.name = (String) entry.getKey();
		NodeMap base = new NodeMap((Map)entry.getValue());
		this.description = base.get("summary").str();
		this.description+= " " + base.get("description").str();
		//--------------------------------------------------------
		if(description.isEmpty()) {description=null;}
		Map mapResponse = base.get("responses").map();
		for (Object code : mapResponse.keySet()) {
			responses.put(code.toString(), buildResponse((Map) mapResponse.get(code)));
		}
		requestBody = buildRequestBody(base.get("requestBody").map());
		parameters = buildParameters(base.get("parameters"));
	}
	
	private List buildParameters(NodeMap nodeMap) {
		try {
			return nodeMap.list();			
		} catch (Exception e) {
			return null;
		}
	}

	private Map<String, Object> buildRequestBody(Map p) {
		Map map = null;
		
		Map content = null;
		try {
			map = new HashMap();
			content = (Map) p.get("content");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Entry contentType = null;
		try {
			contentType = (Entry) content.entrySet().iterator().next();
			map.put("ContentType", contentType.getKey());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Map mapResultType = null;
		try {
			String resultType = (String) ((Map)((Map)contentType.getValue()).get("schema")).get("$ref");
			map.put("resultType",resultType);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return map;
	}

	private Object buildResponse(Map p) {
		Map map = null;
		
		Map content = null;
		try {
			map = new HashMap();
			content = (Map) p.get("content");
		} catch (Exception e) {
			// TODO: handle exception
		}

		Entry contentType = null;
		try {
			contentType = (Entry) content.entrySet().iterator().next();
			map.put("ContentType", contentType.getKey());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Map mapResultType = null;
		try {
			mapResultType = ((Map)((Map)contentType.getValue()).get("schema"));
			String formatString = mapResultType.get("type").equals("array") ? "[%s]" : "%s";
			String resultType = String.format(formatString, ((Map)mapResultType.get("items")).get("$ref"));
			map.put("resultType",resultType);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return map;
	}

	public boolean havePathParam() {
		Node node = parent;
		do {
			if(node instanceof NodePathSwaggerBean && ((NodePathSwaggerBean)node).isPathParam()) {
				return true;
			}
			node = node.getParent();
		} while (node != null);
		return false;
	}

	public Node havePathParam(String name) {
		Node node = parent;
		do {
			if(node instanceof NodePathSwaggerBean && node.getName().equals("{" + name + "}")) {
				return node;
			}
			node = node.getParent();
		} while (node != null);
		return node;
	}	
	
	public Map<String, Object> getRequestBody() {
		return requestBody;
	}
	
	public Map<String, Object> getResponses() {
		return responses;
	}
	
	public List getParameters() {
		List pr = new ArrayList();
		if(parameters != null) {
			pr.addAll(parameters);
		}
		Node node = parent;
		do {
			if(node instanceof NodePathSwaggerBean && ((NodePathSwaggerBean)node).getParameters() != null) {
				NodePathSwaggerBean np = (NodePathSwaggerBean) node;
				pr.addAll(np.getParameters());
			}
			node = node.getParent();
		} while (node != null);
		return pr;
	}

	public boolean haveQueryParam() {
		return getParameters() != null;
	}
	
}