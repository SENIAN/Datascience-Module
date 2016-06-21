import com.sun.org.apache.xml.internal.security.Init;
import lombok.Data;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Mohammed on 6/1/2016.
 */

@Data
public class GeneticAlgorithm {

    List<Individual> initPopulation = new ArrayList<>();
    List<Individual> newPopulationFitness = new ArrayList<>();
    List<Double> FittestPopulation = new ArrayList<>();
    List<Individual> rankSelectionPop = new ArrayList<>();
    double CrossoverRate;
    double MutationRate;
    boolean Elitism;
    int populationSize;
    int iterations;
    Individual individual;
    int initial;

    // repeat the process for the second individual



    //region createIndividual and computefitness / elitism.
    // Creating some individuals binary Strings.
    public void createInitialIndividual(int initial) {
        String ChronosomeValue = String.format("%5s", Integer.toBinaryString(initial)).replace(' ', '0');
        Individual individual = new Individual(ChronosomeValue);
        computeFitness(individual);
    }

    // Compute te fitness
    public List<Individual> computeFitness(Individual individual) {
        double fitness = 0;
        initial = 0;
        int enc = Utils.getInstance().encoder(individual.getInitChronosomeValue());
        fitness = (-Math.pow(enc, 2.00)) + (7 * enc);
        initial++;
        individual.setId(initial);
        individual.setNewChronosomeFittest(fitness);
        newPopulationFitness.add(individual);
        return applyElitism(newPopulationFitness);
    }

    //Apply the Elitism based on the fitness
    public List<Individual> applyElitism(List<Individual> nextPopulation) {
        Comparator<Double> comparator = (Double a, Double b) -> {
            return b.compareTo(a);

        };
        Utils.getInstance().doubleEncodeBeforeSelection(nextPopulation);
        return nextPopulation;
    }

    // endregion creations

    // region Selection
    public List<Individual> computeAfterFitness(List<Individual> individuals) {
            initial = 0;
            double fitness = 0;
        for (int i = 0; i < individuals.size(); i++) {
            int enc = Utils.getInstance().encoder(individual.getInitChronosomeValue());
            fitness = (-Math.pow(enc, 2.00)) + (7 * enc);
            individuals.get(i).setId(initial);
            individuals.get(i).setNewChronosomeFittest(fitness);
            rankSelectionPop.add(individual);
            initial++;
        }
        return rankSelectionPop;
    }

    public List rankSelectionAfterFitness(List<Individual> list, int selectionThreshold) {
        List<Individual> tournementCandidates = new ArrayList<>();
        for (Individual s : newPopulationFitness) {
            list.addAll(computeAfterFitness(s));
        }

        for(int i=0; i < list.size(); i++) {
            if(list.get(i).getNewChronosomeFittest() > selectionThreshold) {
                tournementCandidates.addAll(list.get(i));
            }
        }
        return tournementSelection(Utils.getInstance().sortation(tournementCandidates));
    }


    public void tournementSelection(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    //endregion Selection
    public void Crossover() {

    }

    public void Mutation() {

    }

    public void Accepting() {

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
