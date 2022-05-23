package com.nttdata.bootcamp.services.impl;

import java.math.BigDecimal;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.exceptions.TypeTransactionException;
import com.nttdata.bootcamp.models.TransactionEnterprise;
import com.nttdata.bootcamp.models.products.SavingAccount;
import com.nttdata.bootcamp.repositories.ITransactionEnterpriseRepo;
import com.nttdata.bootcamp.services.ITransactionEnterpriseService;
import com.nttdata.bootcamp.utils.Constants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionEnterpriseImpl implements ITransactionEnterpriseService {
	
	@Autowired
	ITransactionEnterpriseRepo terepo;
	
	@Autowired
	private RestTemplate clientRest;
	
	private WebClient customerServiceClient = WebClient.builder().baseUrl("http://localhost:8024").build();
	
	private Function<Mono<SavingAccount>, Mono<SavingAccount>>
	updateSavingAccount = (objeto) -> customerServiceClient.patch()
	.uri("/savingAccount/update")
	.body(objeto, SavingAccount.class)
	.retrieve()
	.bodyToMono(SavingAccount.class);

	@Override
	public Flux<TransactionEnterprise> findAll() {
		return terepo.findAll();
	}

	@Override
	public Mono<TransactionEnterprise> findById(String id) {
		return terepo.findById(id);
	}
	
	@Override
	public Mono<TransactionEnterprise> save(TransactionEnterprise transactionEnterprise){

		Mono<SavingAccount> savingAccount = customerServiceClient.get()
				.uri("/savingAccount/findById/"+transactionEnterprise.getIdProduct())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(SavingAccount.class)
				.flatMap(p->{
					switch (transactionEnterprise.getTypeTransaction()){
						case Constants.WITHDRAWAL:
							p.withdrawal(new BigDecimal(transactionEnterprise.getAmount()));
							break;
						case Constants.DEPOSIT:
							p.deposit(new BigDecimal(transactionEnterprise.getAmount()));
							break;
						default:
							return Mono.error( new TypeTransactionException("The type transaction is incorret"));
					}
					return Mono.just(p);
				});
		return updateSavingAccount.apply(savingAccount).flatMap(p->{
			return terepo.save(transactionEnterprise);
		});
	}

	@Override
	public Mono<Void> delete(TransactionEnterprise transactionEnterprise) {
		return terepo.delete(transactionEnterprise);
	}
	
}
