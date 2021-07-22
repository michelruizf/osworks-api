package com.test;

import java.util.Stack;

public class Test {

	public static String maskify(String creditCardNumber) {
		// Add code here
		if (creditCardNumber == null || creditCardNumber.trim().isEmpty())
			return "";

		if (creditCardNumber.length() < 6)
			return creditCardNumber;

		int creditCardLength = creditCardNumber.length();
		String firstDigits = String.valueOf(creditCardNumber.charAt(0));
		String innerDigits = creditCardNumber.substring(1, creditCardLength - 4).replaceAll("[0-9]", "#");
		String lastDigits = creditCardNumber.substring(creditCardLength - 4);

		return firstDigits.concat(innerDigits).concat(lastDigits);

	}

	private static String numberToOrdinal( Integer number ) {
		if (number == 0)
			return Integer.toString(number);

		if (((number % 100) - (number % 10)) == 10)
			return Integer.toString(number).concat("th");

		if ((number % 10) == 1)
			return Integer.toString(number).concat("st");

		if ((number % 10) == 2)
			return Integer.toString(number).concat("nd");

		if ((number % 10) == 3)
			return Integer.toString(number).concat("rd");

		return Integer.toString(number).concat("th");

	}

	public static double evaluate(String expr) {
		Stack<Double> evalRPN = new Stack<>();

		if (!isValidExpression(expr)) return 0;

		String[] exprArray = expr.split(" ");

		for (String element : exprArray) {
			//if (element.matches("-?[\\d]+")) {
			if (!"+-*/".contains(element)) {
				evalRPN.push(Double.parseDouble(element));
			} else {
				Double elemA = evalRPN.pop();
				Double elemB = evalRPN.pop();
				Double result = evaluateExpression(element, elemB, elemA);
				evalRPN.push(result);
			}
		}

		return evalRPN.pop();
	}

	private static boolean isValidExpression(String expr) {
		return !expr.trim().isEmpty();
	}

	private static Double evaluateExpression(String operator, Double elemA, Double elemB) {
		switch (operator) {
			case "+":
				return elemA + elemB;
			case "-":
				return elemA - elemB;
			case "*":
				return elemA * elemB;
			default:
				return elemA / elemB;
		}
	}

	public static void main(String[] args) {
		//String result = maskify("5512103073210694");
		//String result = maskify("Skippy");
		//String result = maskify("A234-AAAA-BBBB-ABCD");
		//String result = maskify("AAAA-AAAA-1234-ABCD");
		/*
		String result = numberToOrdinal(0);
		System.out.println("\n" + result);
		result = numberToOrdinal(1);
		System.out.println("\n" + result);
		result = numberToOrdinal(2);
		System.out.println("\n" + result);
		result = numberToOrdinal(3);
		System.out.println("\n" + result);
		result = numberToOrdinal(12);
		System.out.println("\n" + result);
		result = numberToOrdinal(20);
		System.out.println("\n" + result);
		result = numberToOrdinal(31);
		System.out.println("\n" + result);
		result = numberToOrdinal(213);
		*/
		Double result = evaluate("10000 123 +");
		System.out.println("\n" + result);
	}
}
