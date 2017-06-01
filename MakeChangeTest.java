//v3 2016.10.15

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MakeChangeTest {

	int testCount = 0;

    @Test
    public void test() {
        //some general values
        assertEquals(true,  testConsoleApplication("0.41\n", ".*toonies:0; loonies:0; quarters:1; dimes:1; nickels:1\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.44\n", ".*toonies:0; loonies:0; quarters:1; dimes:2; nickels:0\r\n$")  );
        assertEquals(true,  testConsoleApplication("1.31\n", ".*toonies:0; loonies:1; quarters:1; dimes:0; nickels:1\r\n$")  );
        assertEquals(true,  testConsoleApplication("2.31\n", ".*toonies:1; loonies:0; quarters:1; dimes:0; nickels:1\r\n$")  );
        assertEquals(true,  testConsoleApplication("3.31\n", ".*toonies:1; loonies:1; quarters:1; dimes:0; nickels:1\r\n$")  );
        assertEquals(true,  testConsoleApplication("19.15\n", ".*toonies:9; loonies:1; quarters:0; dimes:1; nickels:1\r\n$")  );

        //test whether rounding is always accurate
        assertEquals(true,  testConsoleApplication("0.00\n", ".*toonies:0; loonies:0; quarters:0; dimes:0; nickels:0\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.01\n", ".*toonies:0; loonies:0; quarters:0; dimes:0; nickels:0\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.02\n", ".*toonies:0; loonies:0; quarters:0; dimes:0; nickels:0\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.03\n", ".*toonies:0; loonies:0; quarters:0; dimes:0; nickels:1\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.04\n", ".*toonies:0; loonies:0; quarters:0; dimes:0; nickels:1\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.05\n", ".*toonies:0; loonies:0; quarters:0; dimes:0; nickels:1\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.06\n", ".*toonies:0; loonies:0; quarters:0; dimes:0; nickels:1\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.07\n", ".*toonies:0; loonies:0; quarters:0; dimes:0; nickels:1\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.08\n", ".*toonies:0; loonies:0; quarters:0; dimes:1; nickels:0\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.09\n", ".*toonies:0; loonies:0; quarters:0; dimes:1; nickels:0\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.10\n", ".*toonies:0; loonies:0; quarters:0; dimes:1; nickels:0\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.97\n", ".*toonies:0; loonies:0; quarters:3; dimes:2; nickels:0\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.98\n", ".*toonies:0; loonies:1; quarters:0; dimes:0; nickels:0\r\n$")  );
        assertEquals(true,  testConsoleApplication("0.99\n", ".*toonies:0; loonies:1; quarters:0; dimes:0; nickels:0\r\n$")  );

        //unusual or invalid cases
        assertEquals(true,  testConsoleApplication("0\n", ".*toonies:0; loonies:0; quarters:0; dimes:0; nickels:0\r\n$")  );
        assertEquals(true,  testConsoleApplication("lots\n", ".*INVALID\r\n$")  );
        assertEquals(true,  testConsoleApplication("\n", ".*INVALID\r\n$")  );
    
    }
    
    private boolean testConsoleApplication(String input, String expectedOutput) {

        ByteArrayOutputStream myOut = new ByteArrayOutputStream();

        InputStream stdin = null;
        PrintStream stdout = null;
        String standardOutput = "";

        testCount++;
        
        try {
            stdin = System.in;
            stdout = System.out;

            System.setIn(new ByteArrayInputStream(input.getBytes()));
            System.setOut(new PrintStream(myOut));

           MakeChange.main(null);           

           standardOutput = myOut.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
        
        boolean result = standardOutput.matches(expectedOutput);

        System.out.println("--------------------------------------------------------------------------------");        
        System.out.println("test " + testCount);
        System.out.println("input:" + input.replace("\n", ""));
        System.out.println("expected: " + expectedOutput.replace("\r\n", "\\r\\n"));
        System.out.println("returned: " + standardOutput.replace("\r\n", "\\r\\n"));
        System.out.println(result ? "PASS" : "FAIL");
        
        return result;
    }
    

}
