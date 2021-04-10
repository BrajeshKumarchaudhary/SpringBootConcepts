## Paytm Payment gateway Integration

1. Add payment gateway SDk in pom.xml
 ```text
     <repositories>
    <repository>
        <id>my-repo1</id>
        <url> http://artifactorypg.paytm.in/artifactory/libs-release </url>
    </repository>
   </repositories>
   
   <dependency>
        <groupId>com.paytm.pg</groupId>
        <artifactId>paytm-pg</artifactId>
        <version>0.0.6</version>
  </dependency>
 
   ```
   
2. Build and install 
  ```text
      mvn install
    ```
    
 3. Generate API Keys 
   ```text
       1. MID:MerchantId
       2.Merchant Key
     ```
     
