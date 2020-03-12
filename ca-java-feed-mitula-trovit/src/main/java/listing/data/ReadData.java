/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listing.data;
//import com.mongodb.client.MongoClient;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import feed.utills.*;
import listing.connection.MongoDbConnection;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author sqyuser
 */
public class ReadData {
	private String propertytype; // this field is used for which type of propety
	private String listingtype; // this field is used for which type of listing
	private MongoClient connection;
	private int limit = 200;
	private ObjectId last_id = null;
	MongoCursor<Document> cursor = null;
	FileOutputStream outputStream1;
	FileOutputStream outputStream2;
	FeedHelper helper;
	boolean is_res = true;
	final public String file1;
	final public String file2;
	boolean ismore = false;
	Document d = null;
	String username;

	public ReadData() {
		MongoDbConnection conn = MongoDbConnection.getInstance();
		this.connection = conn.getConnection();
		this.file1 = "Mitula";
		this.file2 = "Trovit";
		this.helper = new FeedHelper();
	}

	/*
	 * Set the property type
	 */
	public void setPropertytype(String propertytype) {
		this.propertytype = propertytype;
	}

	/*
	 * Set the listing type
	 */
	public void setListingtype(String listingtype) {
		this.listingtype = listingtype;
	}

	/*
	 * Read the data from mongodb database
	 */
	public boolean readData(String type,boolean is_comm) throws InterruptedException, IOException {
		BasicDBObject query = this.helper.getBaseQuery(type, this.is_res);
		this.listingtype = type;
		this.is_res=is_comm;
		if(this.is_res) {
		this.outputStream1 = new FileOutputStream(this.file1 + "_" + type + ".xml");
		this.outputStream2 = new FileOutputStream(this.file2 + "_" + type + ".xml");
		}
		else
		{
			this.outputStream1 =new FileOutputStream(this.file1 + "_" + type + ".xml",false);
			this.outputStream2 =new FileOutputStream(this.file2 + "_" + type + ".xml",false);
		}
		String first_tag[] = this.getFirstTag();
		byte[] tagToBytes1 = first_tag[0].getBytes();
		byte[] tagToBytes2 = first_tag[1].getBytes();
		this.outputStream1.write(tagToBytes1);
		this.outputStream2.write(tagToBytes2);
		MongoDatabase database = this.connection.getDatabase("treb");
		MongoCollection<Document> collection = database.getCollection("trebsqy");
		while (true) {
			synchronized (this) {
				while (this.cursor != null)
					wait();
				System.out.println("read blocked is called");
				if (this.last_id != null) {
					String s1="Condo/Resi";
					String s2="Commercial";
					String s3=(this.is_res)?s1:s2;
					System.out.println(this.last_id + "---" + this.listingtype + "--" +s3);
					query.put("_id", new BasicDBObject("$gt", this.last_id));
				}
				try {
					FindIterable<Document> myDoc = collection.find(query).limit(this.limit);
					this.cursor = myDoc.iterator();
				} catch (Exception ex) {
					System.out.print(ex.getMessage());
				}
				if (!this.cursor.hasNext()) {
						System.out.println("finished");
						String tag[] = this.getTag();
						byte[] tagToBytes = tag[0].getBytes();
						byte[] tagToBytes11 = tag[1].getBytes();
						this.outputStream1.write(tagToBytes);
						this.outputStream2.write(tagToBytes11);
						this.outputStream2.close();
						this.outputStream1.close();
						return true;
					}
			notify();
			// makes the working of program easier
			// to understand
//            Thread.sleep(1000); 

		}
	}

	}
/*
 * This function is write the  data for xml
 */
	public void writeDataToXmlFile() throws IOException, InterruptedException, NullPointerException {
		while (true) {
			synchronized (this) {
				while (this.cursor == null)
					wait();
				System.out.println("write is called");
				this.ismore = false;
				int i = 0;
				while (this.cursor.hasNext()) {
					this.ismore = true;
					this.d = this.cursor.next();
					String data[] = this.helper.makeXmldata(this.listingtype, this.d);
					byte[] strToBytes1 = data[0].getBytes();
					byte[] strToBytes2 = data[1].getBytes();
					this.outputStream1.write(strToBytes1);
					this.outputStream2.write(strToBytes2);
					this.last_id = (ObjectId) this.d.get("_id");
					i++;
				}

				this.cursor = null;

				notify();

				// and sleep
//                 Thread.sleep(2000); 
			}
		}
	}

	public String[] getTag() {
		String tag[] = new String[2];
		tag[0] = "</" + this.file1 + ">";
		tag[1] = "</" + this.file2 + ">";
		return tag;
	}

	public String[] getFirstTag() {
		String tag[] = new String[2];
		tag[0] = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<" + this.file1 + ">";
		tag[1] = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<" + this.file2 + ">";
		return tag;
	}
}
