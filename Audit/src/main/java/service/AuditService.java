package service;


import builder.AuditBuilder;
import enams.EntityType;
import lombok.RequiredArgsConstructor;
import mapper.AuditMapper;
import odto.OutPage;
import odto.OutputAuditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.api.IAuditService;
import storage.api.IAuditStorage;
import storage.entity.Audit;

import java.time.Clock;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuditService implements IAuditService {

    @Autowired
    private final IAuditStorage storage;
    @Autowired
    private final AuditMapper auditMapper;


    @Override
    @Transactional
    public Audit create(User user,
                        EntityType entityType,
                        String text,
                        String idEntity) {

        return storage.save(
                AuditBuilder
                        .create()
                        .setId(UUID.randomUUID())
                        .setDtCreate(Clock.systemUTC().millis())
                        .setDtUpdate(Clock.systemUTC().millis())
                        .setText(text)
                        .setType(entityType)
                        .setUid(idEntity)
                        .setUser(user)
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

