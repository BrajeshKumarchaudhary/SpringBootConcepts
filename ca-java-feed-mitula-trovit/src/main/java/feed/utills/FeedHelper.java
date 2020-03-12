package feed.utills;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import listing.connection.*;
public class FeedHelper {
	MongoClient connection;
	public String feedAreas_value[]= {  "Durham","Halton", "Peel", "Toronto","York"};
	public String muncipal_value[]= {"Uxbridge","Scugog","Georgina"};
	public FeedHelper() {
		MongoDbConnection connect =MongoDbConnection.getInstance();
		this.connection=connect.getConnection();
	}
	private final String MitulaLeaseutmcode = "?utm_source=realestate.mitula.ca&utm_medium=referral&utm_campaign=MitulaCA_Lease&source=SMNTs44w8qN8a72SALP0Ng==";
	private final String TroviLeaseutmcode="?source=l8auQ4XZH%2FOA%2Fgjy4%2BfxEQ%3D%3D0n";
	private final String MitulaSaleutmcode = "?utm_source=realestate.mitula.ca&utm_medium=referral&utm_campaign=MitulaCA_Sale&source=3bwdNn4SiFdBwlzxY5L%2Bow%3D%3D0n";
	private final String TrovitSaleutmcode = "?source=qS5qqAmts5rIBL%2FQXG8LBw%3D%3D0n";
	private String img_url="https://s3.ca-central-1.amazonaws.com/canada-bucket-new/Orig/";
	public String[] makeXmldata(String type,Document doc)
	{
		String[] dataxml = new String[2];
		switch(type)
		{
		case "Lease":
			dataxml[0]=this.makeMitulaLeaseFeed(doc);
			dataxml[1]=this.makeTrovitLeaseFeed(doc);
			break;
		case "Sale":
			dataxml[0]=this.makeMitulaSaleFeed(doc);
			dataxml[1]=this.makeTrovitSaleFeed(doc);
			break;
			
	    default:
	    	System.out.print("type is not found");
		}
		return dataxml;
	}
	
