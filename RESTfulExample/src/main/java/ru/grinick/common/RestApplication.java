package ru.grinick.common;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import ru.grinick.common.RestService;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

public class RestApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	public RestApplication() {
		singletons.add(new RestService());
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setAllowCredentials(true);
        corsFilter.setAllowedMethods("GET, POST, PUT, DELETE, OPTIONS, HEAD");
        corsFilter.setAllowedHeaders("origin, content-type, accept, authorization, cache-control");
        corsFilter.setCorsMaxAge(1209600);
        singletons.add(corsFilter);		
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
}