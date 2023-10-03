package by.it_akademy.fitness.mappers;

import by.it_akademy.fitness.odto.OutputProfileDTO;
import by.it_akademy.fitness.storage.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public OutputProfileDTO map(Profile profiles) {
        OutputProfileDTO profileDTO = new OutputProfileDTO();
        profileDTO.setId(profiles.getId());
        profileDTO.setDtCreate(profiles.getDtCreate());
        profileDTO.setDtUpdate(profiles.getDtUpdate());
        profileDTO.setGender(profiles.getGender());
        profileDTO.setHeight(profiles.getHeight());
        profileDTO.setWeight(profiles.getWeight());
        profileDTO.setBirthday(profiles.getBirthday());
        profileDTO.setLifestyle(profiles.getLifestyle());
        profileDTO.setTargetWeight(profiles.getTargetWeight());
        UserMapper userMapper = new UserMapper();
        profileDTO.setUser(userMapper.onceMap(profiles.getUser()));
        return profileDTO;
    }
}
