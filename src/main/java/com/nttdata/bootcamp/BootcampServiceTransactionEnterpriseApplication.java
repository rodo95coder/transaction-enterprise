package com.nttdata.bootcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.nttdata.bootcamp.models.TransactionEnterprise;
import com.nttdata.bootcamp.repositories.ITransactionEnterpriseRepo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
public class BootcampServiceTransactionEnterpriseApplication implements CommandLineRunner{

	@Autowired
	ITransactionEnterpriseRepo terepo;
	
	@Autowired
	ReactiveMongoTemplate mongoTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(BootcampServiceTransactionEnterpriseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		mongoTemplate.dropCollection("transaction_enterprises").subscribe();
		
		Flux.just(TransactionEnterprise.builder()
				.idCustomerEnterprise("b1")
				.idProduct("120")
				.productName("currentAccount")
				.typeTransaction("paid")
				.amount("13")
				.createdAt("19-05-22")
				.build()).flatMap(bs->{
						return terepo.save(bs);
				}).subscribe(s-> log.info("Se ingreso transactionEnterprise: "+s));
		
	}

}
