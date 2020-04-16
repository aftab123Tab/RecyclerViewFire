package com.example.recyclerviewfire;


public class modelclass {

    private String status;
    public modelclass(){

    }
    public modelclass(String status)
    {
        this.status=status;
    }
    public String getStatus(){
        return status;
    }
    private void setStatus(String status) {
        this.status = status;
    }
}
