package com.karkar.springboot.controller;

import com.karkar.springboot.common.Result;
import com.karkar.springboot.controller.request.BaseRequest;
import com.karkar.springboot.service.OperatorLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/log")
public class OperatorLoggerController {

    @Autowired
    private OperatorLoggerService service;

    @GetMapping("/page")
    public Result page(BaseRequest baseRequest) {
        return Result.success(service.page(baseRequest));
    }

}
