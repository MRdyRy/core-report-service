package com.rudy.ryanto.core.report.util;

public class ReportConstant {
    public static final String UTF_8 = "UTF-8";

    public final static String GENERAL_ERROR = "GENERAL_ERROR";

    public enum error{
        GENERAL_ERROR("0","General Error!"),
        CONNECTION_ERROR("1","Error Connection!"),
        USER_NOT_VALID("2","User Sender Not Found / Invalid!"),
        INVALID_MESSAGE("3","Invalid Message, check message!"),
        DATA_NOT_VALID("4","Data Not Valid!");

        private String error_code=null,description=null;

        private error(String code,String description){
            this.error_code=code;
            this.description=description;
        }

        public String getCode(){
            return this.error_code;
        }

        public String getDescription(){
            return this.description;
        }
    }

}
