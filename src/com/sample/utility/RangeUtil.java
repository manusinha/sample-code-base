package com.sample.utility;

import java.text.MessageFormat;

import com.sample.shared.AppConstants;

/**
 * This is the utility class that has methods and corresponding ENUMs to check
 * if borrow amount, APR value and term are in range or not.
 * 
 * @author manu sinha
 * 
 */
public class RangeUtil {

	/**
	 * This method checks if the amount in the range or not.
	 * 
	 * @param amount
	 * @return
	 */
	public static boolean isValidBorrowAmount(double amount) {

		if (amount < BorrowAmount.LOWER.value) {
			return false;
		}

		if (amount > BorrowAmount.UPPER.value) {
			return false;
		}

		return true;

	}

	/**
	 * This method checks if the APR is in the range or not.
	 * 
	 * @param rate
	 * @return
	 */
	public static boolean isValidAPRValue(double rate) {

		if (rate < APR.LOWER.value) {
			return false;
		}

		if (rate > APR.UPPER.value) {
			return false;
		}

		return true;

	}

	/**
	 * This method checks if the term is in the range or not.
	 * 
	 * @param years
	 * @return
	 */
	public static boolean isValidTerm(int years) {

		if (years < Term.LOWER.value) {
			return false;
		}

		if (years > Term.UPPER.value) {
			return false;
		}

		return true;

	}
	

	/**
	 * This ENUM keeps the LOWER and UPPER limit for Borrow Amount.
	 * 
	 * @author manu sinha
	 * 
	 */
	public enum BorrowAmount {

		LOWER(0.01d), UPPER(1000000000000d);

		private double value;

		private BorrowAmount(double value) {
			this.value = value;
		}
		
		public double getValue() {
			return value;
		}
		
		public static String getRangeMessage(){
			return MessageFormat.format(AppConstants.rangeMessageStr, LOWER.value, UPPER.value);
		}
		
		
	}

	/**
	 * This ENUM keeps the LOWER and UPPER limit for APR.
	 * 
	 * @author manu sinha
	 * 
	 */
	public enum APR {

		LOWER(0.000001d), UPPER(100d);

		private double value;

		private APR(double value) {
			this.value = value;
		}
		
		public double getValue() {
			return value;
		}

		public static String getRangeMessage(){
			return MessageFormat.format(AppConstants.rangeMessageStr, LOWER.value, UPPER.value);
		}
		
	}

	/**
	 * This ENUM keeps the LOWER and UPPER limit for Term.
	 * 
	 * @author manu sinha
	 * 
	 */
	public enum Term {

		LOWER(1), UPPER(1000000);

		private int value;

		private Term(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}

		public static String getRangeMessage(){
			return MessageFormat.format(AppConstants.rangeMessageIntegerStr, LOWER.value, UPPER.value);
		}
	}

}
