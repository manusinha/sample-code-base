package com.sample.processor;

import java.io.IOException;

import com.sample.exception.UserInputException;
import com.sample.model.Inputs;
import com.sample.utility.InputUtil;
import com.sample.utility.PrintUtil;

public abstract class ScheduleProcessor {
	
	protected abstract String[] getUserPrompts() throws UserInputException;
	
	protected abstract boolean checkIfValidInput(String input, int index);
	
	public abstract Inputs getInputs() throws UserInputException;
	
	public void promptUserForInputs() throws UserInputException {

		String[] userPrompts = getUserPrompts();

		String input = "";

		for (int i = 0; i < userPrompts.length;) {
			String userPrompt = userPrompts[i];
			try {
				input = InputUtil.readLine(userPrompt);
			} catch (IOException e) {
				PrintUtil
						.printError("An IOException was encountered. Terminating program.\n");
				return;
			} 

			if (checkIfValidInput(input, i)) {
				i++;
			} else {
				PrintUtil.printf("An invalid value was entered.\n");
			}
		}
	}
}
