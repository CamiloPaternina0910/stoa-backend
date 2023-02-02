package com.backend.stoa.dtos.responses.factories;

import com.backend.stoa.dtos.responses.Response;
import java.time.LocalDate;

public class GeneralResponseImp implements ResponseFactory{

    private String msg;
    private boolean error;
    private int status;
    private Response response;

    public GeneralResponseImp(String msg, boolean error, int status){
        this.msg = msg;
        this.error = error;
        this.status = status;
    }

    @Override
    public Response getResponse() {
        response = response.builder().error(error).timestamp(LocalDate.now()).data(msg).status(status).build();
        return response;
    }
}
