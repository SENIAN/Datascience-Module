import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mohammed on 6/1/2016.
 */
/*
ALGORITHM – MAIN LOOP
The main loop of the genetic algorithm is explained in the slides of the course.
Moreover, a C# sample containing only the main loop of a generic genetic algorithm is available and can be used as a
starting point. If you use this code, you will have to program by yourself some specific functions to make it work. The
functions are:
 Func<Ind> createIndividual ==> input is nothing, output is a new individual;
 Func<Ind,double> computeFitness ==> input is one individual, output is its fitness;
 Func<Ind[],double[],Func<Tuple<Ind,Ind>>> selectTwoParents ==> input is an array of individuals (population)
and an array of corresponding fitnesses, output is a function which (without any input) returns a tuple with two
individuals (parents);
 Func<Tuple<Ind, Ind>, Tuple<Ind, Ind>> crossover ==> input is a tuple with two individuals (parents), output is a
tuple with two individuals (offspring/children);
 Func<Ind, double, Ind> mutation ==> input is one individual and mutation rate, output is the mutated individual
where Ind is the data structure which encodes the individual. You need to define concretely this data structure by
yourself


 */
public class GeneticAlgorithm <Individual> {


    List<Individual> newPopulation;
    Individual individual;
    double CrossoverRate;
    double MutationRate;
    boolean Elitism;
    int populationSize;
    int iterations;

    // repeat the process for the second individual

    public List<Individual> runAlgorithm() {
        newPopulation =  new ArrayList<Individual>();
        return newPopulation;
    }

    public Individual createInitialIndividual() {
            Random random = new Random();
        return individual;
    }

    public Individual computeFitness() {

        // compute fitness of each individual in the population
        // apply elitism

        return individual;
    }


    public Individual InitializeCurrentIndividuals() {
        // initialize the selection function given the current individuals and their fitnesses

        // create the individuals of the next generation

        return individual;
    }

    public Individual selectTwoParents() {
        // select two parents
        // do a crossover between the selected parents to generate two children (with a certain probability,
        // crossover does not happen and the two parents are kept unchanged)

        // save the two children in the next population (after mutation)

        return individual;
    }


}
