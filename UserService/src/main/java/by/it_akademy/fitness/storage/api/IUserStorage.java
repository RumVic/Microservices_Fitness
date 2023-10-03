package by.it_akademy.fitness.storage.api;

import by.it_akademy.fitness.storage.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserStorage extends JpaRepository<User, UUID> {
    User findByLogin(String login);

    Page<User> findAll(Pageable pageable);

    User findByActivationCode(String code);

}
