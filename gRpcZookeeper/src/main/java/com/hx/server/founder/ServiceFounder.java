/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.server.founder;

import io.grpc.BindableService;

import java.util.Map;

/**
 * Created by testuser on 16-12-14.
 */
public interface ServiceFounder {
    /**
     * Find service list.
     *
     * @return the service map
     */
    Map<String, BindableService> findServices();
}
