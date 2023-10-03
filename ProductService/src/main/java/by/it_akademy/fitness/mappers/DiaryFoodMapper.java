package by.it_akademy.fitness.mappers;

import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputDiaryFoodDTO;
import by.it_akademy.fitness.storage.entity.DiaryFood;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DiaryFoodMapper {


    public OutPage<OutputDiaryFoodDTO> map(Page<DiaryFood> page) {

        OutPage<OutputDiaryFoodDTO> outDto = new OutPage<>();

        outDto.setNumber(page.getNumber());
        outDto.setSize(page.getSize());
        outDto.setTotalPages(page.getTotalPages());
        outDto.setTotalElements((int) page.getTotalElements());
        outDto.setFirst(page.isFirst());
        outDto.setNumberOfElements(page.getNumberOfElements());
        outDto.setLast(page.isLast());

        List<OutputDiaryFoodDTO> listDiary = new ArrayList<>();

        for (DiaryFood diary : page.getContent()) {
            OutputDiaryFoodDTO dto = new OutputDiaryFoodDTO();
            dto.setId(diary.getId());
            dto.setDtCreate(diary.getDtCreate());
            dto.setDtUpdate(diary.getDtUpdate());
            dto.setDtSupply(diary.getDtSupply());
            dto.setProfile(diary.getProfile());

            if (diary.getDish() != null) {
                DishMapper dishMapper = new DishMapper();
                dto.setDish(dishMapper.builderDish(diary.getDish()));
                dto.setWeightDish(diary.getWeightDish());
            } else dto.setDish(null);

            if (diary.getProduct() != null) {
                ProductMapper productMapper = new ProductMapper();
                dto.setProduct(productMapper.fromEntityToOutput(diary.getProduct()));
                dto.setWeightProduct(diary.getWeightProduct());
            } else dto.setProduct(null);

            listDiary.add(dto);
        }
        outDto.setContent(listDiary);

        return outDto;
    }
}
