package org.microcks.model.node;

import java.util.List;
import java.util.Map;

public abstract class NodeMethod extends Node {

	protected String description;
	
	public boolean havePathParam() {
		return false;
	}

	public Node havePathParam(String name) {
		return null;
	}	
	
	public Map<String, Object> getRequestBody() {
		return null;
	}
	
	public Map<String, Object> getResponses() {
		return null;
	}
	
	public List getParameters() {
		return null;
	}

	public boolean haveQueryParam() {
		return false;
	}

	public NodeVersion getNodeVersion() {
		return null;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}