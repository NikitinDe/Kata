import java.io.IOException;
import java.util.Scanner;

public class Colculator extends Converter {
    
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Main result = new Main();
        System.out.println("Введите выражение:");
        String exp = input.nextLine();
        String answer = result.calc(exp);
        System.out.println("Ответ:\n" + answer);

    }

}
class Main{
    public static String calc(String input)  throws Exception {
        boolean romanOrArab = false;
        String exception = "throws Exception";
        Main romanExamination = new Main();
        Main arabToRoman = new Main();
        int result = 0;
        String[] inputSplit = input.split(" ");
        if (inputSplit.length != 3){
            throw new IOException(exception);
        }
        Integer a = 0;
        Integer b = 0;
        try {
            a = Integer.valueOf(inputSplit[0]);
            b = Integer.valueOf(inputSplit[2]);
        } catch (NumberFormatException e) {
            try {
                a = romanExamination.romanToArab(inputSplit[0]);
                b = romanExamination.romanToArab(inputSplit[2]);
                romanOrArab = true;
            } catch (NumberFormatException ex) {
                throw new IOException(exception) ;
            }
        }
        if ((a < 1) || (a > 10) || (b < 1) || (b > 10)){
            throw new IOException(exception);
        }
        String sign = inputSplit[1];
        switch (sign) {
            case "+" -> result = a + b;
            case "-" -> result = a - b;
            case "*" -> result = a * b;
            case "/" -> result = a / b;
            default -> {
                throw  new IOException(exception);
            }
        }
        String output;
        if (romanOrArab){
            if(result < 1){
                throw new IOException(exception);
            } else {
                output = arabToRoman.arabToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }
        return output;
    }
    Integer romanToArab(String romanInput){
        int result = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }
        return result;
    }
    String arabToRome(int arabInput){
        String result = "";
        int value = 0;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++){
            value = arabInput / arab[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
        return result;
    }
}

