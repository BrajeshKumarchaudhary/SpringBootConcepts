/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listing.connection;
import java.net.URLEncoder;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
//import com.mongodb.MongoClientSettings;
/**
 *
 * @author sqyuser
 */
public class MongoDbConnection {
   
	private static MongoDbConnection single_instance = null;
	final private String username="sqyca";
//	final private String username="dbadmin";
//	final private String password="D3@dm!n$$";
	final private String password="S@yCa!op3n$$";
//	final private String serveraddress="99.79.107.4:27017";
	final private String serveraddress="99.79.99.249:27017";
	MongoClient mongoClient=null;
	
	
	private MongoDbConnection()
	{
		
	}
	
	
 /*
  Make The connection to mongodb
*/
 public  MongoClient getConnection()
{
	 try
	 {
	 if(this.mongoClient==null) {
//		 String uri = "mongodb://localhost";
//		 String dbURI = "mongodb://" + host_n + ":" + port_no + "/" + db_name;
//		  mongoClient = new MongoClient(new MongoClientURI(uri));
		 MongoClientURI uri = new MongoClientURI("mongodb://"+URLEncoder.encode(this.username, "UTF-8")+":"+URLEncoder.encode(this.password, "UTF-8")+"@"+this.serveraddress+"/?authSource=admin");
		 this.mongoClient = new MongoClient(uri);
		 System.out.println("Connected to the database successfully"); 
	 }
	 else
	 {
		 return this.mongoClient;
	 }
	 }
	 catch(Exception ex)
	 {
		 System.out.print(ex.getMessage());
	 }
		return this.mongoClient;
}
 public static MongoDbConnection getInstance()
 {
	 if (single_instance == null) 
         single_instance = new MongoDbConnection(); 

     return single_instance; 
 	
 }
 
}