	/*
	 * make trovit lease
	 */
	public String makeTrovitLeaseFeed(Document doc)
	{
		String title =this.propertyName(doc);
        String content =this.getDescription(doc);
         String url_link = "https://www.squareyards.ca/" +doc.get("Uri_string")+this.TroviLeaseutmcode;
         String xmlfeed="";
         try {
          xmlfeed=""
				+ " <ad>\n" + 
				"                <id><![CDATA["+doc.get("Ml_num")+"]]></id>\n" + 
				"                <url><![CDATA["+ url_link+"]]></url>\n" + 
				"                <title><![CDATA["+title+"]]></title>\n" + 
				"                <type><![CDATA["+this.getPropertyType(doc)+"]]></type>\n" + 
				"                <agency><![CDATA["+doc.get("Broker")+"]]></agency>\n" + 
				"                <content><![CDATA["+content+"]]></content>\n" + 
				"                <price><![CDATA["+this.getPrice(doc)+"]]></price>\n" + 
				"                <property_type><![CDATA["+this.propertyType(doc)+"]]></property_type>\n" + 
				"                <floor_area><![CDATA["+this.area(doc)+"]]></floor_area>\n" + 
				"                    <rooms><![CDATA["+this.totalRooms(doc)+"]]></rooms>\n" + 
				"                    <bathrooms><![CDATA["+ this.getBathRoom(doc)+"]]></bathrooms>\n" + 
				"                <city><![CDATA["+doc.get("Municipality", Document.class).getString("Municipality")+"]]></city>\n" + 
				"                    <city_area><![CDATA["+this.getArea(doc)+"]]></city_area>\n" + 
				"                <postcode><![CDATA["+doc.get("Zip")+"]]></postcode>\n" + 
				"                <region><![CDATA["+doc.get("Address", Document.class).getString("County")+"]]></region>\n" + 
				"                    <latitude><![CDATA["+this.getLatLong(doc,"Latitude")+"]]></latitude>\n" + 
				"                    <longitude><![CDATA["+this.getLatLong(doc,"longitude")+"]]></longitude>\n" + 
				"                <pictures>\n" + 
				"<picture>" + this.images(doc)+
				"       </picture>" + 
				" "+
				"                </pictures>\n" + 
				"                    <virtual_tour><![CDATA["+this.getTourUrl(doc)+"]]></virtual_tour>\n" + 
				"                <date><![CDATA["+doc.get("updated_timestamp")+"]]></date>\n" + 
				"                    <expiration_date><![CDATA["+this.getExpirationdate(doc)+"]]></expiration_date>\n" +
				"<segmentation_text><![CDATA["+doc.get("S_r")+"]]></segmentation_text>"+
				"<mls_database><![CDATA[" +doc.get("Ml_num")+"]]></mls_database>"+
				"<year><![CDATA["+doc.get("Tax", Document.class).getString("Yr")+"]]></year>"+
				"            </ad>";
         }
         catch(java.lang.ClassCastException ex)
         {
        	 
         }
		return xmlfeed;
	}
	/*
	 * make mitula lease
	 */
	public String makeMitulaLeaseFeed(Document doc)
	{
		String title =this.propertyName(doc);
        String content =this.getDescription(doc);
         String url_link = "https://www.squareyards.ca/" +doc.get("Uri_string")+this.MitulaLeaseutmcode;
         String xmlfeed="";
try {
         xmlfeed=""
				+ " <ad>\n" + 
				"                <id><![CDATA["+doc.get("Ml_num")+"]]></id>\n" + 
				"                <url><![CDATA["+ url_link+"]]></url>\n" + 
				"                <title><![CDATA["+title+"]]></title>\n" + 
				"                <type><![CDATA["+this.propertyType(doc)+"]]></type>\n" + 
				"                <agency><![CDATA["+doc.get("Broker")+"]]></agency>\n" + 
				"                <content><![CDATA["+content+"]]></content>\n" + 
				"                <price><![CDATA["+this.getPrice(doc)+"]]></price>\n" + 
				"                <property_type><![CDATA["+this.propertyType(doc)+"]]></property_type>\n" + 
				"                <floor_area><![CDATA["+this.area(doc)+"]]></floor_area>\n" + 
				"                    <rooms><![CDATA["+this.totalRooms(doc)+"]]></rooms>\n" + 
				"                    <bathrooms><![CDATA["+ this.getBathRoom(doc)+"]]></bathrooms>\n" + 
				"                <city><![CDATA["+doc.get("Municipality", Document.class).getString("Municipality")+"]]></city>\n" + 
				"                    <city_area><![CDATA["+this.getArea(doc)+"]]></city_area>\n" + 
				"                <postcode><![CDATA["+doc.get("Zip")+"]]></postcode>\n" + 
				"                <region><![CDATA["+doc.get("Address", Document.class).getString("County")+"]]></region>\n" + 
				"                    <latitude><![CDATA["+this.getLatLong(doc,"Latitude")+"]]></latitude>\n" + 
				"                    <longitude><![CDATA["+this.getLatLong(doc,"longitude")+"]]></longitude>\n" + 
				"                <pictures>\n" + 
				"<picture>" + this.images(doc)+
				"       </picture>" + 
				" "+
				"                </pictures>\n" + 
				"                    <virtual_tour><![CDATA["+this.getTourUrl(doc)+"]]></virtual_tour>\n" + 
				"                <date><![CDATA["+doc.get("updated_timestamp")+"]]></date>\n" + 
				"                    <expiration_date><![CDATA["+this.getExpirationdate(doc)+"]]></expiration_date>\n" + 
				"            </ad>";
}
 catch(java.lang.ClassCastException ex)
{
	 System.out.println(ex.getMessage()); 
}
		return xmlfeed;
	}
	/*
	 * make mitula sale
	 */
	public String makeMitulaSaleFeed(Document doc)
	{
		String title =this.propertyName(doc);
        String content =this.getDescription(doc);
    	String xmlfeed="";
        String url_link = "https://www.squareyards.ca/" +doc.get("Uri_string")+this.MitulaSaleutmcode;
	    try {
        xmlfeed=""
				+ " <ad>\n" + 
				"                <id><![CDATA["+doc.get("Ml_num")+"]]></id>\n" + 
				"                <url><![CDATA["+ url_link+"]]></url>\n" + 
				"                <title><![CDATA["+title+"]]></title>\n" + 
				"                <type><![CDATA["+this.getPropertyType(doc)+"]]></type>\n" + 
				"                <agency><![CDATA["+doc.get("Broker")+"]]></agency>\n" + 
				"                <content><![CDATA["+content+"]]></content>\n" + 
				"                <price><![CDATA["+this.getPrice(doc)+"]]></price>\n" + 
				"                <property_type><![CDATA["+this.propertyType(doc)+"]]></property_type>\n" + 
				"                <floor_area><![CDATA["+this.area(doc)+"]]></floor_area>\n" + 
				"                    <rooms><![CDATA["+this.totalRooms(doc)+"]]></rooms>\n" + 
				"                    <bathrooms><![CDATA["+ this.getBathRoom(doc)+"]]></bathrooms>\n" + 
				"                <city><![CDATA["+doc.get("Municipality", Document.class).getString("Municipality")+"]]></city>\n" + 
				"                    <city_area><![CDATA["+this.getArea(doc)+"]]></city_area>\n" + 
				"                <postcode><![CDATA["+doc.get("Zip")+"]]></postcode>\n" + 
				"                <region><![CDATA["+doc.get("Address", Document.class).getString("County")+"]]></region>\n" + 
				"                    <latitude><![CDATA["+this.getLatLong(doc,"Latitude")+"]]></latitude>\n" + 
				"                    <longitude><![CDATA["+this.getLatLong(doc,"longitude")+"]]></longitude>\n" + 
				"                <pictures>\n" + 
				                       ""+this.images(doc)+
				"                </pictures>\n" + 
				"                    <virtual_tour><![CDATA["+this.getTourUrl(doc)+"]]></virtual_tour>\n" + 
				"                <date><![CDATA["+doc.get("updated_timestamp")+"]]></date>\n" + 
				"                    <expiration_date><![CDATA["+this.getExpirationdate(doc)+"]]></expiration_date>\n" + 
				"            </ad>";
	    }
	    catch(java.lang.ClassCastException ex)
	    {
	    	System.out.println(ex.getMessage()); 	
	    }
		return xmlfeed;
	}
	/*
	 * make mitula Lease
	 */
	public String makeTrovitSaleFeed(Document doc)
	{
		String title =this.propertyName(doc);
        String content =this.getDescription(doc);
         String url_link = "https://www.squareyards.ca/" +doc.get("Uri_string")+this.TrovitSaleutmcode;
         String xmlfeed="";
         try {
         xmlfeed=""
				+ " <ad>\n" + 
				"                <id><![CDATA["+doc.get("Ml_num")+"]]></id>\n" + 
				"                <url><![CDATA["+ url_link+"]]></url>\n" + 
				"                <title><![CDATA["+title+"]]></title>\n" + 
				"                <type><![CDATA["+this.getPropertyType(doc)+"]]></type>\n" + 
				"                <agency><![CDATA["+doc.get("Broker")+"]]></agency>\n" + 
				"                <content><![CDATA["+content+"]]></content>\n" + 
				"                <price><![CDATA["+this.getPrice(doc)+"]]></price>\n" + 
				"                <property_type><![CDATA["+this.propertyType(doc)+"]]></property_type>\n" + 
				"                <floor_area><![CDATA["+this.area(doc)+"]]></floor_area>\n" + 
				"                    <rooms><![CDATA["+this.totalRooms(doc)+"]]></rooms>\n" + 
				"                    <bathrooms><![CDATA["+ this.getBathRoom(doc)+"]]></bathrooms>\n" + 
				"                <city><![CDATA["+doc.get("Municipality", Document.class).getString("Municipality")+"]]></city>\n" + 
				"                    <city_area><![CDATA["+this.getArea(doc)+"]]></city_area>\n" + 
				"                <postcode><![CDATA["+doc.get("Zip")+"]]></postcode>\n" + 
				"                <region><![CDATA["+doc.get("Address", Document.class).getString("County")+"]]></region>\n" + 
				"                    <latitude><![CDATA["+this.getLatLong(doc,"Latitude")+"]]></latitude>\n" + 
				"                    <longitude><![CDATA["+this.getLatLong(doc,"longitude")+"]]></longitude>\n" + 
				"                <pictures>\n" + 
				"<picture>" + 
				"      <picture_url><![CDATA["+this.img_url +"43846/image-C4384623-1552828227-1.jpeg"+"]]></picture_url>\n" + 
				"         <picture_title><![CDATA["+"Image-" +this.propertyName(doc)+"]></picture_title>\n" + 
				"       </picture>" + 
				" "+this.images(doc)+
				"                </pictures>\n" + 
				"                    <virtual_tour><![CDATA["+this.getTourUrl(doc)+"]]></virtual_tour>\n" +
				"<segmentation_text><![CDATA["+doc.get("S_r")+"]]></segmentation_text>"+
				"<mls_database><![CDATA[" +doc.get("Ml_num")+"]]></mls_database>"+
				"<year><![CDATA["+this.getYear(doc)+"]]></year>"+
				"                <date><![CDATA["+doc.get("updated_timestamp")+"]]></date>\n" + 
				"                    <expiration_date><![CDATA["+this.getExpirationdate(doc)+"]]></expiration_date>\n" + 
				"            </ad>";
		}
         catch(java.lang.ClassCastException ex)
         {
        	System.out.println(ex.getMessage()); 
         }
		return xmlfeed;
	}
	public String getPropertyType(Document doc) {
		String type="";
		if(doc.containsKey("S_r"))
		{
			type=(String) doc.get("S_r");
		}
        type = this.formatTrovitType(type);
        return type;
    }

