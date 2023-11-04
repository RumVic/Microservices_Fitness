package by.it_akademy.fitness.security.storage.api;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IUserSecurityStorage extends JpaRepository<UserDetails, UUID> {

    UserDetails findByLogin(String login) throws UsernameNotFoundException;

//    Page<User> findAll(Pageable pageable);
//
//    User findByActivationCode(String code);

}
