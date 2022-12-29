package com.backend.stoa.dtos.responses.factories;

import com.backend.stoa.dtos.responses.LoginData;
import com.backend.stoa.dtos.responses.Response;
import com.universidad.web.responses.LoginData;
import com.universidad.web.responses.Response;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class LoginResponseImp implements ResponseFactory{

    private final String msg = "User found";
    private String token;
    private Response response;

    public LoginResponseImp(String token){
        this.token = token;
    }

    @Override
    public Response getResponse() {
        response = response.builder().error(false).timestamp(LocalDate.now()).data(LoginData.builder().msg(msg).token(token).build()).status(HttpStatus.OK.value()).build();
        return response;
    }
}
