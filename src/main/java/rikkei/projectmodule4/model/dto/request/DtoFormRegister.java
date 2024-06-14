package rikkei.projectmodule4.model.dto.request;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DtoFormRegister
{
    private String username;
    private String password;
    private String fullName;
    private String address;
    private String email;
    private List<String> roles;
}
