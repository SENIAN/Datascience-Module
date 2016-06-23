import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Fatima on 23-06-16.
 */

@Data
public class Algorithms {

    private double mutationRate;
    private double crossoverRate;
    private boolean useElitism;
    private int numOfIterations;
    private int popSize;

    public Algorithms(double mutationRate, double crossoverRate, boolean useElitism, int numOfIterations, int popSize) {
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.useElitism = useElitism;
        this.numOfIterations = numOfIterations;
        this.popSize = popSize;
    }

    public double computeFitness(double calcVal) {
        double c = (-Math.pow(calcVal, 2)) + (7 * calcVal);
        return  c;

    }

    public List<Individual<Integer>> createFirstPopulationSetting() {
        List<Individual<Integer>> initialPopulation = new ArrayList<>();
        int maximumIntegerValue = 32;
        Random r = new Random();
        for (int i = 0; i < popSize; i++) {
            int s = r.nextInt(maximumIntegerValue);
            initialPopulation.add(new Individual<>(s));
        }
        rouletteSelection(initialPopulation);
        return initialPopulation;
    }

    public List<Individual<String>> populateToMakeChronosome() {
        List<Individual<Integer>> currPopulation = createFirstPopulationSetting();
        List<Individual<String>>  allChronosomes = new ArrayList<>();
        currPopulation.forEach(n -> {
            String ChronosomeValue = String.format("%5s", Integer.toBinaryString(n.getIndividual())).replace(' ', '0');
            allChronosomes.add(new Individual<String>(ChronosomeValue));

        });
        getAllChronosomes(allChronosomes);
        return allChronosomes;
    }

    private List<Individual<Double>> rouletteSelection(List<Individual<Integer>> population) {
        double fitness = 0;
        List<Individual<Double>> individual = new ArrayList<>();
        for(Individual<Integer> peep : population) {
            fitness = computeFitness(peep.getIndividual());
            individual.add(new Individual<Double>(fitness));
        }
        getAllChronosomes(individual);
        return individual;

    }

    public void getAllChronosomes(List chronosomes) {
            chronosomes.forEach(n -> System.out.println(n));
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