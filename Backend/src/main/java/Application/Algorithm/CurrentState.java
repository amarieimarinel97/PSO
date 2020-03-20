package Application.Algorithm;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
public class CurrentState {
    Problem problem;
    Parameters parameters;
    Particle[] particleList;

    Double[] globalBestPosition;
    Double globalBestCost;

    Integer currentIteration;

    public String toString() {
        String result = "";
        result += "Iterations: " + currentIteration + '\n';
        result += "Best cost: " + globalBestCost + " \n";
        result += "Best positions: " + Arrays.toString(globalBestPosition) + "\n";
        result += "Problem: " + problem + "\n";
        result += "Parameters: " + parameters + "\n";

        return result;
    }
}
