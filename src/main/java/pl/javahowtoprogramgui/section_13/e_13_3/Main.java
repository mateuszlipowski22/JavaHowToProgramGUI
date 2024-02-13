package pl.javahowtoprogramgui.section_13.e_13_3;

import java.util.Arrays;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        String someString = "PAR/MV0010/2024/02/12";
        if(someString.chars().filter(ch -> ch == '/').count()==4){
            String[] splitString = someString.split("(?=[/]{1}[0-9]{2}$)");
            if(splitString.length==2){
                someString=String.format("%s/%s%s",splitString[0], Calendar.getInstance().get(Calendar.DAY_OF_MONTH),splitString[1]);
                System.out.println("modyfikacja");
            }
        };
        System.out.println(someString);
    }
}
