package rikkei.projectmodule4.model.dto.request;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DtoFormLogin
{
    private String username;
    private String password;

}
