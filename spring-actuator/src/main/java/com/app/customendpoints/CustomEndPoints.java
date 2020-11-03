package com.app.customendpoints;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom-endpoint")
public class CustomEndPoints {

	@ReadOperation
	public String getMessage()
	{
		return "Hello world i am custom Endpoints";
	}
	@ReadOperation
    public String customEndPointByName(@Selector String name) {
        return "custom-end-point--"+name;
    }

    @WriteOperation
    public void writeOperation(@Selector String name) {
        //perform write operation
    }
   @DeleteOperation
   public void deleteOperation(@Selector String name){
    //delete operation
  }
}
