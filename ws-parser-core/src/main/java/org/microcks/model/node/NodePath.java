package org.microcks.model.node;

import java.util.List;

public abstract class NodePath extends Node {

	public List getParameters() {
		return null;
	}
	
	public boolean isPathParam() {
		return false;
	}
	
}