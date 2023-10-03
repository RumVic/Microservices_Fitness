package mapper;

import odto.OutPage;
import odto.OutputUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserMapper {

    public OutputUserDTO onceMap(User user) {

        OutputUserDTO dto = new OutputUserDTO();
        dto.setId(UUID.fromString(String.valueOf(user.hashCode())));
        dto.setLogin(user.getUsername());


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
        dto.setId(UUID.fromString(String.valueOf(user.hashCode())));
        dto.setLogin(user.getUsername());
        return dto;
    }

}
