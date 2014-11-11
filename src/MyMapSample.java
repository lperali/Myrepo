import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
 
public class MyMapSample {
    public static void main(String[] args) {
        Map studentGrades = new HashMap<String, String>();
        studentGrades.put("Leela", "A+");
        studentGrades.put("Samba", "A");
        studentGrades.put("Rama", "A-");
        studentGrades.put("chandrika", "B+");
        
        /**
         *  Using Iterator and while loop.
         * In this method you can rmeove the entries
         * while iterating through it.
         */
       System.out.println("Using Iterator");
        Iterator<Map.Entry<String, String>> iterator = studentGrades.entrySet().iterator() ;
        while(iterator.hasNext()){
            Map.Entry<String, String> studentEntry = iterator.next();
            System.out.println(studentEntry.getKey() +" :: "+ studentEntry.getValue());
            //You can remove elements while iterating.
            iterator.remove();
        }
        if(studentGrades.isEmpty()){
        	System.out.println("Data removed from Map now it's empty");
        }
 
    }
}
