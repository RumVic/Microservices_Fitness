package by.it_akademy.fitness.storage.api;

import by.it_akademy.fitness.storage.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface IProductStorage extends JpaRepository<Product, UUID> {
    Optional<Product> findById(@NonNull UUID id);
    Page<Product> findAll(Pageable pageable);

}
