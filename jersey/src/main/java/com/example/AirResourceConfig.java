package com.example;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by testuser on 16-9-20.
 */
@ApplicationPath("/webapi/*")
public class AirResourceConfig extends ResourceConfig {
    /**
     * <p>Constructor for AirResourceConfig.</p>
     */
    public AirResourceConfig() {
        packages("com.example");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + isRegistered(JacksonFeature.class));
    }
}
