// Emulated extension enum - Page 166-167
package com.hm.effective_java.chapter_six.item34;

import java.util.Arrays;
import java.util.Collection;

public enum ExtendedOperation implements Operation {
	EXP("^") {
		public double apply(double x, double y) {
			return Math.pow(x, y);
		}
	},
	REMAINDER("%") {
		public double apply(double x, double y) {
			return x % y;
		}
	};

	private final String symbol;

	ExtendedOperation(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}

	// Test class to exercise all operations in "extension enum" - Page 167
	public static void main(String[] args) {
		double x = Double.parseDouble("8");
		double y = Double.parseDouble("4");
		test(ExtendedOperation.class, x, y);

		System.out.println(); // Print a blank line between tests
		test2(Arrays.asList(BasicOperation.values()), x, y);
	}

	// test parameter is a bounded type token (Item 29)
	private static <T extends Enum<T> & Operation> void test(Class<T> opSet, double x, double y) {
		for (Operation op : opSet.getEnumConstants())
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
	}

	// test parameter is a bounded wildcard type (Item 28)
	private static void test2(Collection<? extends Operation> opSet, double x, double y) {
		for (Operation op : opSet)
			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
	}
}
