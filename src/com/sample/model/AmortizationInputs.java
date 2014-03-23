package com.sample.model;

public class AmortizationInputs implements Inputs{
	private double amount;
	private double apr;
	private int years;

	public AmortizationInputs(double amount, double apr, int years) {
		this.amount = amount;
		this.apr = apr;
		this.years = years;
	}
	
	public double getAmount() {
		return amount;
	}

	public double getApr() {
		return apr;
	}

	public int getYears() {
		return years;
	}
	
	
}
