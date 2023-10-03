package by.it_akademy.fitness.storage.api;

import by.it_akademy.fitness.storage.entity.DiaryFood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface IDiaryFoodStorage extends JpaRepository<DiaryFood, UUID> {
    Optional<DiaryFood> findById(@NonNull UUID id);
    Page<DiaryFood> findAllByProfile(Pageable pageable, UUID profileUuid);

}
