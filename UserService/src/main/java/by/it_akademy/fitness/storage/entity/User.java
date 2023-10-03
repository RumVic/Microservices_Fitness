package by.it_akademy.fitness.storage.entity;

import by.it_akademy.fitness.enams.EStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
@Table(name ="user_fitness")

public class User implements UserDetails {
    @Id
    private UUID id;
    @Column(name = "dt_create")
    private Long dtCrate;
    @Column(name = "dt_update")
    private Long dtUpdate;
    private String username;
    @Column(unique = true)
    private String login;
    private String activationCode;//new
    private String password;
    private String role;
    //private Collection<GrantedAuthority> role;
    @Enumerated(value = EnumType.STRING)
    private EStatus status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
