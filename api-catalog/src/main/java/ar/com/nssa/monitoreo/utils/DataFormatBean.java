package ar.com.nssa.monitoreo.utils;

import org.microcks.model.node.Node;
import org.microcks.model.node.NodeMethod;
import org.springframework.stereotype.Component;

@Component
public class DataFormatBean {

	public String print(Node v) {
		StringBuffer buffer = new StringBuffer();
		if(v instanceof NodeMethod) {
			return v.getPath();
		}else {
			for(Node i : v.get()) {
				String s = print(i);
				if(s != null && !s.isEmpty()) {
					buffer.append(s).append("\n\n");
				}
			}
		}
		return buffer.toString().trim();
	}
	
}
