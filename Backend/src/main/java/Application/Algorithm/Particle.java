package Application.Algorithm;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Particle {
    Double[] position;
    Double cost;
    Double[] velocity;
    Double[] bestPersonalPosition;
}
