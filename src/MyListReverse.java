 
import java.util.ArrayList;
import java.util.Collections;
 
public class MyListReverse {
 
    public static void main(String a[]){
        ArrayList<String> list = new ArrayList<String>();
        list.add("Java");
        list.add("Cric");
        list.add("Play");
        list.add("Watch");
        list.add("Glass");
        list.add("Movie");
        list.add("football");
        Collections.reverse(list);
        //System.out.println("Results after reverse operation:");
        for(String str: list){
            System.out.println(str);
        }
        //swap function
        Collections.swap(list, 2, 5);
        System.out.println("Results after swap operation:");
        for(String str: list){
            System.out.println(str);
        }
    }
}