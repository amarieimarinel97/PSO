package Application.Model;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode
public class ProblemResults {
        Double absoluteError, percentageError;
        Double realMinimum;
        Double functionMinimum;
        Integer iterations;
        Double[] arguments;
}
