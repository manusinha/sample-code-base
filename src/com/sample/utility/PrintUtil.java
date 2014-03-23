package com.sample.utility;

import java.io.Console;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

import com.sample.model.AmortizationPaymentData;
import com.sample.model.Headings;
import com.sample.model.PaymentData;

public class PrintUtil {

	private static final String DEFAULT_FORMAT = "%1$-20d%2$-20.2f%3$-20.2f%4$-20.2f%5$-20.2f%6$-20.2f\n";
	// returns the unique console object associated with the current JVM, if
	// present
	private static Console console = System.console();

	public static void printOutputTable(Console console, List<String> heading,
			List<PaymentData> payment) {

	}

	public static void printOutputTable(Headings[] headings,
			List<PaymentData> payments, String formatString) {

		if (formatString == null || formatString.isEmpty()) {
			formatString = DEFAULT_FORMAT;
		}

		printHeader(headings);

		for (PaymentData iPayment : payments) {
			printPayment(formatString, iPayment);
		}

	}

	private static void printHeader(Headings[] headings) {
		if (headings == null || headings.length == 0) {
			return;
		}

		List<String> headingList = new ArrayList<String>();

		for (Headings heading : headings) {
			headingList.add(heading.getValue());
		}

		printf("%1$-20s%2$-20s%3$-20s%4$-20s%5$-20s%6$-20s\n",
				headingList.toArray());
	}

	public static void printPayment(String formatString, PaymentData iPayment) {
		if (iPayment == null) {
			return;
		}
		AmortizationPaymentData payment = (AmortizationPaymentData) iPayment;
		Object[] args = new Object[] { payment.getPaymentNumber(),
				payment.getPaymentAmount(), payment.getPaymentInterest(),
				payment.getCurrentBalance(), payment.getTotalPayments(),
				payment.getTotalInterestPaid() };

		printf(formatString, args);
	}

	public static void printf(String formatString, Object... args) {

		try {
			if (console != null) {
				console.printf(formatString, args);
			} else {
				System.out.print(String.format(formatString, args));
			}
		} catch (IllegalFormatException e) {

			System.err.print("Error printing...\n");
		}

	}

	public static void printf(String s) {
		printf("%s", s);
	}

	public static void printError(String s) {
		System.out.print(String.format("%s", s));
	}
}
