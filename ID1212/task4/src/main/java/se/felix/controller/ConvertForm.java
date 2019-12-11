package se.felix.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class ConvertForm {
	
	@NotNull
	@Size(min=3, max=3)
	private String fromCurrency;

	@NotNull
	@Size(min=3, max=3)
	private String toCurrency;
	
	@NotNull
	@PositiveOrZero
	private Integer amount;

	public String getFromCurrency() {
		return this.fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	
	public String getToCurrency() {
		return this.fromCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
