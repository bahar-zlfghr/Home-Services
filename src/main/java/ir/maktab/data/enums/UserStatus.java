package ir.maktab.data.enums;

/**
 * @author : Bahar Zolfaghari
 **/
public enum UserStatus {
    NEW("new"),
    AWAITING_APPROVAL("awaiting approval"),
    APPROVED("approved");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
