package sharpBubbles.authService.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Column(unique = true)
    private String email;

    private String tg;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole roles;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RegistrationSource registrationSource;

}