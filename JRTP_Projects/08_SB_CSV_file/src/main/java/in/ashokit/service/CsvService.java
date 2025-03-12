package in.ashokit.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import in.ashokit.entity.Customer;
import in.ashokit.repo.CustomerRepository;

@Service
public class CsvService {
	 private static final String FILE_PATH = "customers.pdf";
	
	 @Autowired
	 CustomerRepository custRepo;
	 
	
	 
	
    public ByteArrayInputStream generateCsv() {
    	List<Customer> customers= custRepo.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader("Name", "Phone"))) {
            for (Customer customer : customers) {
                csvPrinter.printRecord(customer.getName(), customer.getPhone());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error while writing CSV", e);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
    
    // FOR PDF FILE
    
    public FileInputStream generatePdf() throws IOException, DocumentException {
    	
    	List<Customer> customers= custRepo.findAll();
    	
        File file = new File(FILE_PATH); // Create File object
        Document document = new Document(); // Create PDF document

        // Create PDF writer
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open(); // Open document for writing

        // Add title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph title = new Paragraph("Customer Details\n\n", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add customer data
        for (Customer customer : customers) {
            document.add(new Paragraph("Name: " + customer.getName() + " , Phone: " + customer.getPhone()+ "  , City :- "+customer.getCity()));
        }

        document.close(); // Close document
        return new FileInputStream(file); // Return FileInputStream
    }
}
    
