package com.easyapi.test.controller;



import com.easyapi.test.model.ApiResult;
import com.easyapi.test.model.User;
import com.easyapi.test.model.UserQuery;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/test")
public class TestController {
    /**
     * Retrieve user list through pagination
     * @param userQuery 用户分页查询参数
     * @return 分页用户数据
     */
    @PostMapping("/page")
    public ApiResult<PageInfo<User>> pageList(@RequestBody UserQuery userQuery){
        User user = new User();
        user.setId(1L);
        user.setName("zhang");
        PageInfo<User> pageInfo = new PageInfo<>(Collections.singletonList(user));
        return ApiResult.success(pageInfo);
    }

    /**
     * 获取用户数据
     * @param userId
     * @return 单个用户数据
     */
    @GetMapping("/getUser")
    public ApiResult<User> getUser(@RequestParam Long userId){
        User user = new User();
        user.setId(1L);
        user.setName("zhang");
        return ApiResult.success(user);
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    public ApiResult<Boolean> updateUser(@RequestBody User user){
        return ApiResult.success(Boolean.TRUE);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/delUser")
    public ApiResult<Boolean> delUser(@RequestParam Long userId){
        return ApiResult.success(Boolean.TRUE);
    }

}
