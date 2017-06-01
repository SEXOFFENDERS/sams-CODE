public class BankAccountTest extends junit.framework.TestCase
{

    BankAccount chequing1;
    BankAccount chequing2;
    BankAccount chequing3;
    
    protected void setUp()
    {
    	System.out.println("setup");
        chequing1 = new BankAccount("chequing1");
        chequing2 = new BankAccount("chequing2", 100);
        chequing3 = new BankAccount("chequing3", 100, 2.00, 5.0);        
    }
        
    public void testAccessors()
    {
		assertEquals("chequing2",  chequing2.getAccountNumber());
		assertEquals(100.0,  chequing2.getBalance(), 0);
		assertEquals(5.0,  chequing3.getAnnualInterestRate(), 0);
		assertEquals(2.0,  chequing3.getWithdrawalFee(), 0);
    }
    
    public void testMutators()
    {
	    //test for setters
		chequing1.setWithdrawalFee(3.0);
		chequing1.setAnnualInterestRate(4.0);
		assertEquals(3.0,  chequing1.getWithdrawalFee(), 0);
		assertEquals(4.0,  chequing1.getAnnualInterestRate(), 0);
    }
    
    public void testDeposits()
    {                	
		//test various deposits and withdrawals
		chequing1.setWithdrawalFee(3.0);
		
		chequing1.deposit(500);
		assertEquals(500.0,  chequing1.getBalance(), 0);
			
		chequing1.withdraw(200);
		assertEquals(297.0,  chequing1.getBalance(), 0);			
		assertEquals(false,  chequing1.isOverDrawn());
					
		chequing1.withdraw(300);
		assertEquals(-6.0,  chequing1.getBalance(), 0);
		assertEquals(true,  chequing1.isOverDrawn());
		
    	chequing2.deposit(0.42);
    	assertEquals(100.42,  chequing2.getBalance(), 0.00);
    	chequing2.deposit(0.001);
    	assertEquals(100.421,  chequing2.getBalance(), 0.001);
    }
    
    public void testToString() {
   
    	assertEquals("BankAccount chequing1: $0.00",  chequing1.toString());
    	assertEquals("BankAccount chequing2: $100.00",  chequing2.toString());
    	
    	chequing2.deposit(0.42);
    	assertEquals("BankAccount chequing2: $100.42",  chequing2.toString());

    	chequing2.deposit(0.001);
    	assertEquals("BankAccount chequing2: $100.42",  chequing2.toString());

    	BankAccount chequing4 = new BankAccount("chequing4", -100);
    	assertEquals("BankAccount chequing4: ($100.00)",  chequing4.toString());
    }
   
}

