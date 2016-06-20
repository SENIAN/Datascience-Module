import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Mohammed on 6/20/2016.
 */
public class Utils {

    List tempList;

    public static Utils instance = null;
    public static Utils getInstance() {
        if(instance==null) {
            instance = new Utils();
        }
        return instance;
    }


    public int encoder(String enc) {
        return Integer.parseInt(enc, 2);
    }


    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        st.sorted( Map.Entry.comparingByValue() )
                .forEachOrdered( e -> result.put(e.getKey(), e.getValue()) );

        return result;
    }
}
