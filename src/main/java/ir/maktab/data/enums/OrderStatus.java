package ir.maktab.data.enums;

/**
 * @author : Bahar Zolfaghari
 **/
public enum OrderStatus {
    WAITING_FOR_SPECIALIST_SUGGESTION("waiting for specialist suggestion"),
    WAITING_FOR_SPECIALIST_SELECTION("waiting for specialist selection"),
    WAITING_FOR_SPECIALIST_TO_COME_TO_YOUR_PLACE("waiting for specialist to come to your place"),
    STARTED("started"),
    DONE("done"),
    PAID("paid");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
