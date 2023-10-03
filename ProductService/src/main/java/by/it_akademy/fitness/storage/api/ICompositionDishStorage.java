package by.it_akademy.fitness.storage.api;

import by.it_akademy.fitness.storage.entity.CompositionDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface ICompositionDishStorage extends JpaRepository<CompositionDish, UUID> {

    Optional<CompositionDish> findById(@NonNull UUID id);

}
