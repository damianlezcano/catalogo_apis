package org.microcks.model.node.wsdl;

import java.util.List;
import java.util.Map;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodeVersion;

public class NodeVersionWsdlBean extends NodeVersion {

	private Map schema;
	private StringBuffer wsdl;
	
	public NodeVersionWsdlBean(Node parent, String version, NodeMap metadata, String wsdl) {
		this.parent = parent;
		this.wsdl = new StringBuffer(wsdl);
		this.name = version;
		buildPaths(metadata);
		schema = buildSchema(metadata);
	}

	private void buildPaths(NodeMap metadata) {
		List paths = metadata.get("portType").get("operation").list();
		for (Object obj : paths) {
			Map map = (Map) obj;
			String p = map.get("name").toString();
			Node nodePath = new NodePathWsdlBean(this,p,map);
			add(nodePath);
		}
		
	}

	private Map buildSchema(NodeMap metadata) {
//		NodeMap components = metadata.get("types").get("schema");
//		return components.map();
		return null;
	}

	public String toWsdl() {
		return wsdl.toString();
	}
	
}