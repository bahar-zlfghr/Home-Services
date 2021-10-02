package ir.maktab.exceptions;

/**
 * @author : Bahar Zolfaghari
 **/
public class DuplicateEmailException extends Exception {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
