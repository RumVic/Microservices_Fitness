package by.it_akademy.fitness.service.api;

import by.it_akademy.fitness.idto.IDto;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputAuditDTO;
import by.it_akademy.fitness.storage.entity.Audit;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IAuditService {
    Audit create(IDto dto);
    OutPage<OutputAuditDTO> get(Pageable pag);
    Audit read (UUID uuid);
    List<OutputAuditDTO> getById(String uuid);

}