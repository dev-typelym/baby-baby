package com.app.babybaby.type;

public enum AlertReadStatus {
    READ("읽음"), UNREAD("안 읽음");

    private String value;

    private AlertReadStatus(String value){this.value = value;}

    public String toString(){
        return this.value;
    }

    public static AlertReadStatus change(String value){
        AlertReadStatus result = null;
        for(AlertReadStatus status : AlertReadStatus.values()){
            if(status.toString().equals(value)){
                result = status;
                break;
            }
        }
        return result;
    }
}
