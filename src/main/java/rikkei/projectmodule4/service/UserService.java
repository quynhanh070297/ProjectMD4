package rikkei.projectmodule4.service;


import rikkei.projectmodule4.model.dto.request.DtoFormLogin;
import rikkei.projectmodule4.model.dto.request.DtoFormRegister;
import rikkei.projectmodule4.model.dto.response.JwtResponse;

public interface UserService {
    boolean register(DtoFormRegister formRegister);
    JwtResponse login(DtoFormLogin formLogin);
}
