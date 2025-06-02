package vn.edu.hcmuaf.fit.projectwebck.dao.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Role {
    USER(0, "Người dùng", new HashSet<>(Arrays.asList("VIEW_HOME"))),
    ADMIN(1, "Quản trị viên", new HashSet<>(Arrays.asList(
            "VIEW_DASHBOARD", "MANAGE_VEGETABLES", "MANAGE_USERS", "MANAGE_ORDERS",
                  "MANAGE_PROMOTIONS", "MANAGE_PRODUCT_PROMOTION", "MANAGE_STOCK"
    ))),
    MOD_VEGETABLES(2, "Quản lý rau củ", new HashSet<>(Arrays.asList("VIEW_DASHBOARD", "MANAGE_VEGETABLES"))),
    MOD_USERS(3, "Quản lý người dùng", new HashSet<>(Arrays.asList("VIEW_DASHBOARD", "MANAGE_USERS"))),
    MOD_ORDERS(4, "Quản lý đơn hàng", new HashSet<>(Arrays.asList("VIEW_DASHBOARD", "MANAGE_ORDERS"))),
    MOD_PROMOTIONS(5, "Quản lý khuyến mãi", new HashSet<>(Arrays.asList("VIEW_DASHBOARD", "MANAGE_PROMOTIONS"))),
    MOD_PRODUCT_PROMOTION(6, "Quản lý sản phẩm giảm giá", new HashSet<>(Arrays.asList("VIEW_DASHBOARD", "MANAGE_PRODUCT_PROMOTION"))),
    MOD_STOCK(7, "Quản lý kho", new HashSet<>(Arrays.asList("VIEW_DASHBOARD", "MANAGE_STOCK"))),
    GUEST(8, "Khách", new HashSet<>(Arrays.asList("VIEW_HOME")));

    private final int id;
    private final String name;
    private final Set<String> permissions;

    Role(int id, String name, Set<String> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public int GetId() {
        return id;
    }

    public String GetName() {
        return name;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public static Role fromId(int id) {
        for (Role role : values()) {
            if (role.id == id) return role;
        }
        return GUEST;
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
}
