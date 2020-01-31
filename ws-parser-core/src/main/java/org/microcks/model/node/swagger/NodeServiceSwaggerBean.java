package org.microcks.model.node.swagger;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodeService;

public class NodeServiceSwaggerBean extends NodeService {

	public NodeServiceSwaggerBean(Node parent, NodeMap metadata, String swagger) {
		this(parent, metadata, "", swagger);
	}
	
	public NodeServiceSwaggerBean(Node parent, NodeMap metadata, String uri, String swagger) {
		NodeMap info = metadata.get("info");
		String name = info.get("title").str();
		String version = info.get("version").str();
		String description = info.get("description").str();
		//-----------------------------------------------------------
		this.uri = uri;
		this.parent = parent;
		this.name = name;
		this.descripcion = description;
		Node nodeVersion = new NodeVersionSwaggerBean(this,version,metadata,swagger);
		add(nodeVersion);
	}
	
	@Override
	public String getType() {
		return "rest";
	}
	
}