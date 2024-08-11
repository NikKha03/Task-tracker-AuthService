package sharpBubbles.authService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue
    private long teamId;

    private String name;

}
