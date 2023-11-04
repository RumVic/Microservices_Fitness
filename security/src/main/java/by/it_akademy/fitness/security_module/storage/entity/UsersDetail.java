package by.it_akademy.fitness.security_module.storage.entity;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "user_fitness", schema = "appuser")
 public class UsersDetail implements UserDetails {

    @Id
    private UUID id;
    private String username;
    private String password;
    private Collection authorities;

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
