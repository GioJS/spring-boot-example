package com.example.demo;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
//@EnableMongoRepositories("db.customer")
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Value("${spring.data.mongodb.database}")
	private String  dbName;

	@Value("${spring.data.mongodb.host}")
	private String  host;

	@Value("${spring.data.mongodb.port}")
	private Integer port;

	@Value("${spring.data.mongodb.username}")
	private String  userName;

	@Value("${spring.data.mongodb.password}")
	private String  password;
	
	@Override
	protected String getDatabaseName() {
		return this.dbName;
	}

	@Bean
	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(Collections.singletonList(new ServerAddress(host, port)),
			      Collections.singletonList(MongoCredential.createCredential(userName, dbName, password.toCharArray())));
	}
	
}
