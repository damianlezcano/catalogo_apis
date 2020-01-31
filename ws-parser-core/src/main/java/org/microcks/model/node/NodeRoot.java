package org.microcks.model.node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.microcks.model.NodeMap;
import org.microcks.model.node.openapi.NodeServiceOpenApiBean;
import org.microcks.model.node.swagger.NodeServiceSwaggerBean;
import org.microcks.model.node.wsdl.NodeServiceWsdlBean;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

//@org.springframework.stereotype.Service
public class NodeRoot extends Node {

	public void load(String content) throws JsonParseException, JsonMappingException, IOException {
		load("", content);
	}
	
	public void load(String uri, String content) throws JsonParseException, JsonMappingException, IOException {
		this.name = "/";

		String contentType = getContentType(content);
		System.out.println("## contentType: " + contentType);		
        HashMap metadata = getMetadata(contentType,content);
        
		NodeMap nMap = new NodeMap(metadata);
		
		NodeService nodeService = getNodeService(contentType,this,nMap,uri,content);
		
		Node nodeServiceToRemove = find(this, nodeService.getName());
		
		if(nodeServiceToRemove != null) {
			Node nodeVersionToRemove = nodeServiceToRemove.get(nodeService.getFirst().getName());
			if(nodeVersionToRemove != null) {
				nodeServiceToRemove.get().remove(nodeVersionToRemove);
			}
			nodeServiceToRemove.add(nodeService.getFirst());
		}else {
			add(nodeService);
		}
	}
	
    private NodeService getNodeService(String contentType, NodeRoot nodeRoot, NodeMap nMap, String uri, String content) {
    	NodeService nodeService = null;
    	if("wsdl".equalsIgnoreCase(contentType)) {
    		nodeService = new NodeServiceWsdlBean(this,nMap,uri,content);
    	}else if("json".equalsIgnoreCase(contentType)) {
    		nodeService = new NodeServiceSwaggerBean(this,nMap,uri,content);
    	}else if("yaml".equalsIgnoreCase(contentType)) {
    		nodeService = new NodeServiceOpenApiBean(this,nMap,uri,content);
    	}
    	return nodeService;
	}

	private HashMap getMetadata(String contentType, String content) throws JsonParseException, JsonMappingException, IOException {
    	ObjectMapper mapper = null;
    	if("wsdl".equalsIgnoreCase(contentType)) {
    		SimpleModule module = new SimpleModule().addDeserializer(Object.class, Issue205FixedUntypedObjectDeserializer.getInstance());
    		mapper = (XmlMapper) new XmlMapper().registerModule(module);
    	}else if("json".equalsIgnoreCase(contentType)) {
    		mapper = new ObjectMapper(new JsonFactory());
    	}else if("yaml".equalsIgnoreCase(contentType)) {
    		mapper = new ObjectMapper(new YAMLFactory());
    	}
    	return mapper.readValue(content, HashMap.class);
	}
    
    private String getContentType(String content) {
    	if(content.startsWith("<")){
    		return "wsdl";
    	}else if(content.startsWith("{")){
    		return "json";    		
    	}else {
    		return "yaml";
    	}
    }

	@SuppressWarnings({ "deprecation", "serial" })
    public static class Issue205FixedUntypedObjectDeserializer extends UntypedObjectDeserializer {

        private static final Issue205FixedUntypedObjectDeserializer INSTANCE = new Issue205FixedUntypedObjectDeserializer();

        private Issue205FixedUntypedObjectDeserializer() {}

        public static Issue205FixedUntypedObjectDeserializer getInstance() {
            return INSTANCE;
        }

        @Override
        @SuppressWarnings({ "unchecked", "rawtypes" })
        protected Object mapObject(JsonParser parser, DeserializationContext context) throws IOException {

            // Read the first key.
            String firstKey;
            JsonToken token = parser.getCurrentToken();
            if (token == JsonToken.START_OBJECT) {
                firstKey = parser.nextFieldName();
            } else if (token == JsonToken.FIELD_NAME) {
                firstKey = parser.getCurrentName();
            } else {
                if (token != JsonToken.END_OBJECT) {
                    throw context.mappingException(handledType(), parser.getCurrentToken());
                }
                return Collections.emptyMap();
            }

            // Populate entries.
            Map<String, Object> valueByKey = new LinkedHashMap<>();
            String nextKey = firstKey;
            do {

                // Read the next value.
                parser.nextToken();
                Object nextValue = deserialize(parser, context);

                // Key conflict? Combine existing and current entries into a list.
                if (valueByKey.containsKey(nextKey)) {
                    Object existingValue = valueByKey.get(nextKey);
                    if (existingValue instanceof List) {
                        List<Object> values = (List<Object>) existingValue;
                        values.add(nextValue);
                    } else {
                        List<Object> values = new ArrayList<>();
                        values.add(existingValue);
                        values.add(nextValue);
                        valueByKey.put(nextKey, values);
                    }
                }

                // New key? Put into the map.
                else {
                    valueByKey.put(nextKey, nextValue);
                }

            } while ((nextKey = parser.nextFieldName()) != null);

            // Ship back the collected entries.
            return valueByKey;

        }

    }
	
}