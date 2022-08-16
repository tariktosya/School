package com.example.proje.business.abstracts;


import java.util.List;

import com.example.proje.core.entities.dtos.request.UserAddDto;
import com.example.proje.core.entities.dtos.request.UserChangePasswordDto;
import com.example.proje.core.entities.dtos.request.UserLoginRequestDto;
import com.example.proje.core.entities.dtos.request.UserUpdateDto;
import com.example.proje.core.entities.dtos.response.UserListDto;
import com.example.proje.core.entities.dtos.response.UserLoginResponseDto;
import com.example.proje.core.utilities.results.DataResult;
import com.example.proje.core.utilities.results.Result;

public interface UserService {

    DataResult<List<UserListDto>> getAllUser();

    Result singup(UserAddDto userAddRequestDto);

    DataResult<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto);

    Result changePassword(UserChangePasswordDto userChangePasswordRequestDto);

    Result deleteUser(Integer userId);

    Result updateUser(Integer userId, UserUpdateDto userUpdateRequestDto);
}
