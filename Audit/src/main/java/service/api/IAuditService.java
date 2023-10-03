package service.api;

import enams.EntityType;
import odto.OutPage;
import odto.OutputAuditDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import storage.entity.Audit;


import java.util.List;
import java.util.UUID;

@Service
public interface IAuditService {


    Audit create(User user, EntityType entityType, String text, String idEntity);

    OutPage<OutputAuditDTO> get(Pageable pag);

    Audit read (UUID uuid);

    List<OutputAuditDTO> getById(String uuid);

}