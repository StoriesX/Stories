package com.stories.dao;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.stories.model.User;

public class UserDao {
	private static UserDao userdao;
	private MongoDatabase db;
	public UserDao(){
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		db = mongoClient.getDatabase("stories");
		System.out.println("connected to MongoDB");
	}
	
	public static UserDao getInstance(){
		if(userdao == null){
			userdao = new UserDao();
		}
		return userdao;
	}
	
	public int register(User user){
		MongoCollection<Document> users = db.getCollection("users");
		if(users.find(new Document("username", user.getUsername())).first() != null){
			return 0;
		}else{
			users.insertOne(new Document("username", user.getUsername())
					.append("password", user.getPassword())
					.append("firstName", user.getFirstName())
					.append("lastName", user.getLastName()));
			return 1;
		}
	}
	
	public String login(User user){
		MongoCollection<Document> users = db.getCollection("users");
		if(users.find(new Document("username", user.getUsername()).append("password", user.getPassword())).first() != null){
			return "token";
		}else{
			return null;
		}
	}
	
	//only for testing purpose
	public void removeUser(User user){
		MongoCollection<Document> users = db.getCollection("users");
		users.findOneAndDelete(new Document("username", user.getUsername()).append("password", user.getPassword()));
	}
}
