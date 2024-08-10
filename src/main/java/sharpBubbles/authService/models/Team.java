package sharpBubbles.authService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;

import java.util.List;

@Entity
@Data
@Table(name = "teams")
public class Team {

    private List<User> teamUsers;

    private String teamName;

    @Id
    @GeneratedValue
    private long teamId;

}
