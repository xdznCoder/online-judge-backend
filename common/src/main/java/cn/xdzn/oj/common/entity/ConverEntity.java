package cn.xdzn.oj.common.entity;


import cn.xdzn.oj.common.exception.CustomException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换实体
 * @author HeXin
 */
public class ConverEntity<P extends BaseEntity> {
    /**
     * 反射转换PO
     */
    public P toPo(Class<P> pojoClass) {
        try {
            // 创建新的PO对象
            P pojo = pojoClass.getDeclaredConstructor().newInstance();

            // 获取DTO和PO的所有字段
            Field[] dtoFields = this.getClass().getDeclaredFields();
            Field[] pojoFields = pojoClass.getDeclaredFields();
            Map<String, Field> pojoFieldMap = new HashMap<>();
            for (Field pojoField : pojoFields) {
                pojoField.setAccessible(true);
                pojoFieldMap.put(pojoField.getName(), pojoField);
            }
            // 遍历DTO字段，为POJO字段赋值
            for (Field dtoField : dtoFields) {
                dtoField.setAccessible(true);
                String fieldName = dtoField.getName();
                if (pojoFieldMap.containsKey(fieldName)) {
                    Field pojoField = pojoFieldMap.get(fieldName);
                    pojoField.setAccessible(true);
                    Class<?> dtoFieldType = dtoField.getType();
                    Class<?> pojoFieldType = pojoField.getType();
                    // 如果是Map<String, String>类型的属性，特殊处理
                    if (Map.class.isAssignableFrom(dtoFieldType) && Map.class.isAssignableFrom(pojoFieldType)) {
                        Map<String, String> dtoMap = (Map<String, String>) dtoField.get(this);
                        if (dtoMap != null) {
                            Map<String, String> pojoMap = new HashMap<>(dtoMap); // 使用可修改的Map
                            pojoField.set(pojo, pojoMap);
                        }
                    } else {
                        // 非Map类型的属性，正常赋值
                        if (pojoFieldType.isAssignableFrom(dtoFieldType)) {
                            pojoField.set(pojo, dtoField.get(this));
                        } else {
                            // 如果字段类型不兼容，可以选择跳过或者进行类型转换
                            throw new CustomException("Incompatible field type: " + fieldName);
                        }
                    }
                }
            }
            return pojo;
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }
}