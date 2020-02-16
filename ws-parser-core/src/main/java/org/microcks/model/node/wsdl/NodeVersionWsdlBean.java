package org.microcks.model.node.wsdl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.WSDLException;
import javax.wsdl.xml.WSDLReader;

import org.microcks.model.NodeMap;
import org.microcks.model.node.Node;
import org.microcks.model.node.NodeVersion;

import com.ibm.wsdl.PortTypeImpl;
import com.ibm.wsdl.xml.WSDLReaderImpl;

public class NodeVersionWsdlBean extends NodeVersion {

	private Map schema;
	private StringBuffer wsdl;
	
	public NodeVersionWsdlBean(Node parent, String version, NodeMap metadata, String wsdl) {
		this.parent = parent;
		this.wsdl = new StringBuffer(wsdl);
		this.name = version;
		buildPaths(metadata);
		schema = buildSchema(metadata);
	}

	private void buildPaths(NodeMap metadata) {
		List paths = metadata.get("portType").get("operation").list();
		if(paths.isEmpty()) {
			paths = metadata.lookup("operation").list();
		}
		for (Object obj : paths) {
			if(obj instanceof Map) {
				Map map = (Map) obj;
				String p = map.get("name").toString();
				Node nodePath = new NodePathWsdlBean(this,p,map);
				add(nodePath);				
			}else if(obj instanceof Entry) {
				Entry entry = (Entry) obj;
				if("name".equalsIgnoreCase(entry.getKey().toString())) {
					String p = entry.getValue().toString();
					Node nodePath = new NodePathWsdlBean(this,p);
					add(nodePath);									
				}
			} 
		}			
		
	}

    private static List<Operation> getPortTypeOperations(String wsdlUrl) {
        List<Operation> operationList = new ArrayList();
        try {
            WSDLReader reader = new WSDLReaderImpl();
            reader.setFeature("javax.wsdl.verbose", false);
            Definition definition = reader.readWSDL("http://changed-document-esb-uat.noprod-apps.pro.edenor/service/changedDocument?wsdl");
            Map<String, PortTypeImpl> defMap = definition.getAllPortTypes();
            Collection<PortTypeImpl> collection = defMap.values();
            for (PortTypeImpl portType : collection) {
                operationList.addAll(portType.getOperations());
            }
        } catch (WSDLException e) {
            System.out.println("get wsdl operation fail.");
            e.printStackTrace();
        }
        return operationList;
    }	
	
	private Map buildSchema(NodeMap metadata) {
//		NodeMap components = metadata.get("types").get("schema");
//		return components.map();
		return null;
	}

	public String toWsdl() {
		return wsdl.toString();
	}
	
}