package ir.maktab.data.enums;

/**
 * @author : Bahar Zolfaghari
 **/
public enum OfferStatus {
    ACCEPTED("accepted"),
    NOT_ACCEPTED("not accepted");

    private final String status;

    OfferStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
