package com.mthree.Exception;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String errMsg, Throwable err){
        super(errMsg, err);
    }

    public InsufficientFundsException(String errMsg){
        super(errMsg);
    }

    public InsufficientFundsException(Throwable err){
        super(err);
    }
}
