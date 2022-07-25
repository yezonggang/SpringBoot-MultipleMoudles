package com.example.security;

import java.util.concurrent.Callable;

public class CallableAnsyTask implements Callable<Long> {

    private int[] _arr;
    
    public CallableAnsyTask(int[] arr) {
        _arr = arr;
    }
    
    @Override
    public Long call() throws Exception {
        long result = 0;
        for (int i = 0; i < _arr.length; i++) {
            result += _arr[i];
        }
        
        return result;
    }
}