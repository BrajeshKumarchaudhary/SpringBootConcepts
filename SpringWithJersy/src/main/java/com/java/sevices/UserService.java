package com.java.sevices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.java.model.User;
import com.java.util.Users;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="users")
@Path("users")
public class UserService {
     private static Map<Integer,User> map=new HashMap<>();
     static {
    	 User user1=new User();
    	 user1.setId(1);
    	 user1.setName("barjesh kumar");
    	 user1.setCompany("square yards");
    	 User user=new User();
    	 user.setId(2);
    	 user.setName("barjesh kumar");
    	 user.setCompany("square yards");
    	 map.put(user1.getId(), user1);
    	 map.put(user.getId(), user);
     }
     @GET
     @Produces("application/json")
     public Users getAllUser()
     {
    	Users us=new Users(); 
    	us.setList(new ArrayList<>(map.values()));
    	return us;
     }

}
