package in.ashokit.Beans;

import org.springframework.batch.item.ItemProcessor;

import in.ashokit.record.Person;

public class PersonItemProcessor implements ItemProcessor<Person,Person>{

	@Override
	public Person process(Person item) throws Exception {// Dummy logic-- we can write logic to convert data to pdf also
		final String firstName = item.firstName().toUpperCase();// item is coming from .csv file
		final String lastName = item.lastName().toUpperCase();
		
		final Person transformedPerson = new Person(firstName, lastName);
		return transformedPerson;
	}

}
