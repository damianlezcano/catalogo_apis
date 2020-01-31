package org.microcks.model.node;

import org.microcks.model.NodeMap;
import org.microcks.repository.RepositoryBean;

public abstract class NodeVersion extends Node {

	public RepositoryBean getRepository() {
		return null;
	}

	public NodeMap getSchema() {
		return null;
	}

	public String toYaml() {
		return null;
	}
	
}