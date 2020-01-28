package test.java.Calculator;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.util.Scanner;

public class MultiplTest extends Calculator implements IRetryAnalyzer{

    int counter = 0;
    int retryLimit = 3;

    @Test (description = "Тестирование игры на умножение")
    public void testMultiplicationGame () {
        Scanner s = new Scanner(System.in);
        System.out.print("Введите число от одного до 9: ");
        int userInput = s.nextInt();
        int result = multiplyNumbers(userInput,9);
        int finalNumber = multiplyNumbers(result,12345679);
        String userNumber = String.valueOf(finalNumber);
        System.out.println("Ваше начальное число - " + userNumber.charAt(0));
    }

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (counter < retryLimit) {
                counter++;
                iTestResult.setStatus(ITestResult.FAILURE);
                System.out.println("Retrying once again");
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}
