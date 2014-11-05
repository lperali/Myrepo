 
import java.util.ArrayList;
import java.util.List;
 
public class MyListCheck {
 
    public static void main(String a[]){
        ArrayList<String> arrl = new ArrayList<String>();
        arrl.add("First");
        arrl.add("Second");
        arrl.add("Third");
        arrl.add("Random");
        List<String> list = new ArrayList<String>();
        list.add("Second");
        list.add("Random");
        System.out.println("Does ArrayList contains all list elements?: "
                    +arrl.containsAll(list));
        /*list.add("one");
        System.out.println("Does ArrayList contains all list elements?: "
                    +arrl.containsAll(list));*/
    }
}