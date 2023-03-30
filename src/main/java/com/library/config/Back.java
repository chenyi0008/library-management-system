package com.library.config;

import com.library.domain.Code;
import com.library.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/back")
public class Back {
    @GetMapping
    @PostMapping
    public Result back(){
        return new Result(Code.BACK,"请登录账号");
    }
}
