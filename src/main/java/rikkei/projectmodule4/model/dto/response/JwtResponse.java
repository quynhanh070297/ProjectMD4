package rikkei.projectmodule4.model.dto.response;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse
{
    private String fullName;
    private String address;
    private String email;
    private Boolean status;
    private Collection<? extends GrantedAuthority> authorities;
    private String token;
}
