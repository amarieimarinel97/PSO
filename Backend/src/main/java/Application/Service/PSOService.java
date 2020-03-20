package Application.Service;

import Application.Algorithm.*;
import Application.Model.ProblemResults;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static Application.Algorithm.Functions.ackley;

@Service
public class PSOService {
    public ProblemResults solveMcCormick(Parameters initParameters, Double[] param1Domain, Double[] param2Domain) {
        List<Double[]> parametersDomains = new ArrayList<>();
        parametersDomains.add(param1Domain);
        parametersDomains.add(param2Domain);

        Problem problem = Problem.builder()
                .parametersDomains(parametersDomains)
                .objectiveFunction(Functions::mccormick)
                .build();
        CurrentState state = PSO.init(problem, initParameters);
        state=PSO.solve(state);

        return ProblemResults.builder()
                .arguments(state.getGlobalBestPosition())
                .functionMinimum(state.getGlobalBestCost())
                .iterations(state.getCurrentIteration())
                .build();
    }

    public ProblemResults solveMichalewicz(Parameters initParameters, Double[] param1Domain, Double[] param2Domain){
        List<Double[]> parametersDomains = new ArrayList<>();
        parametersDomains.add(param1Domain);
        parametersDomains.add(param2Domain);

        Problem problem = Problem.builder()
                .parametersDomains(parametersDomains)
                .objectiveFunction(Functions::michalewicz)
                .build();
        CurrentState state = PSO.init(problem, initParameters);
        state=PSO.solve(state);

        return ProblemResults.builder()
                .arguments(state.getGlobalBestPosition())
                .functionMinimum(state.getGlobalBestCost())
                .iterations(state.getCurrentIteration())
                .build();
    }

    public ProblemResults solveAckley(Parameters initParameters, Double dimension){
        List<Double[]> parametersDomains = new ArrayList<>();
        for(int i=0;i<dimension;++i)
            parametersDomains.add(new Double[]{-32.768, 32.768});

        Problem problem = Problem.builder()
                .parametersDomains(parametersDomains)
                .objectiveFunction(Functions::ackley)
                .build();

        initParameters.setMaxVelocity(32.768*2*0.02);

        CurrentState state = PSO.init(problem, initParameters);
        state=PSO.solve(state);

        Double absoluteError = Math.abs(state.getGlobalBestCost() - ackley(new Double[]{0.0}));
        Double percentageError = Math.abs(absoluteError/state.getGlobalBestCost() * 100);
        return ProblemResults.builder()
                .absoluteError(Precision.round(absoluteError, 6))
                .percentageError(percentageError)
                .arguments(state.getGlobalBestPosition())
                .functionMinimum(Precision.round(state.getGlobalBestCost(),6))
                .realMinimum(Precision.round(ackley(new Double[]{0.0}),6))
                .iterations(state.getCurrentIteration())
                .build();
    }
}
