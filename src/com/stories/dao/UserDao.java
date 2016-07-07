package com.stories.dao;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.stories.exception.DuplicatedUserException;
import com.stories.exception.WrongCredentialsException;
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
	
	public String authenticate(String username, String password) throws Exception{
		MongoCollection<Document> users = db.getCollection("users");
		Document user = users.find(new Document("username", username).append("password", password)).first();
		if(user != null){
			System.out.println("user logged in: "+username);
			Random random = new SecureRandom();
		    String token = new BigInteger(130, random).toString(32);
		    users.updateOne(user, new Document("$set", new Document("token", token)));
		    return token;
		}else{
			throw new WrongCredentialsException();
		}
	}
	
	//only for development purpose
	/*
	public void removeUser(String username){
		MongoCollection<Document> users = db.getCollection("users");
		users.deleteMany(new Document("username", username));
	}
	*/
	public void register(String username, String password, String firstName, String lastName) throws Exception{
		MongoCollection<Document> users = db.getCollection("users");
		if(!User.validate(username, password, firstName, lastName)){
			throw new Exception();
		}else if(users.find(new Document("username", username)).first() != null){
			throw new DuplicatedUserException();
		}else{
			System.out.println("created a new user: "+username);
			users.insertOne(new Document("username", username).append("password", password)
					.append("firstName", firstName).append("lastName", lastName));
		}	
	}

}
