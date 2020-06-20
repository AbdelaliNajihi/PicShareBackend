package com.pic.share.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.pic.share.dto.UserDto;
import com.pic.share.model.UserApp;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDto userToUserDto(UserApp user);
	List<UserDto> usersToUsersDto(List<UserApp> users);
}
