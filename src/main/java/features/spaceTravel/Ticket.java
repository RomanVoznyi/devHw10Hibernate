package features.spaceTravel;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Entity
@Data
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column
    private Timestamp created_at;
    @Column
    private long client_id;
    @Column
    private long from_planet_id;
    @Column
    private long to_planet_id;
}
