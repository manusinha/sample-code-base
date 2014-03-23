package com.sample.executor;

import java.util.List;

import com.sample.exception.UserInputException;
import com.sample.model.AmortizationHeadings;
import com.sample.model.AmortizationSchedule;
import com.sample.model.PaymentData;
import com.sample.model.Schedule;
import com.sample.processor.AmortizationScheduleProcessor;
import com.sample.processor.ScheduleProcessor;
import com.sample.utility.PrintUtil;

public class ScheduleExecutor {
	public static void main(String[] args) {

		ScheduleProcessor asc = new AmortizationScheduleProcessor();

		try {
			asc.promptUserForInputs();
			Schedule aSchedule = new AmortizationSchedule(asc.getInputs());
			List<PaymentData> payments = aSchedule.process();

			PrintUtil.printOutputTable(AmortizationHeadings.values(), payments,
					null);
		} catch (IllegalArgumentException e) {
			PrintUtil
					.printError("Unable to process the values entered. Terminating program.\n");
		} catch (UserInputException e) {
			PrintUtil
					.printError("Unable to retrieve user entered values correctly. Terminating program.\n");
		}
	}
}
