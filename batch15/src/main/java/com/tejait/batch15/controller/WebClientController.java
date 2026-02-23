package com.tejait.batch15.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
@RestController
@RequestMapping("wedclient")
public class WebClientController {
	WebClient webclient;
	@GetMapping("asynchName")
	public String asynchronousType() {
		
	String name= "Teja IT";
	Mono<String>tagline= webclient.get()
	    		  .uri("http://localhost:8081/test/tagline")
	    		  .retrieve()
	    		  .bodyToMono(String.class);
	    		  return name.concat(" "+tagline.block());
	    		  
	
	}
	
	
		@GetMapping("synchName")
		public String synchronousType() {
			
		String name= "Teja II";
		String tagline= webclient.get()
		    		  .uri("http://localhost:8081/test/tagline")
		    		  .retrieve()
		    		  .bodyToMono(String.class)
		    		  .block();
		
		    		  return name.concat(" "+tagline);
		    		  
		
		}
		  

		
	  

	

}
