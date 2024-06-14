package rikkei.projectmodule4.security.principals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rikkei.projectmodule4.model.entity.Roles;
import rikkei.projectmodule4.model.entity.User;
import rikkei.projectmodule4.repository.IUserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("username khong ton tai"));
        return CustomerUserDetail.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .fullName(user.getFullname())
                .address(user.getAddress())
                .email(user.getEmail())
                .status(user.isStatus())
                .authorities(mapToAuthorities(user.getRoles()))
                .build();
    }

    private List<? extends GrantedAuthority> mapToAuthorities(List<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).toList();
    }
}
