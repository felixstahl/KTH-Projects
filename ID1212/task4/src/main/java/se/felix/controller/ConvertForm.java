package se.felix.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import se.felix.integration.DbHandler;

public class ConvertForm {
	
	@NotNull
	private String fromCurrency;

	@NotNull
	private String toCurrency;
	
	@NotNull
	@PositiveOrZero
	private double amount;
	
	private double result;

	public String getFromCurrency() {
		return this.fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	
	public String getToCurrency() {
		return this.toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void setResult() {
		DbHandler db = new DbHandler();
		this.result = amount * db.convert(this.fromCurrency, this.toCurrency);
	}
	
	public double getResult() {
		return this.result;
	}
}
