package io.github.adelyarr.semester.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_type", nullable = false)
    private AuthType authType;

    @Column(name = "oauth2_id", unique = true)
    private String oauth2Id;

    public User(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.authType = AuthType.LOCAL;
    }

    public User(String email, AuthType authType, String oauth2Id) {
        this.email = email;
        this.authType = authType;
        this.oauth2Id = oauth2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
