package com.nttdata.bootcamp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.models.TransactionEnterprise;
import com.nttdata.bootcamp.services.ITransactionEnterpriseService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/transactionEnterprise")
public class TransactionEnterpriseController {
	
	@Autowired
	private ITransactionEnterpriseService terepo;
	
	@GetMapping("/findAll")
	public Flux<TransactionEnterprise> findAll(){
		return terepo.findAll();
	}
	
	@PutMapping("/findById/{id}")
	public Mono<TransactionEnterprise> findById(@PathVariable String id){
		log.info("a TransactionEnterprise was consulted");
		return terepo.findById(id);
	}
	
	@PostMapping("/save")
	public Mono<TransactionEnterprise> save(@RequestBody TransactionEnterprise transactionEnterprise){
		log.info("a TransactionEnterprise was created");
		return terepo.save(transactionEnterprise);
	}
	
	@DeleteMapping("/delete")
	public Mono<Void> delete(@RequestBody TransactionEnterprise transactionEnterprise){
		return terepo.delete(transactionEnterprise);
	}

}