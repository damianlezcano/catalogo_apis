package org.microcks.model.node.wsdl;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodeService;

public class NodeServiceWsdlBean extends NodeService {
	
	public NodeServiceWsdlBean(Node parent, NodeMap metadata, String wsdl) {
		this(parent, metadata, "", wsdl);
	}
	
	public NodeServiceWsdlBean(Node parent, NodeMap metadata, String uri, String wsdl) {
		String name = metadata.get("service").get("name").str();
		String version = "latest";
		//-----------------------------------------------------------
		this.uri = uri;
		this.parent = parent;
		this.name = name;
		this.descripcion = metadata.get("documentation").str();
		Node nodeVersion = new NodeVersionWsdlBean(this,version,metadata,wsdl);
		add(nodeVersion);
	}
	
	@Override
	public String getDescription() {
		return descripcion;
	}
	
	@Override
	public String getType() {
		return "soap";
	}
	
}