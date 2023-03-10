package features.spaceTravel.client;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(length = 200)
    private String name;

    private Client() {
    }

    public Client(String name) {
        setName(name);
    }

    public void setName(String name) {
        if (name == null || name.length() < 3 || name.length() > 200) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }
}
