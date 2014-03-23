package com.sample.processor;

import com.sample.exception.UserInputException;
import com.sample.model.AmortizationInputs;
import com.sample.model.Inputs;
import com.sample.utility.PrintUtil;
import com.sample.utility.RangeUtil;
import com.sample.utility.RangeUtil.APR;
import com.sample.utility.RangeUtil.BorrowAmount;
import com.sample.utility.RangeUtil.Term;

public class AmortizationScheduleProcessor extends ScheduleProcessor {

	private Double amount;
	private Double apr;
	private Integer years;

	@Override
	public String[] getUserPrompts() {
		String[] prompts = {
				"Please enter the amount you would like to borrow: ",
				"Please enter the annual percentage rate used to repay the loan: ",
				"Please enter the term, in years, over which the loan is repaid: " };

		return prompts;
	}

	@Override
	public boolean checkIfValidInput(String input, int index) {
		boolean isValidValue = true;
		try {
			switch (index) {
			case 0:
				amount = Double.parseDouble(input);
				if (RangeUtil.isValidBorrowAmount(amount) == false) {
					isValidValue = false;

					PrintUtil.printf(BorrowAmount.getRangeMessage());
				}
				break;
			case 1:
				apr = Double.parseDouble(input);
				if (RangeUtil.isValidAPRValue(apr) == false) {
					isValidValue = false;

					PrintUtil.printf(APR.getRangeMessage());
				}
				break;
			case 2:
				years = Integer.parseInt(input);
				if (RangeUtil.isValidTerm(years) == false) {
					isValidValue = false;

					PrintUtil.printf(Term.getRangeMessage());
				}
				break;
			}
		} catch (NumberFormatException e) {
			isValidValue = false;
		}
		return isValidValue;
	}

	@Override
	public Inputs getInputs() throws UserInputException {
		if (amount == null || apr == null || years == null) {
			throw new UserInputException("Inputs are not properly initialized.");
		}
		return new AmortizationInputs(amount, apr, years);
		
	}

}
