package org.microcks.model.node.wsdl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodeMethod;

public class NodeMethodWsdlBean extends NodeMethod {

	private Map<String,Object> requestBody = new HashMap<String,Object>();
	private Map<String,Object> responses = new HashMap<String,Object>();
	private List parameters = new ArrayList();
	
	public NodeMethodWsdlBean(NodePathWsdlBean parent, NodeMap nodeMap) {
		this.parent = parent;
		this.name = "post";
		buildResponse(nodeMap);
		buildParameters(nodeMap);
	}
		
	private void buildResponse(NodeMap nodeMap) {
		this.responses.put("output", nodeMap.get("output"));
	}

	private void buildParameters(NodeMap nodeMap) {
		Object input = nodeMap.get("input");
		this.parameters.add(input);
	}

	public boolean havePathParam() {
		Node node = parent;
		do {
			if(node instanceof NodePathWsdlBean && ((NodePathWsdlBean)node).isPathParam()) {
				return true;
			}
			node = node.getParent();
		} while (node != null);
		return false;
	}

	public Node havePathParam(String name) {
		Node node = parent;
		do {
			if(node instanceof NodePathWsdlBean && node.getName().equals("{" + name + "}")) {
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
			if(node instanceof NodePathWsdlBean && ((NodePathWsdlBean)node).getParameters() != null) {
				NodePathWsdlBean np = (NodePathWsdlBean) node;
				pr.addAll(np.getParameters());
			}
			node = node.getParent();
		} while (node != null);
		return pr;
	}

	public boolean haveQueryParam() {
		return getParameters() != null;
	}
	
//	@Override
//	public String getPath() {
//		return super.getPath() + " -> ("+ parameters +")";
//	}
	
}