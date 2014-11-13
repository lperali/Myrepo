
import java.util.*;

public class FindDups2 {
    public static void main(String[] args) {
        Set<String> uniques = new HashSet<String>();
        Set<String> dups    = new HashSet<String>();

        for (String a : args)
            if (!uniques.add(a))
                dups.add(a);

        // Destructive set-difference usage
        uniques.removeAll(dups);

        //System.out.println("Unique words:    " + uniques);
        System.out.println("Duplicate words: " + dups);
        System.out.println("Duplicate words: 222222" + dups);
        
    }
}