package by.it_akademy.fitness.service;

import by.it_akademy.fitness.buider.ProfileBuilder;
import by.it_akademy.fitness.enams.EGender;
import by.it_akademy.fitness.enams.ELifestyle;
import by.it_akademy.fitness.idto.InputProfileDTO;
import by.it_akademy.fitness.mappers.ProfileMapper;
import by.it_akademy.fitness.security_module.odto.OutPage;
import by.it_akademy.fitness.odto.OutputProfileDTO;
import by.it_akademy.fitness.service.api.IProfileService;
import by.it_akademy.fitness.service.api.IUserService;
import by.it_akademy.fitness.storage.api.IProfileStorage;
import by.it_akademy.fitness.storage.entity.Profile;
import by.it_akademy.fitness.storage.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.UUID;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    private final String CREATED = "New user's profile was created";

    @Autowired
    private final IProfileStorage storage;
    @Autowired
    private final IUserService userService;

    // TODO private final IAuditService auditService;

    private final ProfileMapper profileMapper;


    @Override
    @Transactional
    public Profile create(InputProfileDTO dto, String header) throws UsernameNotFoundException {

        validate(dto);

        User currentUserProfile = userService.extractCurrentUserProfile(header);

        String mail = SecurityContextHolder.getContext().getAuthentication().getName();

        //UserDetails user = userService.loadCurrentUserByLogin(mail);


        Profile profile = storage.save(ProfileBuilder
                .create()
                .setId(UUID.randomUUID())
                .setDtCreate(Clock.systemUTC().millis())
                .setDtUpdate(Clock.systemUTC().millis())
                .setUser(currentUserProfile)
                .setHeight(dto.getHeight())
                .setWeight(dto.getWeight())
                .setBirthday(dto.getDt_birthday())
                .setGender(dto.getSex())
                .setLifestyle(dto.getActivity_type())
                .setTargetWeight(dto.getTarget())
                .build());

        /* TODO auditService.create(
                user,
                EntityType.PROFILE,
                CREATED,
                profile.getId().toString()
        );

         */

        return profile;
    }

    @Override
    public OutputProfileDTO readById(UUID id) throws UsernameNotFoundException {
        Profile profile = storage.findById(id).orElseThrow();
        return profileMapper.map(profile);
    }

    @Override
    public Profile read(UUID id) throws UsernameNotFoundException {
        return storage.findById(id).orElseThrow();
    }

    @Override
    public OutPage get(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public Profile update(UUID id, Long dtUpdate, InputProfileDTO item, String header) {
        return null;
    }

    @Override
    public void delete(Profile profile) {
    }

    public void validate(InputProfileDTO input) {
        if (input.getWeight() < 30) {
            throw new IllegalStateException("Min value of weight is 30 kg");
        }
        if (input.getHeight() < 100 || input.getHeight() > 300) {
            throw new IllegalStateException("Check you Height");
        }
        if (input.getTarget() < 30 || input.getTarget() > 200) {
            throw new IllegalStateException("Check your target");
        }
        if (!input.getSex().equals(EGender.MALE)
                && !input.getSex().equals(EGender.FEMALE)
                && !input.getSex().equals(EGender.THEY)) {
            throw new IllegalStateException("Check your SEX");
        }
        if (!input.getActivity_type().equals(ELifestyle.ACTIVE)
                && !input.getActivity_type().equals(ELifestyle.PASSIVE)
                && !input.getActivity_type().equals(ELifestyle.COMBINE)) {
            throw new IllegalStateException("Check type of lifestyle");
        }
    }
}
