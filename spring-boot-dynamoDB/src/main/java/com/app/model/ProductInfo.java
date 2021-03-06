package com.app.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerated;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "ProductInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
	@DynamoDBAutoGeneratedKey
	private String id;
	@DynamoDBAttribute
	private String msrp;
	@DynamoDBAttribute
	private String cost;
}
