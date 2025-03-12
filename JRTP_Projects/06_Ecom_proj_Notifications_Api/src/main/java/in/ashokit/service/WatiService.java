package in.ashokit.service;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*
 * @Service public class WatiService {
 * 
 * private final RestTemplate restTemplate = new RestTemplate();
 * 
 * @Value("${wati.api.url}") private String watiApiUrl;
 * 
 * @Value("${wati.api.key}") private String apiKey;
 * 
 * public String sendMessage(String phoneNumber, String message) { String url =
 * watiApiUrl + "/sendSessionMessage/"+phoneNumber;
 * 
 * 
 * HttpHeaders headers = new HttpHeaders(); headers.set("Authorization",
 * apiKey); headers.setContentType(MediaType.APPLICATION_JSON);
 * 
 * // JSON Body with message text Map<String, String> body =
 * Collections.singletonMap("messageText", message);
 * 
 * System.out.println("Sending Request: " + body);
 * 
 * HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
 * ResponseEntity<String> response = restTemplate.postForEntity(url, request,
 * String.class);
 * 
 * System.out.println("Response: " + response.getBody());
 * 
 * return response.getBody(); } }
 */