package Application.Algorithm;

import java.util.*;

public class PSO {
    private static Random r = new Random();

    public static CurrentState init(Problem problem, Parameters initParams) {
        int noOfParameters = problem.parametersDomains.size();

        Particle[] particleList = new Particle[initParams.particlesNo];
        Double[] globalBestPosition = new Double[noOfParameters];
        Arrays.fill(globalBestPosition, Double.MAX_VALUE);

        for (int i = 0; i < initParams.particlesNo; ++i) {
            Double[] position = new Double[noOfParameters];
            Double[] velocity = new Double[noOfParameters];

            for (int j = 0; j < noOfParameters; ++j) {
                Double min = problem.parametersDomains.get(j)[0];
                Double max = problem.parametersDomains.get(j)[1];

                position[j] = min + (max - min) * r.nextDouble();
                velocity[j] = r.nextDouble() * initParams.maxVelocity;
            }

            Particle particle = Particle.builder()
                    .position(position)
                    .bestPersonalPosition(position)
                    .velocity(velocity)
                    .build();

            particle.setCost(problem.objectiveFunction.apply(particle.position));
            particleList[i] = particle;
        }

        return CurrentState.builder()
                .globalBestCost(Double.MAX_VALUE)
                .globalBestPosition(globalBestPosition)
                .parameters(initParams)
                .particleList(particleList)
                .problem(problem)
                .currentIteration(0)
                .build();
    }


    public static CurrentState particleSwarmOptimize(CurrentState oldstate) {
        Parameters parameters = oldstate.parameters;
        Particle[] newParticles = new Particle[parameters.particlesNo];
        int noOfParameters = oldstate.problem.parametersDomains.size();

        Double newGlobalBestCost = Double.MAX_VALUE;
        Double[] newGlobalBestPosition = oldstate.getGlobalBestPosition();
        for (int i = 0; i < parameters.particlesNo; ++i) {
            Particle p = oldstate.particleList[i];
            newParticles[i] = Particle.builder()
                    .bestPersonalPosition(Arrays.copyOf(p.bestPersonalPosition, p.bestPersonalPosition.length))
                    .position(Arrays.copyOf(p.position, p.position.length))
                    .velocity(Arrays.copyOf(p.velocity, p.velocity.length))
                    .cost(p.cost)
                    .build();
        }
        for (int i = 0; i < parameters.particlesNo; ++i) {
            Double[] position = new Double[noOfParameters];
            for (int j = 0; j < noOfParameters; ++j) {
                position[j] = oldstate.problem.parametersDomains.get(j)[0];
            }
            newParticles[i].position = position;
        }


        for (int i = 0; i < parameters.particlesNo; ++i) {
            Particle p = newParticles[i];

            Double potentialCost = oldstate.problem
                    .objectiveFunction.apply(oldstate
                            .particleList[i]
                            .position);
            if (potentialCost < oldstate.particleList[i].cost) {
                p.setBestPersonalPosition(oldstate.particleList[i].position);
                p.setCost(potentialCost);
            } else {
                p.setBestPersonalPosition(oldstate.particleList[i].bestPersonalPosition);
                p.setCost(oldstate.particleList[i].cost);
            }

            if (p.getCost() < newGlobalBestCost) {
                newGlobalBestCost = p.getCost();
                newGlobalBestPosition = p.getBestPersonalPosition();
            }
            newParticles[i] = p;
        }


        Double W = parameters.inertia;
        Double c1 = parameters.c1;
        Double c2 = parameters.c2;

        for (int i = 0; i < parameters.particlesNo; ++i) {
            boolean areNewPositionsInDomain = true;
            Particle p = newParticles[i];

            Double[] oldPosition = oldstate.particleList[i].getPosition();
            Double[] oldVelocity = oldstate.particleList[i].getVelocity();

            for (int k = 0; k < noOfParameters; ++k) {

                double r1 = r.nextDouble();
                double r2 = r.nextDouble();
                Double newVelocity = W * oldVelocity[k] +
                        c1 * r1 * (p.bestPersonalPosition[k] - oldPosition[k]) +
                        c2 * r2 * (newGlobalBestPosition[k] - oldPosition[k]);
                Double newPosition = newVelocity + oldPosition[k];

                p.getVelocity()[k] = newVelocity;
                p.getPosition()[k] = newPosition;

                areNewPositionsInDomain = areNewPositionsInDomain
                        && oldstate.problem.parametersDomains.get(k)[0] <= p.getPosition()[k]
                        && oldstate.problem.parametersDomains.get(k)[1] >= p.getPosition()[k];
            }

            if (!areNewPositionsInDomain) {
                for (int k = 0; k < noOfParameters; ++k) {
                    Double min = oldstate.problem.parametersDomains.get(k)[0];
                    Double max = oldstate.problem.parametersDomains.get(k)[1];

                    Double newPosition = min + (max - min) * r.nextDouble();
                    p.getPosition()[k] = newPosition;
                }
            }
            newParticles[i] = p;
        }

        return CurrentState.builder()
                .currentIteration(oldstate.currentIteration + 1)
                .globalBestPosition(newGlobalBestPosition)
                .globalBestCost(newGlobalBestCost)
                .problem(oldstate.problem)
                .parameters(oldstate.parameters)
                .particleList(newParticles)
                .build();
    }

    public static CurrentState solve(CurrentState initialState) {
        CurrentState resultedState = initialState;
        int noOfIterations = resultedState.parameters.maxIterations;

        double inertiaDecreasingStep = (initialState.parameters.inertia - 0.1) / noOfIterations > 0 ?
                (initialState.parameters.inertia - 0.1) / noOfIterations : 0;

        for (int i = 0; i < noOfIterations; ++i) {
            resultedState = particleSwarmOptimize(resultedState);
            resultedState.parameters.inertia -= inertiaDecreasingStep;
        }
        return resultedState;
    }
}
