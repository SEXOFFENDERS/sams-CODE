package ca.ab.cbe.wahs.gjw;

public class CurrencyUtilities {
	
	public static double calculateTotalDollars(int quarters, int dimes, int nickels, int pennies)
	{
		return ( (quarters * 0.25) + (dimes * 0.1) + (nickels * 0.05) + (pennies * 0.01));
	}

}
