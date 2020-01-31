package org.microcks.model.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;

public abstract class NodeMethod extends Node {

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
	
}