package Application.Algorithm;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.function.Function;

@Data
@Builder
public class Problem {
    Function<Double[], Double> objectiveFunction;
    List<Double[]> parametersDomains;


    private String printParameters(){
        String result ="";
        for(Double[] domain : parametersDomains){
            result+="[ ";
            for(int i=0;i<domain.length;++i){
                result+=domain[i]+" ";
            }
            result +=" ] ;";
        }
        return result;
    }

    @Override
    public String toString() {
        return  "objectiveFunction=" + "mccornick " +
                ", parametersDomains=" +  printParameters();
    }
}
