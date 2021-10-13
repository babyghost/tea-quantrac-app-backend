package vn.tea.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages= {"vn"}) 
@EntityScan("vn")
@EnableJpaRepositories("vn")
@EnableScheduling
@Slf4j
public class TeaAppBackendApplication {

	public static void main(String[] args) {
		log.debug("app main");
		SpringApplication.run(TeaAppBackendApplication.class, args);
		
	}
	
//	  @Bean
//	  CommandLineRunner lookup(ClientSMS quoteClient) {
//	    return args -> {
//	      String country = "Spain";
//
//	      if (args.length > 0) {
//	        country = args[0];
//	      }
//	      SendSMSResponse response = quoteClient.getResponse("" , "", "", "");
//	      System.err.println(response.getSendSMSResult());
//	    };
//	  }

}
