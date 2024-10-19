package cn.xdzn.oj.common.entity;

import lombok.Data;

/**
 * @author m
 * @date 2023/7/24
 * @Description
 */
@Data
public class BarDTO {
    String name;
    String value;

    public BarDTO() {
    }

    public BarDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