    private String  formatTrovitType(String propertyType) {
//“For Rent” or “For Sale”
        switch (propertyType.toLowerCase()) {
            case "sale":
                propertyType = "For Sale";
                break;
            case "lease":
                propertyType = "For Rent";
                break;
            default :
                propertyType = "";
                break;
        }
        return propertyType;
    }
    public String getPrice(Document doc)
    { 
    	String price="";
    	try
    	{
    		price=doc.get("price", Document.class).getString("Lp_dol");
    	}
    	catch( java.lang.ClassCastException ex)
    	{
    		try {
    		price=doc.get("price", Document.class).getDouble("Lp_dol").toString();
    		}
    		catch(java.lang.ClassCastException ex1)
    		{
    			price=doc.get("price", Document.class).getInteger("Lp_dol").toString();
    		}
    	}
          return price;
    }
    
    
	/*
	 * make property name
	 */
	public String propertyName(Document doc)
	{
		String name="";
        if (doc.get("Apt_num")!=null) {
            name = name+"#" +doc.get("Apt_num")+ '-';
        }
        name = name+doc.get("Address", Document.class).getString("Addr");
        name=name+",";
        name = name+doc.get("Municipality", Document.class).getString("Municipality");
        name=name+",";
        name = name+doc.get("Address", Document.class).getString("County");
        name =name+' ';
        name= name+doc.get("Zip");
        return name;
	}
	  public String getDescription(Document doc) {
	        return (doc.get("Descreption", Document.class).getString("Ad_text")!=null) ? doc.get("Descreption", Document.class).getString("Ad_text") : (String) doc.get("Descreption");
	    }
	  public String totalRooms(Document doc) {
		  String num="";
		  try
		  {
		  if(doc.get("Total_Rooms")!=null)
		  {
			  num=(String) doc.get("Total_Rooms");
		  }
		  }
		  catch( java.lang.ClassCastException ex)
		  {
			  num=doc.get("Total_Rooms").toString();
		  }
		  return num;
	    }
	  public String getYear(Document doc) {
		  String year="";
		  try
		  {
		  if( doc.get("Tax", Document.class).getString("Yr")!=null)
		  {
			  year=doc.get("Tax", Document.class).getString("Yr");
		  }
		  }
		  catch( java.lang.ClassCastException ex)
		  {
			  year=doc.get("Tax", Document.class).getInteger("Yr").toString();
		  }
		  return year;
	    }
	 
