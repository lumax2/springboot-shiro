package com.yoozoo.mapper;


import com.yoozoo.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Hao on 2018/3/25.
 */
public interface UserMapper {

    User findByName(String username);

    List<String> findPermissionByUserId(String userId);

    void updatePassword(User user);
}
