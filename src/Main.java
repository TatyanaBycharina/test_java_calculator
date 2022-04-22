import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        System.out.println("Калькулятор умеет выполнять операции сложения, вычитания,\n" +
                "умножения и деления с двумя числами: a + b, a - b, a * b, a / b.");

        Scanner scan = new Scanner(System.in);

        for(;;) {
            String input = scan.nextLine();

            String[] inputSplit = input.split("\\s+");

            if(inputSplit.length < 3 || inputSplit.length > 3) {
                throw new IllegalArgumentException("Неверный формат записи: запись должна \n" +
                        "соответствовать формату A + B с пробелами");
            }

            if(stringIsNum(inputSplit[0]) && stringIsNum(inputSplit[2])) {
                System.out.println(calculate(Integer.parseInt(inputSplit[0]), inputSplit[1], Integer.parseInt(inputSplit[2])));
            } else if (!stringIsNum(inputSplit[0]) && !stringIsNum(inputSplit[2])) {
                int firstNum = romanToNum(inputSplit[0]);
                int secondNum = romanToNum(inputSplit[2]);
                int result = calculate(firstNum, inputSplit[1], secondNum);
                if (result < 1) {
                    throw new IllegalArgumentException("В римской системе нет отрицательных чисел");
                }
                System.out.println(numToRoman(result));
            } else {
                throw new IllegalArgumentException("Используются одновременно разные системы счисления");
            }
        }

    }

    static int calculate(int firstNum, String sym, int secondNum) {
        if ((firstNum < 1 || firstNum > 10) || (secondNum < 1 || secondNum > 10)) {
            throw new IllegalArgumentException("Числа должны находиться в диапазоне от 1 до 10 включительно");
        }
        int result = 0;
        switch (sym) {
            case "+": result = firstNum + secondNum; break;
            case "-": result = firstNum - secondNum; break;
            case "*": result = firstNum * secondNum; break;
            case "/": result = firstNum / secondNum; break;
            default: throw new IllegalArgumentException("Несоответствующая операция");
        }
        return result;
    }

    //Преобразуем римские числа в арабские
    static int romanToNum(String a) {
        int stringLength = a.length();
        int result = 0;
        for (int i = 0; i < stringLength; i++) {
            char romanChar = a.charAt(i);
            switch (romanChar) {
                case 'I': if ((i < stringLength - 1) && (a.charAt(i + 1) == 'V' || a.charAt(i + 1) == 'X')) {
                    result -= 1;
                } else { result +=1; }
                    break;
                case 'V': result += 5; break;
                case 'X': if ((i < stringLength - 1) && (a.charAt(i + 1) == 'L' || a.charAt(i + 1) == 'C')) {
                    result -= 10;
                } else { result +=10; }
            }
        }
        return result;
    }
//Проверяем, является ли строка числом
    static boolean stringIsNum(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Преобразуем арабские цифры в римские
    static String numToRoman(int a) {
        String result = "";
        while (a >= 100) {
            result += "C";
            a -= 100;
        }
        while (a >= 90) {
            result += "XC";
             a -= 90;
        }
        while (a >= 50) {
            result += "L";
            a -= 50;
        }
        while (a >= 40) {
            result +="XL";
            a -= 40;
        }
        while (a >= 10) {
            result += "X";
            a -= 10;
        }
        while (a >= 9) {
            result += "IX";
            a -= 9;
        }
        while (a >= 5) {
            result += "V";
            a -= 5;
        }
        while (a >= 4) {
            result += "IV";
            a -= 4;
        }
        while (a >= 1) {
            result += "I";
            a -= 1;
        }
        return result;
    }
}
