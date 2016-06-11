package org.urdad.services.mocking.examples;

import java.time.Duration;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class Person 
{

	public Person(String name, LocalDate dateOfBirth, Address address)
	{
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}

	public String getName()
	{
		return name;
	}

	public int getAge()
	{
		return (int) Duration.between(dateOfBirth, LocalDate.now()).toDays() / 365;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	private LocalDate dateOfBirth;
	@NotNull
	private Address address;

}