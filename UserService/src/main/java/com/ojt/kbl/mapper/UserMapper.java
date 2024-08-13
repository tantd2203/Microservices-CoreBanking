package com.ojt.kbl.mapper;

import com.ojt.kbl.entity.UserEntity;
import com.ojt.kbl.model.User;
import org.springframework.beans.BeanUtils;

public class UserMapper extends BaseMapper<UserEntity, User> {

    @Override
    public UserEntity convertToEntity(User dto, Object... args) {
        UserEntity userEntity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, userEntity);
        }
        return userEntity;
    }

    @Override
    public User convertToDto(UserEntity entity, Object... args) {
        User user = new User();
        if (entity != null) {
            BeanUtils.copyProperties(entity, user);
        }
        return user;
    }
}