	  public String getTourUrl(Document doc) {
		  String turl="";
		  if(doc.get("Tour_url")!=null)
		  {
			  turl=(String) doc.get("Tour_url");
		  }
		  return turl;
	    }
	  public String getLatLong(Document doc,String lat) {
		  Double latlong = null;
		  
		  if(doc.containsKey("Map_location")&&doc.get("Map_location", Document.class).getDouble("Latitude")!=null)
		  {
			  latlong=doc.get("Map_location", Document.class).getDouble(lat);
			  return latlong.toString();
		  }
		  return "";
		 
	    }
	  public String getBathRoom(Document doc) {
		  String bathnum="";
		  try
		  {
		  if(doc.containsKey("Washrooms")&&doc.get("Washrooms", Document.class).getString("Bath_tot")!=null)
		  {
			  bathnum=(String) doc.get("Washrooms", Document.class).getString("Bath_tot");
		  }
		  }
		  catch(java.lang.ClassCastException ex)
		  {
			  bathnum=doc.get("Washrooms", Document.class).getInteger("Bath_tot").toString();
		  }
		  return bathnum;
	    }
	  public String getArea(Document doc) {
		  String areaname="";
		  if(doc.get("Area", Document.class).getString("Area")!=null)
		  {
			  areaname=(String) doc.get("Area", Document.class).getString("Area");
		  }
		  return areaname;
	    }
	  public String getExpirationdate(Document doc) {
		  String date="";
		  if(doc.get("date", Document.class).getString("Xd")!=null)
		  {
			  date=(String) doc.get("date", Document.class).getString("Xd");
		  }
		  return date;
	    }
	  public String images(Document doc) {
		  String images="";
		  List<Document> array=null;
		  try {
		   array=(ArrayList) doc.get("images");
		  }
		  catch(Exception ex)
		  {
			  System.out.print(ex.getMessage());
		  }
           if(doc.containsKey("images"))
           {
        	   for(int i=0;i<array.size();i++)
        	   {
        		   images=images+"<picture>" + 
        					"      <picture_url><![CDATA["+this.img_url +array.get(i).get("orig_img_path")+"]]></picture_url>\n" + 
        					"       <picture_title><![CDATA["+"Image-" +this.propertyName(doc) + "-" +(i + 1)+"]]></picture_title>\n" + 
        					"       </picture>" + 
        					" ";
        	   }
           }
         return images;
	    }
	  
	  
	  
