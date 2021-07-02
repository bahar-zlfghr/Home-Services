package ir.maktab.exceptions;

/**
 * @author : Bahar Zolfaghari
 **/
public class PasswordNotMatchedException extends Exception {
    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
