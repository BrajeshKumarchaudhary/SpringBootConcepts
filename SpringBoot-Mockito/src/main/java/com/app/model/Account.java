package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	private Integer accountId;
	private String accountNumber;
	private String accountHolderName;
	private String branchName;
	private String branchCode;

}
