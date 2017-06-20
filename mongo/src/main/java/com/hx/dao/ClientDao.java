/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.dao;

import com.hx.domain.Client;

import java.util.List;

/**
 * Created by testuser on 17-3-21.
 */
public interface ClientDao {
    void insert(Client client);

    void insert(List<Client> clients);

    void insertByTime(Client client, String day);

    List<Client> getSomeFields(String acSN);

    List<Client> getAllFields(String acSN);

    void update(String acSN);
}
