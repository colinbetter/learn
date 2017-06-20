package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import java.util.Random;

/**
 * Created by testuser on 16-9-20.
 */
@Path("myresource")
public class MyResource {
    @Context
    private Request request;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        Random r = new Random();
        int i = r.nextInt();
        System.out.println(i + " " + request.getClass() + " " + this.toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i + " " + request.getClass() + " " + this.toString());
        return "Got it!";
    }
}
