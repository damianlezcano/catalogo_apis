package ar.com.nssa.monitoreo.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.microcks.model.NodeMap;
import org.microcks.model.node.NodeMethod;
import org.microcks.model.node.NodeService;

public class Endpoint {

	private String uri;
	private String file;
	private Boolean visible;
	private String description;
	private String[] labels;
	private String error;
	private NodeService ns;
	private String openshift;
	private Date date;
	private Auth auth;
	private NodeMap prop;
	
	@Override
	public String toString() {
		return String.format("%s", uri);
	}
	
	public String getUri() {
		return uri;
	}

	public String getFile() {
		return file;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NodeService getNs() {
		return ns;
	}

	public void setNs(NodeService ns) {
		this.ns = ns;
	}
	
	public String getDescription() {
		if((description == null || description != null && description.isEmpty()) && ns != null) {
			return ns.getDescription();
		}
		return description;
	}
	
	public String getType() {
		if(ns == null) {
			return "error";
		}
		return ns.getType();
	}
	
	public List<NodeMethod> getAllNodeMethod(){
		List<NodeMethod> ls = new ArrayList<NodeMethod>();
		try {
			ls = ns.getAllNodeMethod();
			for (NodeMethod nodeMethod : ls) {
				String field = nodeMethod.getParent().getName();
				String value = prop.get("field").get(field).str();
				if(value != null && !value.isEmpty()) {
					nodeMethod.setDescription(value);					
				}
			}
			return ls;
			
		} catch (Exception e) {
			return ls;
		}
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getOpenshift() {
		return openshift;
	}

	public void setOpenshift(String openshift) {
		this.openshift = openshift;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public NodeMap getProp() {
		return prop;
	}

	public void setProp(NodeMap prop) {
		this.prop = prop;
	}

}