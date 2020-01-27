package test.java.Calculator;

import org.testng.annotations.Test;

import java.util.Scanner;

public class DivideTest extends Calculator{

    @Test (threadPoolSize = 5, invocationCount = 4)
    public void testDivision () {
        Scanner s = new Scanner(System.in);
        int userNumber = s.nextInt();
        for (int i = -20; i < 20; i++){
            System.out.println(divideNumbers(userNumber,i));
        }
    }

}
