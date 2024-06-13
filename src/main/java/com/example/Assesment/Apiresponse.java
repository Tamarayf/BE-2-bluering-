package com.example.Assesment;

import com.example.Assesment.DTO.LeaveeDTO;
import lombok.Data;

import java.util.List;


@Data
public class Apiresponse {
    private Boolean success;
    private String message;
    private Object returnField;

    public Apiresponse(Boolean success,String message){
        this.success=success;
        this.message=message;
    }

    public Apiresponse(Boolean success,String message,Object returnField){
        this.success=success;
        this.message=message;
        this.returnField=returnField;
    }


}