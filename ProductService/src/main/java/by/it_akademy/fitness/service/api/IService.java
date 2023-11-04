package by.it_akademy.fitness.service.api;

import by.it_akademy.fitness.security_module.odto.OutPage;
import by.it_akademy.fitness.security_module.exceptionEdvice.LockException;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IService<ENTITY, IDTO, ODTO> {

    ENTITY create(IDTO dto,String header);

    ENTITY read (UUID id);

    OutPage get(Pageable pageable);

    ENTITY update(UUID id, Long dtUpdate, IDTO item, String header) throws LockException, LockException;

    void delete(ENTITY entity);


}
