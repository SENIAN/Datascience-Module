import lombok.Data;

import java.util.*;

/**
 * Created by Mohammed on 23-06-16.
 */

@Data
public class Algorithms {

    public double mutationRate;
    public double crossoverRate;
    public boolean useElitism;
    public int numOfIterations;
    public int popSize;
    List<Individual<Integer>> initialPopulation = new ArrayList<>();

    public Algorithms(double mutationRate, double crossoverRate, boolean useElitism, int numOfIterations, int popSize) {
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.useElitism = useElitism;
        this.numOfIterations = numOfIterations;
        this.popSize = popSize;

    }

    public double computeFitness(double calcVal) {
        double c = (-Math.pow(calcVal, 2)) + (7 * calcVal);
        // if fitness smaller than 0 then return 0 else return actual value
        return (c < 0) ? 0 : c;

    }

    public List<Individual<Integer>> createFirstPopulationSetting() {
        int maximumIntegerValue = 31;
        Random r = new Random();
        for (int i = 0; i < popSize; i++) {
            int s = r.nextInt(maximumIntegerValue);
            initialPopulation.add(new Individual<>(s));
        }
        return initialPopulation;
    }

    public List<Individual<String>> populateToMakeChronosome() {
        List<Individual<Integer>> currPopulation = createFirstPopulationSetting();
        List<Individual<String>> allChronosomes = new ArrayList<>();
        currPopulation.forEach(n -> {
            String ChronosomeValue = String.format("%5s", Integer.toBinaryString(n.getIndividual())).replace(' ', '0');
            allChronosomes.add(new Individual<String>(ChronosomeValue));
        });
        getAllChronosomes(allChronosomes);
        return allChronosomes;
    }

    public List<Individual<String>> populateToMakeChronosomeWithElitism(List<Individual<Integer>> currPopulationElitism) {
        List<Individual<String>> allChronosomesElitism = new ArrayList<>();
        currPopulationElitism.forEach(n -> {
            String ChronosomeValue = String.format("%5s", Integer.toBinaryString(n.getIndividual())).replace(' ', '0');
            allChronosomesElitism.add(new Individual<String>(ChronosomeValue));
        });
        getFittestChronosomes(allChronosomesElitism);
        return allChronosomesElitism;
    }

    public Map<Individual<String>, String> getFittestChronosomes(List<Individual<String>> chronosomes) {
        double fitness = 0;
        Map<Individual<String>, String> fittestChronosomes = new HashMap<>();
        for (Individual<String> chronosome : chronosomes) {
            Long decimal = Long.parseLong(chronosome.getIndividual(), 2);
            fitness = computeFitness((double) decimal.intValue());
            if (fitness > 0) {
                fittestChronosomes.put(chronosome, Double.toString(fitness));
                //       System.out.println(chronosome + "Individual belongs to the fittest added for next rounds");
            } else {
                //       System.out.println(chronosome + " Individual isn't the fitt enough they fall out");
            }
        }
        String fittest = getFittestFromFittestList(fittestChronosomes);
        System.out.println("<---------------------------Best Individual------------------------->");
        System.out.println(fittest);
        return fittestChronosomes;
    }

    public String getFittestFromFittestList(Map<Individual<String>, String> fittestOnes) {

        int fitness = 0;
        Map<Individual<Integer>, Integer> fittestOne = new HashMap<>();
        List<Individual<Integer>> newParent = new ArrayList<>();

        for (Map.Entry<Individual<String>, String> entry : fittestOnes.entrySet()) {
            Long decimal = Long.parseLong(entry.getKey().getIndividual(), 2);
            fitness = (int) computeFitness(decimal.intValue());
            fittestOne.put(new Individual<>(decimal.intValue()), fitness);
        }

        for (Map.Entry<Individual<Integer>, Integer> entry : fittestOne.entrySet()) {
            newParent.add(entry.getKey());
        }
        getTwoParents(newParent);

        String Individual = String.valueOf(fittestOne.entrySet().stream().max((p1, p2) -> p1.getValue() > p2.getValue() ? 1 : -1).get().getKey());
        String fitnessNumber = String.valueOf(fittestOne.entrySet().stream().max((p1, p2) -> p1.getValue() > p2.getValue() ? 1 : -1).get().getValue());
        return "Individual:  " + Individual + "   Fitness: " + fitnessNumber;
        }

