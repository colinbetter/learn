

package com.hx.dao;

import com.hx.domain.Client;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by testuser on 17-3-21.
 */
@Component("clientDao")
public class ClientDaoImpl implements ClientDao {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final Logger LOG = LoggerFactory.getLogger(ClientDaoImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(Client client) {
        mongoTemplate.insert(client);
    }

    @Override
    public void insert(List<Client> clients) {
        mongoTemplate.insertAll(clients);
    }

    @Override
    public void insertByTime(Client client, String day) {
        mongoTemplate.insert(client, "clientonline_" + day);
    }

    @Override
    public List<Client> getSomeFields(String acSN) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.and(new BasicDBObject("acSN", acSN));
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("acSN", 1);
        fieldsObject.put("updateTime", 1);
        fieldsObject.put("updateTime", 1);
        fieldsObject.put("clientRadioMode", 1);
        Query query = new BasicQuery(queryBuilder.get(), fieldsObject);
        return mongoTemplate.find(query, Client.class);
    }

    @Override
    public List<Client> getAllFields(String acSN) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.or(new BasicDBObject("acSN", acSN));
        Query query = new BasicQuery(queryBuilder.get());
        List<Client> clients = mongoTemplate.find(query, Client.class);
        //LOG.info(clients.get(0).getApSN());
        return clients;
    }

    @Override
    public void update(String acSN) {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.and(new BasicDBObject("acSN", acSN));
        Query query = new BasicQuery(queryBuilder.get());
        Update update = new Update().set("updateTime", LocalDateTime.now());
        WriteResult writeResult = mongoTemplate.updateMulti(query, update, Client.class);
        //LOG.info("the "+writeResult.getN()+" has been modified");
    }
}
