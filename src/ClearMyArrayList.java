 
import java.util.ArrayList;
 
public class ClearMyArrayList {
 
    public static void main(String a[]){
        ArrayList<String> arrl = new ArrayList<String>();
        //adding elements to the end
        arrl.add("test");
        arrl.add("revanth");
        arrl.add("changed");
        arrl.add("Random");
        System.out.println("Actual ArrayList:"+arrl);
        arrl.clear();
        System.out.println("After clear ArrayList:"+arrl);
    }
}