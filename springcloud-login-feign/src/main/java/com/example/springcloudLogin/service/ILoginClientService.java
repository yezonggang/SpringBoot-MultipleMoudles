package com.example.springcloudLogin.service;

import response.ResponseData;

public interface ILoginClientService {
    ResponseData checkLogin(String userName);
}
