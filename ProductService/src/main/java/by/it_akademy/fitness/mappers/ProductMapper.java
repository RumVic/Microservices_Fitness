package by.it_akademy.fitness.mappers;

import by.it_akademy.fitness.odto.OutPage;
import by.it_akademy.fitness.odto.OutputProductDTO;
import by.it_akademy.fitness.storage.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductMapper {

    public OutPage<OutputProductDTO> map(Page<Product> productOutPage) {

        OutPage<OutputProductDTO> outPage = new OutPage<>();

        outPage.setNumber(productOutPage.getNumber());
        outPage.setSize(productOutPage.getSize());
        outPage.setTotalPages(productOutPage.getTotalPages());
        outPage.setTotalElements((int) productOutPage.getTotalElements());
        outPage.setFirst(productOutPage.isFirst());
        outPage.setNumberOfElements(productOutPage.getNumberOfElements());
        outPage.setLast(productOutPage.isLast());

        List<OutputProductDTO> list = new ArrayList<>();

        for (Product product : productOutPage.getContent()) {
            OutputProductDTO productDTO = fromEntityToOutput(product);
            list.add(productDTO);
        }

        outPage.setContent(list);
        return outPage;
    }

    public OutputProductDTO fromEntityToOutput(Product product) {

        OutputProductDTO dto = new OutputProductDTO();
        dto.setId(product.getId());
        dto.setDtCreate(product.getDtCreate());
        dto.setDtUpdate(product.getDtUpdate());
        dto.setTitle(product.getTitle());
        dto.setCalories(product.getCalories());
        dto.setFats(product.getFats());
        dto.setCarbohydrates(product.getCarbohydrates());
        dto.setWeight(product.getWeight());
        dto.setProteins(product.getProteins());
        return dto;
    }
}
