package org.microcks.model.node.wsdl;

import java.util.List;
import java.util.Map;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodePath;

public class NodePathWsdlBean extends NodePath {

	private List parameters;
	
	public NodePathWsdlBean(Node parent, String key) {
		this.parent = parent;
		this.name = key;
	}

	public NodePathWsdlBean(Node parent, String name, Map map) {
		this(parent,name);
		add(new NodeMethodWsdlBean(this,new NodeMap(map)));
	}

	public Node get(String value) {
		Node r = super.get(value);
		if(r == null) {
			for (Node node : nodes) {
				if(node.getName().startsWith("{")) {
					r = node;
					break;
				}
			}
		}
		return r;
	}

	public List getParameters() {
		return parameters;
	}
	
	public boolean isPathParam() {
		return name.startsWith("{") && name.endsWith("}");
	}
	
}