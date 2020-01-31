package org.microcks.model.node;

public abstract class NodeService extends Node {
	
	protected String descripcion;
	protected String uri;

	public String getDescription() {
		return descripcion;
	}
	
	public String getUri() {
		return uri;
	}
	
	public String getType() {
		return "";
	}
	
}