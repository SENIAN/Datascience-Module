import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mohammed on 5/31/2016.
 */


public class Settings {

    List<String> individualList = new ArrayList<>();
    public static Settings instance = null;

            public static Settings getInstance() {
                if(instance==null) {
                    instance =  new Settings();
                }
                return instance;
            }

    public void createFirstPopulationSetting(int initialPopulationValue) {
        int maximumIntegerValue = 32;
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        Random r = new Random();
        for (int i = 0; i < initialPopulationValue; i++) {
            int s = r.nextInt(maximumIntegerValue);
            geneticAlgorithm.createInitialIndividual(s);
        }
    }


}
