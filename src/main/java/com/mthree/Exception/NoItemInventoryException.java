package com.mthree.Exception;

public class NoItemInventoryException extends Exception{
    public NoItemInventoryException(String errMsg, Throwable err){
        super(errMsg, err);
    }

    public NoItemInventoryException(String errMsg){
        super(errMsg);
    }

    public NoItemInventoryException(Throwable err){
        super(err);
    }
}
