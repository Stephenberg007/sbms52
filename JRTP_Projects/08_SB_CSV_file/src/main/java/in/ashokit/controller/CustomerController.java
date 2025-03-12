package in.ashokit.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import in.ashokit.entity.Customer;
import in.ashokit.repo.CustomerRepository;
import in.ashokit.service.CsvService;

@RestController
@RequestMapping("/api")
public class CustomerController {
    
    private final CsvService csvService;

    public CustomerController(CustomerRepository customerRepository, CsvService csvService) {
        
        this.csvService = csvService;
    }

    // Endpoint to download CSV
    @GetMapping("/csv")
    public ResponseEntity<InputStreamResource> exportCsv() {
        

        ByteArrayInputStream csvData = csvService.generateCsv();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customers.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(csvData));
    }
    
    @GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> downloadPdf() throws IOException, DocumentException {
     
        FileInputStream fileInputStream = csvService.generatePdf();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customers.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(fileInputStream));
    }

    
}
