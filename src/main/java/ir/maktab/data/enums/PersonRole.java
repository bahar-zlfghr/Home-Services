package ir.maktab.data.enums;

/**
 * @author : Bahar Zolfaghari
 **/
public enum PersonRole {
    MANAGER("manager"),
    CUSTOMER("customer"),
    SPECIALIST("specialist");

    private final String role;

    PersonRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
