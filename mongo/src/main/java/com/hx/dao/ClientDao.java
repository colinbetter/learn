

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
