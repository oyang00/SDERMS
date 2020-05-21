package com.ouyang.ctrl;

import com.ouyang.base.wrap.WrapParams;
import com.ouyang.valid.aspect.Param;
import com.ouyang.valid.aspect.ParamValidation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCtrl {

    @PostMapping("/test")
    @ParamValidation(@Param("name"))
    public void test(WrapParams wrapParams) {
        System.out.println(wrapParams);
    }
}
