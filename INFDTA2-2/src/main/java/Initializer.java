import com.sun.org.apache.xml.internal.security.Init;

/**
 * Created by Fatima on 23-06-16.
 */
public class Initializer {


    public static void main(String[] args) {

        Algorithms algorithms = new Algorithms(0,0,true,100,100);
        algorithms.createFirstPopulationSetting();
        algorithms.populateToMakeChronosome();

    }

}
