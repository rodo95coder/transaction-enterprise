package com.nttdata.bootcamp.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transaction_enterprises")
@Builder
public class TransactionEnterprise {
	@Id
	private String id;
	private String idCustomerEnterprise;
	private String idProduct;
	private String productName;
	private String typeTransaction;
	private String amount;
	private String createdAt;
}
