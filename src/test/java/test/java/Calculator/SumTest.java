package test.java.Calculator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SumTest extends Calculator{

    @DataProvider(name = "Входящие данные для суммы")
    public Object[][] sumData() {
        return new Object[][]{
                {-70,-30,-100},
                {3,5,8}
        };
    }

    @Test(dataProvider = "sumData",enabled = false)
    public void testSum(int num1, int num2, int sum) {
        int result = findSum(num1,num2);
        assertEquals(result,sum);
    }
}
