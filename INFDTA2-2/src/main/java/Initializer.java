import com.sun.org.apache.xml.internal.security.Init;

import java.util.List;

/**
 * Created by Fatima on 23-06-16.
 */
public class Initializer {


    public static void main(String[] args) {

        Algorithms algorithms = new Algorithms(0, 0, true, 10, 1000000);
        int startingPoint = -1;
        for (int i = 0; i <= algorithms.numOfIterations; i++) {
            if (algorithms.useElitism) {

                System.out.println("<----------------------Iteration Count:[" + i +"]------------------>");

                System.out.println("<----------------------Creating First Population----------------->");
                System.out.println("<----------------------Population Size:[" + algorithms.popSize + "]------------------>");
                List<Individual<Integer>> initialPoint = algorithms.createFirstPopulationSetting();
                System.out.println("<----------------------Average of Population------------------->");
                algorithms.getAverageFitness(initialPoint);
                System.out.println("<----------------------PopulateChronosomes With Elitism------------------->");
                List<Individual<String>> pointWithElitism = algorithms.populateToMakeChronosomeWithElitism(initialPoint);

                System.out.println("<----------------------Average of Population Based on Binary After Elitism------------------->");
                algorithms.getAverageBasedOnBinary(pointWithElitism);
                startingPoint = 1;
            } else {
                System.out.println("<----------------------Creating First Population------------------->");
                List<Individual<Integer>> initialPoint = algorithms.createFirstPopulationSetting();
                System.out.println("<----------------------Population Size   " + algorithms.popSize + " ------------------>");
                System.out.println("<----------------------Average of Population------------------->");
                algorithms.getAverageFitness(initialPoint);
                System.out.println("<----------------------PopulateChronosomes Without Elitism------------------->");
                List<Individual<String>> pointWithoutElitism = algorithms.populateToMakeChronosome();
                System.out.println("<----------------------Average of Population Based on Binary Without Elitism------------------->");
                algorithms.getAverageBasedOnBinary(pointWithoutElitism);
                startingPoint = 0;
            }
        }

    }
}
