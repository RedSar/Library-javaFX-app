package ma.rsmi.bibleo.utils;

import java.util.ArrayList;
import java.util.List;

public class Helper {
   private final static String [] colors = {"#FFE5D9", "#FBFAF0", "#FFDDE4", "#e8e8e8", "#e2e0d4", "#d5e9dd"};
   private static List<Integer> indexes = new ArrayList<>();
   public static String getColor(){
       if(indexes.size() == colors.length)
           indexes.clear();

        int index = 0;
        do {
          index = (int) (Math.random()  * colors.length);
        } while (indexes.contains(index));
        indexes.add(index);

        return colors[index];
   }

    public static void main(String[] args) {
        System.out.println(getColor());
        System.out.println(getColor());
        System.out.println(getColor());
        System.out.println(getColor());
        System.out.println(getColor());
        System.out.println(getColor());
        System.out.println(getColor());
        System.out.println(getColor());
        System.out.println(getColor());
    }


}
