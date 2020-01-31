package org.microcks.repository;

import java.util.HashMap;
import java.util.Map;

public class RepositoryFactoryBean {

	private static Map<String,RepositoryBean> map = new HashMap<String,RepositoryBean>();
	
	public static RepositoryBean get(String ctx) {
		if(!map.containsKey(ctx)) {
			RepositoryBean r = new RepositoryBean();
			map.put(ctx, r);
		}
		return map.get(ctx);
	}
	
}