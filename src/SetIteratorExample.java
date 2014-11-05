import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class SetIteratorExample {
   /**
    * How to iterate a Set in Java example
    */
   public static void main(String[] args) {
    //Insted of HashSet, you can use
    //TreeSet or LinkedHashSet as well
    Set<String> set = new HashSet<String>();
    set.add("Element 1");
    set.add("Element 2");
    set.add("Element 3");
   
    System.out.println("For loop"); //since Java 5
    for (String s : set) {
        System.out.println(s);
    }
   
    System.out.println("Iterator");
    Iterator<String> it = set.iterator();
    while (it.hasNext()) {
        String s = it.next();
        System.out.println(s);
    }
   } 
}