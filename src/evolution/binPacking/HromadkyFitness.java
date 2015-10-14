package evolution.binPacking;

import evolution.FitnessFunction;
import evolution.individuals.Individual;
import evolution.individuals.IntegerIndividual;

import java.util.Vector;

public class HromadkyFitness implements FitnessFunction {

    Vector<Double> weights;
    int K;

    public HromadkyFitness(Vector<Double> weights, int K) {
        this.weights = weights;
        this.K = K;
    }

    public int[] getBinWeights(Individual ind) {

        int[] binWeights = new int[K];

        int[] bins = ((IntegerIndividual) ind).toIntArray();

        for (int i = 0; i < bins.length; i++) {

            binWeights[bins[i]] += weights.get(i);
        }

        return binWeights;

    }

    @Override
    public double evaluate(Individual ind) {

        int[] binWeights = getBinWeights(ind);

        double squares = 0;
        double sum = 0;
        
        double min = Integer.MAX_VALUE;
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            if (binWeights[i] < min) {
                min = binWeights[i];
            }
            if (binWeights[i] > max) {
                max = binWeights[i];
            }
            
            sum += binWeights[i];
            squares += binWeights[i]*binWeights[i];
        }

        ind.setObjectiveValue(max - min);    // tohle doporucuji zachovat
        
        double average = sum / K;
        
        return 2 / Math.pow(Math.log(
            (squares - (K*average*average)) / K
        ), 1);

//        return 1 / (max - min);
    }
}
