package cn.xdzn.oj.service.user.common;


import cn.xdzn.oj.service.user.infrastructure.pojo.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 权限工具类
 *
 * @author HeXin
 * @date 2024/03/08
 */
public class AuthUtils {
    private AuthUtils() {

    }
    /**
     * 创建树
     *
     * @param permissionList 权限列表
     * @return {@link List}<{@link Permission}>
     */
    public static List<Permission> createTree(List<Permission> permissionList) {
        ArrayList<Permission> tree = new ArrayList<>();
        for (Permission permission : permissionList) {
            if (permission.getParentId() == 0) {
                addChild(permission, permissionList);
                tree.add(permission);
            }
        }
        return tree;
    }

    /**
     * 添加子项
     *
     * @param permission     准许
     * @param permissionList 权限列表
     */
    private static void addChild(Permission permission, List<Permission> permissionList) {
        ArrayList<Permission> tree = new ArrayList<>();
        for (Permission p : permissionList) {
            if (p.getParentId().equals(permission.getId())) {
                addChild(p, permissionList);
                tree.add(p);
            }
        }
        permission.setChildren(tree);
    }

    /**
     * 获取子密钥名称
     *
     * @param permission 准许
     * @param keys       按键
     */
    public static void getChildrenKeyName(Permission permission, Set<String> keys) {
        if (permission == null) {
            return;
        }
        keys.add(permission.getKeyName());
        if (permission.getChildren() == null || permission.getChildren().isEmpty()) {
            return;
        }
        permission.getChildren().forEach(p -> {
            keys.add(p.getKeyName());
            getChildrenKeyName(p, keys);
        });
    }

    public static Permission getTree(Long id, List<Permission> rootTree) {
        if (rootTree == null) {
            return null;
        }
        for (Permission permission : rootTree) {
            if (permission.getId().equals(id)) {
                return permission;
            } else {
                return getTree(id, permission.getChildren());
            }
        }
        return null;
    }
}
