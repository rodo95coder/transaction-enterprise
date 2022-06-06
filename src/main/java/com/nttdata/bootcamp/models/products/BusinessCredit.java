package com.nttdata.bootcamp.models.products;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "businesscredits")
@Builder
public class BusinessCredit {
  @Id
  private String id;
  private String idCustomerEnterprise;
  private String accountingBalance;
  private String availableBalance;
}