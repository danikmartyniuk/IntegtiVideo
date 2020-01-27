package test.java.Calculator;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Calculator {

    public int findSum (int num1, int num2) {
        return num1+num2;
    }

    public int substractNumbers (int num1, int num2) {
        return num1-num2;
    }

    public int multiplyNumbers (int num1, int num2) {
        return num1*num2;
    }

    public int divideNumbers (int num1, int num2) {
        return num1/num2;
    }

    @BeforeMethod
    public void welcome () {
        System.out.println("Привет, рады тебя видеть!");
    }

    @AfterMethod
    public void goodbye () {
        System.out.println("Раз такой умный, считай сам в уме");
    }

}
