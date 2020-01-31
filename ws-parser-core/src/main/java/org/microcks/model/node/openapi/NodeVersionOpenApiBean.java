package org.microcks.model.node.openapi;

import java.util.Map;
import java.util.Map.Entry;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodeVersion;
import org.microcks.repository.RepositoryBean;
import org.microcks.repository.RepositoryFactoryBean;

public class NodeVersionOpenApiBean extends NodeVersion {

	private Map schema;
	private StringBuffer yaml;
	
	private RepositoryBean repository;
	
	public NodeVersionOpenApiBean(Node parent, String version, NodeMap metadata, String yaml) {
		this.parent = parent;
		this.yaml = new StringBuffer(yaml);
		this.name = version;
		buildPaths(metadata);
		schema = buildSchema(metadata);
		repository = RepositoryFactoryBean.get(getPath());
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
			t = new NodePathOpenApiBean(thiz,path[0]);
			Node nt = buildPaths(t,quitarPrimerPosicion(path),entry);
			t.add(nt);
		}else {
			Node nodePath = new NodePathOpenApiBean(thiz,path[0],(Map)entry.getValue());
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

	public RepositoryBean getRepository() {
		return repository;
	}

	public NodeMap getSchema() {
		return new NodeMap(schema);
	}
	
	public void setRepository(RepositoryBean repository) {
		this.repository = repository;
	}

	public String toYaml() {
		return yaml.toString();
	}
	
}