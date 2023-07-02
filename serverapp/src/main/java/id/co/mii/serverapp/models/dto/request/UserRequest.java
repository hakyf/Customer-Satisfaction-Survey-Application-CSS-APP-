package id.co.mii.serverapp.models.dto.request;

import lombok.Data;

@Data
public class UserRequest {

    private String name;
    private String email;
    private String phone;
    private String jobPosition;
    private String username;
    private String password;
}
