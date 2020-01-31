package org.microcks.model.node;

import java.util.ArrayList;
import java.util.List;

import org.microcks.repository.RepositoryBean;

public abstract class Node {

	protected Node parent;
	protected List<Node> nodes;

	protected String name;
	
	public void add(Node node) {
		if(nodes == null) {
			nodes = new ArrayList<Node>();
		}
		
		add(node,this);
		//node.setParent(this);
		
	}
	
	private void add(Node src, Node tar) {
		Node t = find(tar,src.getName());
		if(t == null) {
			tar.nodes.add(src);
		}else {
			for (Node node :  src.get()) {
				add(node,t);
			}
		}
	}
	
	protected Node find(Node tar, String name) {
		Node r = null;
		List<Node> nd = tar.get();
		if(nd != null) {
			for (Node node : nd) {
				if(name.equals(node.getName())) {
					r = node;
					break;
				}
			}
		}
		return r;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Node getFirst(){
		if(nodes != null && !nodes.isEmpty()) {
			return nodes.get(0);
		}
		return null;
	}	
	
	public List<Node> get(){
		return nodes;
	}
	
	public String getName() {
		return name;
	}
	
	public Node get(String name) {
		Node r = null;
		for (Node node : nodes) {
			if(name.equals(node.getName())) {
				r = node;
				break;
			}
		}
		return r;
	}
	
	public String getPath() {
		String path = "";
		if(parent != null) {
			path += parent.getPath() + "/" + name; 
		}
		return path;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public RepositoryBean getRepository() {
		return parent.getRepository();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}else {
			Node from = this;
			Node to = (Node) obj;
			if(from.get() == null && to.get() == null) {
				return true;
			}else if(from.get() == null || to.get() == null) {
				return false;
			}else {
				for(Node i : from.get()) {
					Node r = to.get(i.getName());
					if(r == null) {
						return false;
					}else {
						if(!i.equals(r)) {
							return false;
						}
					}
				}
			}
		}		
		return true;
	}

	public List<NodeMethod> getAllNodeMethod() {
		List<NodeMethod> list = new ArrayList<NodeMethod>();
		if(this instanceof NodeMethod) {
			list.add((NodeMethod)this);
		}else {
			for(Node n : get()) {
				List<NodeMethod> nm = n.getAllNodeMethod();
				list.addAll(nm);
			}
		}
		return list;
	}
	
}