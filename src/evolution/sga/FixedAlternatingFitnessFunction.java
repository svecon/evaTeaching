package evolution.sga;

import evolution.FitnessFunction;
import evolution.individuals.BooleanIndividual;
import evolution.individuals.Individual;

/**
 * @author Ondrej Svec
 */
public class FixedAlternatingFitnessFunction implements FitnessFunction {

    /**
     * THis is an example fitness function

     * @param ind The individual which shall be evaluated
     * @return The number of 1s in the individual
     */
    @Override
    public double evaluate(Individual ind) {

        BooleanIndividual bi = (BooleanIndividual) ind;
        boolean[] genes = bi.toBooleanArray();

        double fitness = 0.0;

        for (int i = 0; i < genes.length; i++) {
            if ((genes[i] && i%2 == 1) || (!genes[i] && i%2==0))
                fitness += 1.0;
        }

        ind.setObjectiveValue(fitness);

        return fitness;
    }

}
