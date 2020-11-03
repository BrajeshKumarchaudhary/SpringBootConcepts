package com.auth.app.util;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.app.model.InputData;
import com.auth.app.repository.InputDataRepo;

@Service
public class Util {

	@Autowired
   private InputDataRepo inputrepo;
	
	
    /**
     * Returns true if a value is true. Useful for method references
     */
    public static Boolean isTrue(Boolean value) {
        return value;
    }

    /**
     * Generate a random UUID
     */
    public static String generateRandomUuid() {
        return UUID.randomUUID().toString();
    }
    public  List<InputData> getInputData(){
		return this.inputrepo.findAll();
	}
	public  Map<String,String> getMailProp(){
		return this.getInputData().stream().collect(Collectors.toMap(InputData::getDataKey, InputData::getDataValue));
	}
	public  void updateTokenExpire(String tokenExpires, String key) {
                   this.inputrepo.updateTokenExpire(tokenExpires, key);    		
	}
	public void updateAccessToken(String accesstoken, String key) {
		this.inputrepo.updateAccessToken(accesstoken, key);
	}

}
