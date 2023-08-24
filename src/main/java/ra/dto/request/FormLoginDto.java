package ra.dto.request;

import org.springframework.validation.Errors;

public class FormLoginDto {
    private String username;
    private String password;

    public FormLoginDto() {
    }

    public FormLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void checkValidate(Errors errors) {
        // kiểm tra trống
        if (this.username.trim().equals("")){
            errors.rejectValue("username","username.empty");
        }
    }
}