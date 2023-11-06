package by.it_akademy.fitness.service;

import by.it_akademy.fitness.builder.AuditBuilder;
import by.it_akademy.fitness.idto.IDto;
import by.it_akademy.fitness.mapper.AuditMapper;
import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputAuditDTO;
import by.it_akademy.fitness.service.api.IAuditService;
import by.it_akademy.fitness.storage.api.IAuditStorage;
import by.it_akademy.fitness.storage.entity.Audit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditService implements IAuditService {
    private final IAuditStorage storage;
    private final AuditMapper auditMapper;


    @Override
    @Transactional
    public Audit create(IDto iDto) {
        return storage.save(
                AuditBuilder
                        .create()
                        .setId(UUID.randomUUID())
                        .setDtCreate(Clock.systemUTC().millis())
                        .setDtUpdate(Clock.systemUTC().millis())
                        .setText(iDto.getter().getDescription())
                        .setType(iDto.getter().getEntityType())
                        .setUid(iDto.getter().getUserId())
                        .setUser(iDto.getter().getUserId())
                        .build());
    }

    public OutPage<OutputAuditDTO> get(Pageable pag) {
        Page<Audit> auditPage = storage.findAll(pag);
        return auditMapper.map(auditPage);
    }
    @Override
    public List<OutputAuditDTO> getById(String uuid) {
        List<Audit> audits = storage.findByUid(uuid);
        return auditMapper.onceMap(audits);
    }
    @Override
    public Audit read(UUID uuid) {
        return null;
    }
}

