package by.it_akademy.fitness.storage.api;

import by.it_akademy.fitness.storage.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IDishStorage extends JpaRepository<Dish, UUID> {
    Page<Dish> findAll(Pageable pageable);

}
