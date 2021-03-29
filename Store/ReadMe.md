1.Import ExcelSheet API Endpoint
  
  ```Text
    
    POST Request:
    http://localhost:8080/erp/importSheet
    RequestBody:Input Excel File.(Required)
  
  ```
2.Get Units Data API EndPoint

```Text

      GetRequest:
         http://localhost:8080/erp/getUnits?pageNo=0&pageSize=10
      RequestParam:
         1.pageNo.(Required)
         2.pageSize.(Required)
         3.unitName.(Optional)
         4.unitType.(Optional)
```  
  
