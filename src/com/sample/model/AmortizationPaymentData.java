package com.sample.model;


public class AmortizationPaymentData implements PaymentData {

	int paymentNumber;
	double paymentAmount;
	double paymentInterest;
	double currentBalance;
	double totalPayments;
	double totalInterestPaid;

	public AmortizationPaymentData(int paymentNumber, double paymentAmount,
			double paymentInterest, double currentBalance,
			double totalPayments, double totalInterestPaid) {
		this.paymentNumber = paymentNumber;
		this.paymentAmount = paymentAmount;
		this.paymentInterest = paymentInterest;
		this.currentBalance = currentBalance;
		this.totalPayments = totalPayments;
		this.totalInterestPaid = totalInterestPaid;
	}

	public int getPaymentNumber() {
		return paymentNumber;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public double getPaymentInterest() {
		return paymentInterest;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public double getTotalPayments() {
		return totalPayments;
	}

	public double getTotalInterestPaid() {
		return totalInterestPaid;
	}

	public int getFormattedPaymentNumber() {
		return paymentNumber;
	}

	@Override
	public String toString() {
		return "Payment [paymentNumber=" + paymentNumber + ", paymentAmount="
				+ paymentAmount + ", paymentInterest=" + paymentInterest
				+ ", currentBalance=" + currentBalance + ", totalPayments="
				+ totalPayments + ", totalInterestPaid=" + totalInterestPaid
				+ "]";
	}

}
