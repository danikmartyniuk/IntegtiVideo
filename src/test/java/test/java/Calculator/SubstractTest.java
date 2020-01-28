package test.java.Calculator;

import org.testng.annotations.Test;

import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class SubstractTest extends Calculator{

    @Test (description = "Простой тест для проверки логики вычитания", priority = 3)
    public void testSubstracting () {
        Scanner s = new Scanner(System.in);
        int firstNum = s.nextInt();
        int secNum = s.nextInt();
        int subs = firstNum - secNum;
        int methodSubs = substractNumbers(firstNum,secNum);
        assertEquals(methodSubs,subs,"Метод работает неправильно");
    }

    @Test (description = "Тест для проверки введённых чисел",priority = 1)
    public void testNumbers () {
        Scanner s = new Scanner(System.in);
        int firstNum = s.nextInt();
        int secNum = s.nextInt();
        if (firstNum > 2147483647 || secNum > 2147483647){
            System.out.println("Были введены слишком большие числа");
        } else {
            substractNumbers(firstNum,secNum);
        }
    }
}
