package com.sample.utility;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sample.exception.UserInputException;

/**
 * This utility class is responsible for input data from different source. For
 * example, console, system in etc.
 * 
 * @author manu sinha
 * 
 */
public class InputUtil {
	public static String readLine(String userPrompt) throws IOException, UserInputException {

		// in case there is nothing to prompt the user with, we do not proceed
		if (userPrompt == null || userPrompt.length() == 0) {
			throw new UserInputException("User Prompt is missing");
		}
		String line = "";
		Console console = System.console();
		if (console != null) {
			line = console.readLine(userPrompt);
		} else {

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(System.in));

			PrintUtil.printf(userPrompt);
			line = bufferedReader.readLine();
		}
		line.trim();
		return line;
	}
}
