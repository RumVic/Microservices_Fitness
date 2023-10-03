package mapper;


import odto.OutPage;
import odto.OutputAuditDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import storage.entity.Audit;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuditMapper {


    public OutPage<OutputAuditDTO> map(Page<Audit> auditPages) {

        OutPage<OutputAuditDTO> outPage = new OutPage<>();

        outPage.setNumber(auditPages.getNumber());
        outPage.setSize(auditPages.getSize());
        outPage.setTotalPages(auditPages.getTotalPages());
        outPage.setTotalElements((int) auditPages.getTotalElements());
        outPage.setFirst(auditPages.isFirst());
        outPage.setNumberOfElements(auditPages.getNumberOfElements());
        outPage.setLast(auditPages.isLast());

        List<OutputAuditDTO> dtoList = new ArrayList<>();

        for (Audit audit : auditPages.getContent()) {
            OutputAuditDTO auditDTO = builder(audit);
            dtoList.add(auditDTO);
        }
        outPage.setContent(dtoList);
        return outPage;

    }

    public OutputAuditDTO builder(Audit audit) {
        OutputAuditDTO auditDTO = new OutputAuditDTO();
        UserMapper userMapper = new UserMapper();
        auditDTO.setUser(userMapper.onceMap(audit.getUser()));
        auditDTO.setText(audit.getText());
        auditDTO.setType(audit.getType());
        auditDTO.setUid(audit.getUid());
        return auditDTO;
    }

    public List<OutputAuditDTO> onceMap(List<Audit> list) {

        List<OutputAuditDTO> dtoList = new ArrayList<>();

        for (Audit audit : list) {
            OutputAuditDTO outputAuditDTO = builder(audit);
            dtoList.add(outputAuditDTO);
        }
        return dtoList;
    }

}
