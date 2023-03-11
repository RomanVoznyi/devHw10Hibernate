package features.spaceTravel;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Planet {
    @Id
    private String id;

    @Column(length = 500)
    private String name;

    private Planet() {
    }

    public Planet(String id, String name) {
        setId(id);
        setName(name);
    }

    public void setId(String id) {
        if (id == null || !id.matches("^[A-Z0-9]{1,200}$")) {
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.length() < 1 || name.length() > 200) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }
}
