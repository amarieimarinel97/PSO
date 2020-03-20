package Application;

import Application.Algorithm.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

import static Application.Algorithm.Functions.*;

@EnableConfigurationProperties
@SpringBootApplication
public class Application {


    public static void main(String[] args) {

//        List<Double[]> michalewiczDomains = new ArrayList<>();
//        michalewiczDomains.add(new Double[]{1.0, Math.PI});
//        michalewiczDomains.add(new Double[]{1.0, Math.PI});
//        Problem michalewiczProblem = Problem.builder()
//                .objectiveFunction(Functions::michalewicz)
//                .parametersDomains(michalewiczDomains)
//                .build();
//
//
//        Parameters michalewiczParameters = Parameters.builder()
//                .c1(1.5)
//                .c2(0.3)
//                .inertia(0.3)
//                .maxIterations(100)
//                .maxVelocity(1.0)
//                .particlesNo(40)
//                .build();
//
//        CurrentState michalewiczState = PSO.init(michalewiczProblem, michalewiczParameters);
//        michalewiczState = PSO.solve(michalewiczState);
//        System.out.println("----------------------------");
//        System.out.println("Michalewicz");
//        System.out.printf("f(2.20, 1.57)        : %.15f\n", michalewicz(new Double[]{2.20, 1.57}));
//        System.out.printf("f(" + michalewiczState.getGlobalBestPosition()[0] + ", " + michalewiczState.getGlobalBestPosition()[1] + ") : %.15f\n", michalewicz(new Double[]{michalewiczState.getGlobalBestPosition()[0], michalewiczState.getGlobalBestPosition()[1]}));
//        System.out.println(michalewiczState.toString());
//
//
//        List<Double[]> mcCormickDomains = new ArrayList<>();
//        mcCormickDomains.add(new Double[]{-1.5, 4.0});
//        mcCormickDomains.add(new Double[]{-3.0, 4.0});
//
//
//        Problem mcCormickProblem = Problem.builder()
//                .objectiveFunction(Functions::mccormick)
//                .parametersDomains(mcCormickDomains)
//                .build();
//
//        Parameters mcCormickParameters = Parameters.builder()
//                .c1(0.6)
//                .c2(0.3)
//                .inertia(0.0)
//                .maxIterations(100)
//                .particlesNo(100)
//                .maxVelocity(1.0)
//                .build();
//
//        CurrentState mcCormickState = PSO.init(mcCormickProblem, mcCormickParameters);
//        mcCormickState = PSO.solve(mcCormickState);
//        System.out.println("----------------------------");
//        System.out.println("McCormick");
//
//        System.out.printf("f(-.54719, -1.54719) : %.15f\n", mccormick(new Double[]{-.54719, -1.54719}));
//        System.out.printf("f(" + mcCormickState.getGlobalBestPosition()[0] + ", " + mcCormickState.getGlobalBestPosition()[1] + ") : %.15f\n", mccormick(new Double[]{mcCormickState.getGlobalBestPosition()[0], mcCormickState.getGlobalBestPosition()[1]}));
//        System.out.println(mcCormickState.toString());
//

        List<Double[]> ackleyDomains = new ArrayList<>();
        ackleyDomains.add(new Double[]{-32.768, 32.768});
        ackleyDomains.add(new Double[]{-32.768, 32.768});
        ackleyDomains.add(new Double[]{-32.768, 32.768});


        Problem ackleyProblem = Problem.builder()
                .objectiveFunction(Functions::ackley)
                .parametersDomains(ackleyDomains)
                .build();

        Parameters ackleyParameters = Parameters.builder()
                .c1(0.6)
                .c2(0.3)
                .inertia(0.0)
                .maxIterations(1000)
                .particlesNo(250)
                .maxVelocity(1.0)
                .build();

        CurrentState ackleyState = PSO.init(ackleyProblem, ackleyParameters);
        ackleyState = PSO.solve(ackleyState);
        System.out.println("----------------------------");
        System.out.println("Ackley");

        System.out.printf("f(0.0, 0.0, 0.0) : %.15f\n", ackley(new Double[]{0.0, 0.0, 0.0}));
        System.out.printf("f(" + ackleyState.getGlobalBestPosition()[0] + ", " + ackleyState.getGlobalBestPosition()[1] + ", " + ackleyState.getGlobalBestPosition()[2] + ") : %.15f\n", ackley(new Double[]{ackleyState.getGlobalBestPosition()[0], ackleyState.getGlobalBestPosition()[1], ackleyState.getGlobalBestPosition()[2]}));
        System.out.println(ackleyState.toString());



        SpringApplication.run(Application.class, args);

    }
}