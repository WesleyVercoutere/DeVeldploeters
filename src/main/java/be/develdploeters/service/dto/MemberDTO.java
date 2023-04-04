package be.develdploeters.service.dto;

import lombok.Data;

@Data
public class MemberDTO {

    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;

}
