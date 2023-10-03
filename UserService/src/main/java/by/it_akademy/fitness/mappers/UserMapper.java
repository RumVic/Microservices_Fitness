package by.it_akademy.fitness.mappers;

import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputUserDTO;
import by.it_akademy.fitness.storage.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public OutputUserDTO onceMap(User user) {

        OutputUserDTO dto = new OutputUserDTO();
        dto.setId(user.getId());
        dto.setDtCrate(user.getDtCrate());
        dto.setDtUpdate(user.getDtUpdate());
        dto.setLogin(user.getLogin());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());

        return dto;
    }

    public OutPage<OutputUserDTO> map(Page<User> userPage) {

        OutPage<OutputUserDTO> dto = new OutPage();

        dto.setNumber(userPage.getNumber());
        dto.setSize(userPage.getSize());
        dto.setTotalPages(userPage.getTotalPages());
        dto.setTotalElements((int) userPage.getTotalElements());
        dto.setFirst(userPage.isFirst());
        dto.setNumberOfElements(userPage.getNumberOfElements());
        dto.setLast(userPage.isLast());

        List<OutputUserDTO> dtoList = new ArrayList<>();

        for (User user : userPage.getContent()) {
            OutputUserDTO userDTO = transfer(user);
            dtoList.add(userDTO);
        }
        dto.setContent(dtoList);
        return dto;
    }

    public OutputUserDTO transfer(User user) {
        OutputUserDTO dto = new OutputUserDTO();
        dto.setId(user.getId());
        dto.setDtCrate(user.getDtCrate());
        dto.setDtUpdate(user.getDtUpdate());
        dto.setRole(user.getRole());
        dto.setLogin(user.getLogin());
        dto.setStatus(user.getStatus());

        return dto;
    }

}
