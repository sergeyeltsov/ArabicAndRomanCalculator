package jr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ArabicAndRomanCalculator {

    public static void main(String[] args) throws IOException {

        System.out.println("Enter an example by pattern (1 + 1):");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String errorMessage = "Enter the correct numbers";

        String example = br.readLine();

        try {
            String[] arg;
            arg = example.split(" ");

            if (isNumeric(arg[0])) {
                if (isNumeric(arg[2])) {
                    System.out.println(calc(Integer.parseInt(arg[0]), Integer.parseInt(arg[2]), arg[1]));
                } else {
                    System.out.println(errorMessage);
                }
            } else if (isNumeric(arg[2])) {
                System.out.println(errorMessage);
            } else {
                System.out.println(calc(arg[0], arg[2], arg[1]));
            }
        } catch (IllegalArgumentException e1) {
            System.out.println(errorMessage);

        } catch (ArrayIndexOutOfBoundsException e2) {
            System.out.println("Enter an example correctly");

        } catch (ArithmeticException e3) {
            System.out.println("It is impossible to divide by zero");
        }

    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

            List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

            int i = 0;

            while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
                RomanNumeral symbol = romanNumerals.get(i);
                if (romanNumeral.startsWith(symbol.name())) {
                    result += symbol.getValue();
                    romanNumeral = romanNumeral.substring(symbol.name().length());
                } else {
                    i++;
                }
            }

            if (romanNumeral.length() > 0) {
                throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
            }

            return result;
        }


        public static String arabicToRoman ( int number){

            if ((number <= 0) || (number > 4000)) {

                throw new IllegalArgumentException(number + " is not in range (0...4000)");
            }

            List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

            int i = 0;
            StringBuilder sb = new StringBuilder();

            while ((number > 0) && (i < romanNumerals.size())) {
                RomanNumeral currentSymbol = romanNumerals.get(i);
                if (currentSymbol.getValue() <= number) {
                    sb.append(currentSymbol.name());
                    number -= currentSymbol.getValue();
                } else {
                    i++;
                }
            }
            return sb.toString();
        }

        public static int calc ( int num1, int num2, String operation){
            int result = 0;
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            return result;
        }

        public static String calc (String number1, String number2, String operation){
            int result = 0;
            int num1 = romanToArabic(number1);
            int num2 = romanToArabic(number2);
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    if (result <= 0) {
                        System.out.println(result + " is not in range (0...4000");
                        break;
                    }
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            return arabicToRoman(result);
        }
    }