	  public  String propertyType(Document doc) {
	        String propertyType = "";
	        if (doc.get("Prop_type")!=null)
	            propertyType =(String) doc.get("Prop_type");
	        if (doc.get("Type")!=null && doc.get("Type", Document.class).getString("Type_own1_out")!=null)
	            propertyType = doc.get("Type", Document.class).getString("Type_own1_out");
	        return propertyType;
	    }
	  public String  area(Document doc) {
	        String area ="";
	        switch ((String)doc.get("pClassName")) {
	            case "ResidentialProperty":
	            case "CondoProperty":
	//property_Area-Sqft
	                if (doc.get("property_Area", Document.class).getString("Sqft")!=null) {
	                	try {
	                	String[] split = doc.get("property_Area", Document.class).getString("Sqft").split("-");
	                     area = (String) split[0];
	                	}catch(java.lang.ClassCastException ex)
	                	{
//	                		String[] split = doc.get("property_Area", Document.class).getInteger("Sqft").split("-");
//		                     area = (String) split[0];
	                	}
	                }
	                break;
	            case "CommercialProperty":
	//Tot_area
	                area =(String) doc.get("Tot_area");
	                break;
	        }
	        return area;
	    }
	  public BasicDBObject getBaseQuery(String type,boolean is_res) {
		  BasicDBObject query = new BasicDBObject();
	       if(type=="Sale")
	       {
	    	  query=this.getSaleQuery(); 
	    	  query.put("data_active", new BasicDBObject("$eq", 1));
		      query.put("pStatus", new BasicDBObject("$eq", 1));
		      query.put("image_active", new BasicDBObject("$eq", 1));
		      query.put("S_r", new BasicDBObject("$eq", type));
	       }
	       else
	       {
	    	   query=this.getLeaseQuery(is_res); 
	    	   query.put("data_active", new BasicDBObject("$eq", 1));
		       query.put("pStatus", new BasicDBObject("$eq", 1));
		       query.put("image_active", new BasicDBObject("$eq", 1));
		       query.put("S_r", new BasicDBObject("$eq", type));
	       }
	       return query;
	  }
	  public String[]  getAreaBounds() {
		  BasicDBObject query = new BasicDBObject();
	        query.put("key", new BasicDBObject("$eq", "feedConditions"));
	    	try {
	        if (this.feedAreas_value==null) {
	        	MongoDatabase database = this.connection.getDatabase("treb");
	        	MongoCollection<Document> collection = database.getCollection("app_setting");
	        	  FindIterable<Document> doc = collection.find(query);
	        	  MongoCursor<Document>  cursor = doc.iterator();
	        	  this.feedAreas_value=(String[]) cursor.next().get("value");
	            }
	        	}
	        	catch(Exception ex)
	        	{
	        		System.out.print(ex.getMessage());
	        	}
	        return this.feedAreas_value;
	    }
	  public BasicDBObject getSaleQuery() {
		  BasicDBObject query = new BasicDBObject();
		  List<String> areas =Arrays.asList(this.feedAreas_value);
		  List<String> munc =Arrays.asList(this.muncipal_value);
		  query.put("Area.Area", new BasicDBObject("$in", areas));
		  query.put("Municipality.Municipality", new BasicDBObject("$nin", munc));
	       return query;
	    }
	  public BasicDBObject getLeaseQuery( boolean is_res) {
		  BasicDBObject query = new BasicDBObject();
		  List<String> areas =Arrays.asList(this.feedAreas_value);
		  List<String> munc =Arrays.asList(this.muncipal_value);
		  List<String> list = new ArrayList<String>();
		  if (is_res) {
			  list.add("ResidentialProperty");
			  list.add("CondoProperty");
			  query.put("pClassName", new BasicDBObject("$in", list));
			  query.put("Area.Area", new BasicDBObject("$in", areas));
	  		  query.put("Municipality.Municipality", new BasicDBObject("$nin", munc));
//			  query.put("price.Lp_dol",new BasicDBObject("$gte", 2500));
	        } else {
	        	query.put("pClassName", "CommercialProperty");
	        	query.put("Area.Area", new BasicDBObject("$in", areas));
	  		    query.put("Municipality.Municipality", new BasicDBObject("$nin", munc));
	        	
	        }
	        return query;
}
}
