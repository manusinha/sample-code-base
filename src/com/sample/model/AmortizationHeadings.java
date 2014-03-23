package com.sample.model;

public enum AmortizationHeadings implements Headings {
	PAYMENT_NUMBER("Payment Number"), 
	PAYMENT_AMOUNT("Payment Amount"), 
	PAYMENT_INTEREST("Payment Interest"), 
	CURRENT_BALANCE("Current Balance"), 
	TOTAL_PAYMENTS("Total Payments"), 
	TOTAL_INTEREST_PAID("Total Interest Paid");

	private String value;

	private AmortizationHeadings(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "AmortizationScheduleHeadings [value=" + value + "]";
	}
}
