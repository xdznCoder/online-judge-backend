package cn.xdzn.oj.common.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 三元组
 *
 * @author HeXin
 * @date 2024/06/08
 */
@Data
public class Triad {
    /**
     * 维度
     */
    private String dimension;
    /**
     * 名字
     */
    private String name;
    /**
     * 价值
     */
    private String value;

    public Triad() {
    }

    public Triad(String dimension, String name, String value) {
        this.dimension = dimension;
        this.name = name;
        this.value = value;
    }

    /**
     * 降级为二元组
     *
     * @return {@link List }<{@link BinaryGroup }>
     */
    public static List<BinaryGroup<String, ArrayList<BarDTO>>> demoteBinary(List<Triad> triads) {
        // 按照维度分组
        Map<String, List<Triad>> dimensionMap = triads.stream().collect(Collectors.groupingBy(Triad::getDimension));
        Map<String,ArrayList<BarDTO>> binaryMap = new HashMap<>();
        for(Map.Entry<String,List<Triad>> entry : dimensionMap.entrySet()) {
            binaryMap.put(entry.getKey(),new ArrayList<>(getBarDTO(entry.getValue())));
        }
        // 创建二元组
        BinaryGroup<String, ArrayList<BarDTO>> binaryGroup = new BinaryGroup<>();
        return binaryGroup.getList(binaryMap);
    }

    public static List<BarDTO> getBarDTO(List<Triad> triads) {
        return triads.stream().map(Triad::getBarDTO).toList();
    }

    public static BarDTO getBarDTO(Triad triad) {
        return new BarDTO(triad.getName(),triad.getValue());
    }
}
