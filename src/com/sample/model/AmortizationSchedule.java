package com.sample.model;

import java.util.ArrayList;
import java.util.List;

import com.sample.shared.AppConstants;
import com.sample.utility.RangeUtil;

public class AmortizationSchedule implements Schedule {
	
	private AmortizationInputs inputs;

	private long amountBorrowed = 0; // in cents
	private double apr = 0d;
	private int initialTermMonths = 0;

	private double monthlyInterest = 0d;
	private long monthlyPaymentAmount = 0; // in cents

	private List<PaymentData> monthlyPayments;

	public AmortizationSchedule(Inputs inputs) {
		this.inputs = (AmortizationInputs) inputs;

		init();
	}

	private void init() {
		
		if ((RangeUtil.isValidBorrowAmount(inputs.getAmount()) == false)
				|| (RangeUtil.isValidAPRValue(inputs.getApr()) == false)
				|| (RangeUtil.isValidTerm(inputs.getYears()) == false)) {
			throw new IllegalArgumentException();
		}

		amountBorrowed = Math.round(inputs.getAmount() * 100);
		apr = inputs.getApr();
		initialTermMonths = inputs.getYears() * 12;

		monthlyPaymentAmount = calculateMonthlyPayment();

		/* 
		 * The following shouldn't happen with the available valid ranges
		 * for borrow amount, apr, and term; however, without range validation,
		 * monthlyPaymentAmount as calculated by calculateMonthlyPayment()
		 * may yield incorrect values with extreme input values
		 */
		if (monthlyPaymentAmount > amountBorrowed) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public List<PaymentData> process() {
		monthlyPayments = new ArrayList<PaymentData>();
		// processing logic starts-----
		long balance = amountBorrowed;
		int paymentNumber = 0;
		long totalPayments = 0;
		long totalInterestPaid = 0;

		// first payment
		addMonthlyPaymentData(paymentNumber++, 0d, 0d,
				((double) amountBorrowed) / 100d,
				((double) totalPayments) / 100d,
				((double) totalInterestPaid) / 100d);

		final int maxNumberOfPayments = initialTermMonths + 1;
		while ((balance > 0) && (paymentNumber <= maxNumberOfPayments)) {
			// Calculate H = P x J, this is your current monthly interest
			long curMonthlyInterest = Math.round(((double) balance)
					* monthlyInterest);

			// the amount required to payoff the loan
			long curPayoffAmount = balance + curMonthlyInterest;

			// the amount to payoff the remaining balance may be less than the
			// calculated monthlyPaymentAmount
			long curMonthlyPaymentAmount = Math.min(monthlyPaymentAmount,
					curPayoffAmount);

			// it's possible that the calculated monthlyPaymentAmount is 0,
			// or the monthly payment only covers the interest payment - i.e. no
			// principal
			// so the last payment needs to payoff the loan
			if ((paymentNumber == maxNumberOfPayments)
					&& ((curMonthlyPaymentAmount == 0) || (curMonthlyPaymentAmount == curMonthlyInterest))) {
				curMonthlyPaymentAmount = curPayoffAmount;
			}

			// Calculate C = M - H, this is your monthly payment minus your
			// monthly interest,
			// so it is the amount of principal you pay for that month
			long curMonthlyPrincipalPaid = curMonthlyPaymentAmount
					- curMonthlyInterest;

			// Calculate Q = P - C, this is the new balance of your principal of
			// your loan.
			long curBalance = balance - curMonthlyPrincipalPaid;

			totalPayments += curMonthlyPaymentAmount;
			totalInterestPaid += curMonthlyInterest;

			// output is in dollars
			addMonthlyPaymentData(paymentNumber++,
					((double) curMonthlyPaymentAmount) / 100d,
					((double) curMonthlyInterest) / 100d,
					((double) curBalance) / 100d,
					((double) totalPayments) / 100d,
					((double) totalInterestPaid) / 100d);

			// Set P equal to Q and go back to Step 1: You thusly loop around
			// until the value Q (and hence P) goes to zero.
			balance = curBalance;
		}

		// processing logic ends-------
		return monthlyPayments;
	}

	/**
	 * M = P * (J / (1 - (Math.pow(1/(1 + J), N)))); Where: P = Principal I =
	 * Interest J = Monthly Interest in decimal form: I / (12 * 100) N = Number
	 * of months of loan M = Monthly Payment Amount
	 */
	private long calculateMonthlyPayment() {
		// calculate J
		monthlyInterest = apr / AppConstants.MONTHLY_INTEREST_DIVISOR;

		// this is 1 / (1 + J)
		double tmp = Math.pow(1d + monthlyInterest, -1);

		// this is Math.pow(1/(1 + J), N)
		tmp = Math.pow(tmp, initialTermMonths);

		// this is 1 / (1 - (Math.pow(1/(1 + J), N))))
		tmp = Math.pow(1d - tmp, -1);

		// M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
		double rc = amountBorrowed * monthlyInterest * tmp;

		return Math.round(rc);
	}

	private void addMonthlyPaymentData(int paymentNumber, double paymentAmount,
			double paymentInterest, double currentBalance,
			double totalPayments, double totalInterestPaid) {
		// initial payment
		PaymentData payment = new AmortizationPaymentData(paymentNumber, paymentAmount,
				paymentInterest, currentBalance, totalPayments,
				totalInterestPaid);
		monthlyPayments.add(payment);
	}

	public enum Headings{
		PAYMENT_NUMBER("Payment Number"),
		PAYMENT_AMOUNT("Payment Amount"),
		PAYMENT_INTEREST("Payment Interest"),
		CURRENT_BALANCE("Current Balance"),
		TOTAL_PAYMENTS("Total Payments"),
		TOTAL_INTEREST_PAID("Total Interest Paid");

		private String value;

		private Headings(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		@Override
		public String toString() {
			return "Headings [value=" + value + "]";
		}
		
	}

}
