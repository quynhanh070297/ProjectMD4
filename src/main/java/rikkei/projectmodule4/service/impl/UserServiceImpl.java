package rikkei.projectmodule4.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rikkei.projectmodule4.model.dto.request.DtoFormLogin;
import rikkei.projectmodule4.model.dto.request.DtoFormRegister;
import rikkei.projectmodule4.model.dto.response.JwtResponse;
import rikkei.projectmodule4.model.entity.Roles;
import rikkei.projectmodule4.model.entity.User;
import rikkei.projectmodule4.repository.IRoleRepository;
import rikkei.projectmodule4.repository.IUserRepository;
import rikkei.projectmodule4.security.jwt.JWTProvider;
import rikkei.projectmodule4.security.principals.CustomerUserDetail;
import rikkei.projectmodule4.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserServiceImpl implements UserService
{
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;

    @Override
    public JwtResponse login(DtoFormLogin formLogin) {
        Authentication authentication = null;
        try {
            //check thông tin đăng nhập gồm username và password có đúng hay không?
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(formLogin.getUsername(),formLogin.getPassword()));
        }catch (AuthenticationException e){
            log.error("Username or password not valid!");
        }
        //Lấy thông tin tài khoản đã đăng nhập lưu thông authentication
        CustomerUserDetail userDetail = (CustomerUserDetail) authentication.getPrincipal();

        //Tạo chuỗi token từ thông tin tài khoản
        String token = jwtProvider.generateToken(userDetail);

        //Trả về cho người dùng dữ liệu khi login xong (bao gồm cả chuỗi token và các thông tin liên quan tài khoản)
        return JwtResponse.builder()
                .fullName(userDetail.getFullName())
                .address(userDetail.getAddress())
                .email(userDetail.getEmail())
                .status(userDetail.getStatus())
                .authorities(userDetail.getAuthorities())
                .token(token)
                .build();
    }

    @Override
    public boolean register(DtoFormRegister formRegister)
    {
        User user = User.builder()
                .username(formRegister.getUsername())
                .password(passwordEncoder.encode(formRegister.getPassword()))
                .fullname(formRegister.getFullName())
                .address(formRegister.getAddress())
                .email(formRegister.getEmail())
                .status(true)
                .build();
        List<Roles> roles = new ArrayList<>();
        if(formRegister.getRoles()!=null && !formRegister.getRoles().isEmpty()){
            formRegister.getRoles().forEach(role -> {
                switch (role){
                    case "ROLE_ADMIN":
                        roles.add(roleRepository.findRoleByRoleName(role).orElseThrow(()->new NoSuchElementException("role admin khong ton tai")));
                        break;
                    case "ROLE_USER":
                        roles.add(roleRepository.findRoleByRoleName(role).orElseThrow(()->new NoSuchElementException("role user khong ton tai")));
                        break;
                    case "ROLE_MODERATOR":
                        roles.add(roleRepository.findRoleByRoleName(role).orElseThrow(()->new NoSuchElementException("role moderator khong ton tai")));
                        break;
                }
            });
        }else{
            roles.add(roleRepository.findRoleByRoleName("ROLE_USER").orElseThrow(()->new NoSuchElementException("role user khong ton tai")));
        }
        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }
}
