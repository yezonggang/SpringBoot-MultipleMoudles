package com.security.sys.service.common;

import com.security.sys.entity.current.SearchParam;

import javax.servlet.http.HttpServletResponse;

public interface CommonService {
    void search(SearchParam param, HttpServletResponse response);
}
