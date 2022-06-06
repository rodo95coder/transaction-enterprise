package com.nttdata.bootcamp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.models.TransactionEnterprise;
import com.nttdata.bootcamp.repositories.ITransactionEnterpriseRepo;
import com.nttdata.bootcamp.services.ITransactionEnterpriseService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionEnterpriseImpl implements ITransactionEnterpriseService {
	
	@Autowired
	ITransactionEnterpriseRepo terepo;

	@Override
	public Flux<TransactionEnterprise> findAll() {
		return terepo.findAll();
	}

	@Override
	public Mono<TransactionEnterprise> findById(String id) {
		return terepo.findById(id);
	}

	@Override
	public Mono<TransactionEnterprise> save(TransactionEnterprise transactionEnterprise) {
		return terepo.save(transactionEnterprise);
	}

	@Override
	public Mono<Void> delete(TransactionEnterprise transactionEnterprise) {
		return terepo.delete(transactionEnterprise);
	}
	
}