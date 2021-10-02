package ir.maktab.dtos.filter;

/**
 * @author : Bahar Zolfaghari
 **/
public class UserFilterDto {
    private String role;
    private String name;
    private String family;
    private String Email;
    private String score;

    public String getRole() {
        return role;
    }

    public UserFilterDto setRole(String role) {
        this.role = role;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserFilterDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public UserFilterDto setFamily(String family) {
        this.family = family;
        return this;
    }

    public String getEmail() {
        return Email;
    }

    public UserFilterDto setEmail(String email) {
        Email = email;
        return this;
    }

    public String getScore() {
        return score;
    }

    public UserFilterDto setScore(String score) {
        this.score = score;
        return this;
    }
}
