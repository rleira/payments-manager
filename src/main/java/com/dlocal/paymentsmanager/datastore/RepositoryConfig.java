package com.dlocal.paymentsmanager.datastore;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
class RepositoryConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty("mongo.name");
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(env.getProperty("mongo.url"), Integer.parseInt(env.getProperty("mongo.port")));
    }
}
