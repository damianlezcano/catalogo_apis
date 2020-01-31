package org.microcks.model.node.openapi;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodeService;

public class NodeServiceOpenApiBean extends NodeService {

	public NodeServiceOpenApiBean(Node parent, NodeMap metadata, String yaml) {
		this(parent, metadata, "",yaml);
	}
	
	public NodeServiceOpenApiBean(Node parent, NodeMap metadata, String uri,String yaml) {
		NodeMap info = metadata.get("info");
		String name = info.get("title").str();
		String version = info.get("version").str();
		//-----------------------------------------------------------
		this.uri = uri;
		this.parent = parent;
		this.name = name;
		this.descripcion = null;
		Node nodeVersion = new NodeVersionOpenApiBean(this,version,metadata,yaml);
		add(nodeVersion);
	}
	
	@Override
	public String getType() {
		return "rest";
	}
	
}