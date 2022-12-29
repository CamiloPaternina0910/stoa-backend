package com.backend.stoa.dtos.responses.factories;

import com.backend.stoa.dtos.responses.Response;
import com.universidad.web.responses.Response;

import java.time.LocalDate;
import java.util.List;

public class GeneralListResponseImp<T> implements ResponseFactory{

    private List<T> data;
    private boolean error;
    private int status;
    private Response response;

    public GeneralListResponseImp(List<T> data, boolean error, int status){
        this.data = data;
        this.error = error;
        this.status = status;
    }

    @Override
    public Response getResponse() {
        response = Response.builder().error(error).data(data).status(status).timestamp(LocalDate.now()).build();
        return response;
    }
}