    public Individual<Integer> russianRoulette(List<Individual<Integer>> population) {
        int fitness = 0;
        int newFitness = 0;
        for (Individual<Integer> first : population) {
            double thisValue = computeFitness(first.getIndividual());
            fitness = (int) thisValue;
        }

        int r = new Random().nextInt(fitness);

        for (Individual<Integer> sec : population) {
            newFitness += computeFitness((sec.getIndividual()));
            if (newFitness >= r) {
                return sec;
            }
        }
        return null;
    }


    public void getTwoParents(List<Individual<Integer>> chronosomes) {
        Individual<Integer> firstParent = russianRoulette(chronosomes);
        Individual<Integer> secondParrent = russianRoulette(chronosomes);
        crossoverAndMutation(firstParent, secondParrent);

    }

    public Individual<Integer> mutation(Individual<Integer> mutate) {
        String ChronosomeValue = String.format("%5s", Integer.toBinaryString(mutate.getIndividual())).replace(' ', '0');
        int mutationFactor = new Random().nextInt(ChronosomeValue.length());
        char mutationReference = ChronosomeValue.charAt(mutationFactor);
        char mutationArray[];
        if (mutationReference == '0') {
            mutationArray = ChronosomeValue.toCharArray();
            mutationArray[mutationFactor] = '1';
            return new Individual<Integer>(Integer.parseInt(String.valueOf(mutationArray), 2));
        }
        mutationArray = ChronosomeValue.toCharArray();
        mutationArray[mutationFactor] = '0';
        return new Individual<Integer>(Integer.parseInt(String.valueOf(mutationArray), 2));

    }


    public double getAverageFitness(List<Individual<Integer>> population) {
        double returnResult = 0;
        for (Individual<Integer> value : population) {
            returnResult = (float) computeFitness(value.getIndividual());
        }
        returnResult = returnResult / population.size();
        System.out.println(returnResult);
        return returnResult;
    }

    public Individual<Integer> createNewChild(Individual<Integer> parentOne, Individual<Integer> parentTwo) {
        //splitting the dna

        System.out.println("<---------------------------Spliting DNA------------------------->");
        String MatchDna1 = String.format("%5s", Integer.toBinaryString(parentOne.getIndividual())).replace(' ', '0');
        String MatchDna2 = String.format("%5s", Integer.toBinaryString(parentTwo.getIndividual())).replace(' ', '0');

        System.out.println("<---------------------------Parent DNA" + "    " + MatchDna1 + MatchDna2  +  "------------------------->");

        int aRandomValue = new Random().nextInt(MatchDna1.length());
        String Splitted1 =  MatchDna1.substring(0, aRandomValue);
        String Splitted2 =  MatchDna2.substring(aRandomValue, MatchDna2.length());
        System.out.println("<---------------------------New DNA    " + Splitted1+Splitted2 +"------->");
        return new Individual<>(Integer.parseInt(Splitted1+Splitted2, 2));
   }


    public List<Individual<Integer>> crossoverAndMutation(Individual<Integer> parentOne, Individual<Integer> parentTwo) {
        Random r = new Random();
        Individual<Integer> justAChild;
        List<Individual<Integer>> childPopulation = new ArrayList<>();
        if (r.nextDouble() < crossoverRate) {
            justAChild = createNewChild(parentOne, parentTwo);
            childPopulation.add(justAChild);
        }
        childPopulation.add(parentOne);
        if (r.nextDouble() < mutationRate) {
            justAChild = createNewChild(parentOne, parentTwo);
            childPopulation.add(mutation(justAChild));
        }
        childPopulation.add(parentOne);
        initialPopulation = childPopulation;
        initialPopulation.forEach(n-> System.out.println(" Child Fitness Value:   " + n +  "    From ParentOne Fitness:   " + parentOne.getIndividual() + " ParentTwo Fitness:  " + parentTwo.getIndividual()));
        return null;
    }


    public void getAllChronosomes(List chronosomes) {
        //      chronosomes.forEach(n -> System.out.println(n));
    }

    public void getAllChronosomes(Map map) {
        getFittestFromFittestList(map);
        map.forEach((key, value) -> {
            System.out.println("Chronosome:  " + key + "     Fitness:    " + value);
        });
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
