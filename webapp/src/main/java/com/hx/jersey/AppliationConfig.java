package com.hx.jersey;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by testuser on 16-9-19.
 */
@ApplicationPath("/jersey/*")
public class AppliationConfig extends ResourceConfig {
    public AppliationConfig() {
        packages("com.hx.jersey.resource");
    }
}