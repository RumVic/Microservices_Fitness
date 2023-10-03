package by.it_akademy.fitness.storage.api;

import by.it_akademy.fitness.storage.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface IProfileStorage extends JpaRepository<Profile, UUID> {
    Optional<Profile> findById(@NonNull UUID id);
}
