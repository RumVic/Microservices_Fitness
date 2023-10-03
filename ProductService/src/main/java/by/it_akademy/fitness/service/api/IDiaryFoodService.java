package by.it_akademy.fitness.service.api;

import by.it_akademy.fitness.exception.LockException;
import by.it_akademy.fitness.idto.InputDiaryFoodDTO;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputDiaryFoodDTO;
import by.it_akademy.fitness.storage.entity.DiaryFood;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;


public interface IDiaryFoodService extends IService<DiaryFood, InputDiaryFoodDTO, OutputDiaryFoodDTO> {
    DiaryFood createWithParam(InputDiaryFoodDTO idto, String header, UUID uuid) throws LockException;

    List<DiaryFood> getListOfLine(UUID id);

    OutPage get(Pageable pageable, UUID id);
}
