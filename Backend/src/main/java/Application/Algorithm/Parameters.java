package Application.Algorithm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Parameters {
    Integer particlesNo;
    Double maxVelocity;
    Double inertia;
    Double c1, c2;
    Integer maxIterations;

    @Override
    public String toString() {
        return  "particlesNo=" + particlesNo +
                ", maxVelocity=" + maxVelocity +
                ", inertia=" + inertia +
                ", c1=" + c1 +
                ", c2=" + c2 +
                ", maxIterations=" + maxIterations;
    }
}
