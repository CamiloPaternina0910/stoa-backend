package com.backend.stoa.dtos.responses.factories;

import com.backend.stoa.dtos.responses.Response;

import java.time.LocalDate;

public class ObjectResponseImp implements ResponseFactory{

    private Object data;
    private int status;
    private boolean error;
    private Response response;

    public ObjectResponseImp(Object data, int status, boolean error){
        this.data = data;
        this.status = status;
        this.error = error;
    }

    @Override
    public Response getResponse() {
        response = Response.builder().error(error).status(status).timestamp(LocalDate.now()).data(data).build();
        return response;
    }
}
