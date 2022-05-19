package com.nttdata.bootcamp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.models.TransactionEnterprise;

public interface ITransactionEnterpriseRepo extends ReactiveMongoRepository<TransactionEnterprise, String>{

}
