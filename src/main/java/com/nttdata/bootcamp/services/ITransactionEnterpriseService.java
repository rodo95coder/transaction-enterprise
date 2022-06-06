package com.nttdata.bootcamp.services;

import com.nttdata.bootcamp.models.TransactionEnterprise;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionEnterpriseService {
	public Flux<TransactionEnterprise> findAll();
	public Mono<TransactionEnterprise> findById(String id);
	public Mono<TransactionEnterprise> save(TransactionEnterprise transactionEnterprise);
	public Mono<Void> delete(TransactionEnterprise transactionEnterprise);
}