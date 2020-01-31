package org.microcks.model.node.swagger;

import java.util.Map;
import java.util.Map.Entry;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodeVersion;

public class NodeVersionSwaggerBean extends NodeVersion {

	private Map schema;
	private StringBuffer swagger;
	
	public NodeVersionSwaggerBean(Node parent, String version, NodeMap metadata, String swagger) {
		this.parent = parent;
		this.swagger = new StringBuffer(swagger);
		this.name = version;
		buildPaths(metadata);
		schema = buildSchema(metadata);
	}

	private void buildPaths(NodeMap metadata) {
		Map paths = metadata.get("paths").map();
		for (Object obj : paths.entrySet()) {
			Entry entry = (Entry) obj;
			String[] path = entry.getKey().toString().split("/");
			Node n = buildPaths(this,quitarPrimerPosicion(path), entry);
			add(n);
		}
	}
	
	private Node buildPaths(Node thiz, String[] path, Entry entry) {
		Node t;
		if(path.length > 1) {
			t = new NodePathSwaggerBean(thiz,path[0]);
			Node nt = buildPaths(t,quitarPrimerPosicion(path),entry);
			t.add(nt);
		}else {
			Node nodePath = new NodePathSwaggerBean(thiz,path[0],(Map)entry.getValue());
			t = nodePath;
		}
		return t;
	}

	private Map buildSchema(NodeMap metadata) {
		NodeMap components = metadata.get("components").get("schemas");
		return components.map();
	}
	
	private String[] quitarPrimerPosicion(String[] array) {
		String[] nArray = new String[array.length-1];
		for (int i = 0; i < array.length-1; i++) {
			nArray[i] = array[i+1];
		}
		return nArray;
	}

	public String toSwagger() {
		return swagger.toString();
	}
	
}