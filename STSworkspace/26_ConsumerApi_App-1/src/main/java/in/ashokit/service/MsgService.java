package in.ashokit.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.ashokit.binding.AllQuotes;
import in.ashokit.binding.Quote;

@Service
public class MsgService {
	RestTemplate rt = new RestTemplate();
	
	public void getApiResponse() {
		
		String providerUrl = "https://api.restful-api.dev/objects/";
		
		ResponseEntity<String> forEntity = rt.getForEntity(providerUrl, String.class);
		int value = forEntity.getStatusCode().value();
		String body = forEntity.getBody();
		System.out.println("Status Code Value :-"+value+" BODY :-"+body);
	}
	// Now I am accessing JSON data from the PROVIDER
	public void getRandomQuote() {
		String providerUrl = "https://dummyjson.com/quotes/random";
		ResponseEntity<String> forEntity = rt.getForEntity( providerUrl, String.class);
		//ResponseEntity<Quote> forEntity = rt.getForEntity(providerUrl, Quote.class);// conversion of Json into a JAVA Object
		System.out.println("Resonse Code : "+forEntity.getStatusCode().value());
		System.out.println("Response Body :- "+forEntity.getBody());
	}
	
//	public void getAllQuotes() {
//		String provUrl = "https://dummyjson.com/quotes";// Now I am getting all quotes from The Url.
//		ResponseEntity<AllQuotes> allQuotes = rt.getForEntity(provUrl, AllQuotes.class);
//		List<Quote> quotes=allQuotes.getBody().getQuotes();
//		quotes.forEach(quote -> {
//			System.out.println(quote);
//		});
//		
//	}
	public List<Quote> getAllQuotes() {//Here I am hitting Provider URL through Consumer
		String provUrl = "https://dummyjson.com/quotes";// Now I am getting all quotes from The Url.
		ResponseEntity<AllQuotes> allQuotes = rt.getForEntity(provUrl, AllQuotes.class);
		List<Quote> quotes=allQuotes.getBody().getQuotes();//getBody() would have given us value for all the keys of JSON
			return quotes;	
	}
	
	
}
