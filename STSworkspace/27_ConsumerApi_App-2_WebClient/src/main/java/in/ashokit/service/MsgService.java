package in.ashokit.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.ashokit.binding.AllQuotes;
import in.ashokit.binding.Quote;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MsgService {
	
	String providerUrl="https://dummyjson.com/quotes/random";
	String provUrl="https://dummyjson.com/quotes";
	WebClient webClient = WebClient.create();
	
	public void getRandomQuote() {
		Mono<Quote> bodyToMono = webClient.get()
											.uri(providerUrl)
											.retrieve().bodyToMono(Quote.class);
											//.bodyToMono(String.class);
		Quote block = bodyToMono.block();
		System.out.println(block);
		
	}
	public void getRandomQuote1() {
		Flux<AllQuotes> bodyToFlux = webClient.get()
											.uri(provUrl)
											.retrieve().bodyToFlux(AllQuotes.class);
											//.bodyToMono(String.class);
		//Quote block = bodyToFlux.block();
		  bodyToFlux.subscribe(quote -> System.out.println("Quote: " + quote));
		//System.out.println(bodyToFlux);
		
	}
	
public void getRandomQuote2() {
		System.out.println("-Request Sending Started----");//1st Sop statement
					Disposable subscribe = webClient.get()
											.uri(providerUrl)
											.retrieve()
											.bodyToMono(Quote.class)
											//.block()
											.subscribe(response -> {
												handleResponse(response);
											});
											
		System.out.println("-Request Sending Completed----");//3rd Sop
		
		
	}
	public static void handleResponse(Quote response) {
		System.out.println(response);//2nd Sop
		// in future push reponse to kafka server;
	}
	
}
