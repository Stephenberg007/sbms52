package in.ashokit.binding;

import java.util.List;

import lombok.Data;

@Data
public class AllQuotes {
	private List<Quote> quotes;
	private Integer total;
	private Integer skip;
	private Integer limit;
	
	
}
