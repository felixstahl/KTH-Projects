package se.felix.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ConvertForm {
	
	@NotNull
	private String fromCurrency;

	@NotNull
	private String toCurrency;
	
	@NotNull
	@Min(value = 0)
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
	
	public void setResult(double result) {
		this.result = amount * result;
	}
	
	public double getResult() {
		return this.result;
	}
}
