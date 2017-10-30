package com.example.demo;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class CustomerUserDetailsService implements UserDetailsService {
	@Autowired
	private MongoClient mongoClient;
	
	@Override
	public UserDetails loadUserByUsername(String usr) throws UsernameNotFoundException {
		MongoDatabase database = mongoClient.getDatabase("customer");
        MongoCollection<Document> collection = database.getCollection("customer.login");
        
        Document document = collection.find(Filters.eq("usr", usr)).first();
        
        if(document != null) {     
            String password = document.getString("pwd");
            List<String> authorities = (List<String>) document.get("authorities");
            MongoUserDetails mongoUserDetails = new MongoUserDetails(usr, password, authorities.toArray(new String[authorities.size()]));
            return mongoUserDetails;
        }
		return null;
	}

}
