package Application.Controller;

import Application.Algorithm.Parameters;
import Application.Model.ProblemResults;
import Application.Service.PSOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;


@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/pso")
@Slf4j
public class PSOController {

    private PSOService service;
    public PSOController(PSOService service){
        this.service=service;
    }

    @GetMapping("/mccormick")
    public ResponseEntity<ProblemResults> solveMcCormick(Parameters initParameters, @RequestParam Double[] param1Domain, @RequestParam Double[] param2Domain){
        log.info("GET request for solve mccormick");
        ProblemResults problemResults = service.solveMcCormick(initParameters, param1Domain, param2Domain);
        return new ResponseEntity<ProblemResults>(problemResults, HttpStatus.OK);
    }

    @GetMapping("/michalewicz")
    public ResponseEntity<ProblemResults> solveMichalewicz(Parameters initParameters, @RequestParam Double[] param1Domain, @RequestParam Double[] param2Domain){
        log.info("GET request for solve michalewicz");
        ProblemResults problemResults = service.solveMichalewicz(initParameters, param1Domain, param2Domain);
        return new ResponseEntity<ProblemResults>(problemResults, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/ackley")
    public ResponseEntity<ProblemResults> solveAckley(Parameters initParameters, @RequestParam Double dimension){
        log.info("GET request for solve ackley");
        ProblemResults problemResults = service.solveAckley(initParameters, dimension);
        return new ResponseEntity<ProblemResults>(problemResults, HttpStatus.OK);
    }

}
