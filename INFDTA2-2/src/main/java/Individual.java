import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Data;
import sun.misc.BASE64Encoder;

/**
 * Created by Mohammed on 6/1/2016.
 */

/*

INPUTS
The user-specified parameters of the program should be:
 Crossover rate (value between 0 and 1 indicating the probability of actually carrying out the crossover between
parents)
 Mutation rate (value between 0 and 1 indicating the probability of carrying out a mutation)
 Elitism (Boolean indicating if elitism is used or not in the algorithm)
 Population size (integer indicating the amount of individuals in the population)
 Number of iterations (integer indicating after how many iterations/generations the algorithm will stop


Beginning > Initial population > Evaluation > Selection  > Crossover > Mutation > Evolved Population > Convergence Check >
End
 */

@Data
public class Individual {


    public String InitChronosomeValue;
    private static Individual instance = null;


    public static Individual getInstance() {
        if(instance==null) {
            instance = new Individual();

        }
        return instance;
    }

}