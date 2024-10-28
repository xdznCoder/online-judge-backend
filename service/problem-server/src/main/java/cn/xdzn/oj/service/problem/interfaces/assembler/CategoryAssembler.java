package cn.xdzn.oj.service.problem.interfaces.assembler;

import cn.xdzn.oj.service.problem.domain.Training.entity.po.Category;
import cn.xdzn.oj.service.problem.interfaces.dto.CategoryDTO;

public class CategoryAssembler {
    private CategoryAssembler() {
    }

    public static CategoryDTO toDTO(Category category) {
        return new CategoryDTO()
                .setId(category.getId())
                .setName(category.getName())
                .setColor(category.getColor())
                .setGid(category.getGid());
    }
}
