package com.example.proje.business.abstracts;


import java.util.List;

import com.example.proje.model.request.user.UserAddDto;
import com.example.proje.model.request.user.UserChangePasswordDto;
import com.example.proje.model.request.user.UserLoginRequestDto;
import com.example.proje.model.request.user.UserUpdateDto;
import com.example.proje.model.response.user.UserListDto;
import com.example.proje.model.response.user.UserLoginResponseDto;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.Result;

public interface UserService {

    DataResult<List<UserListDto>> getAllUser();

    Result singup(UserAddDto userAddRequestDto);

    DataResult<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto);

    Result changePassword(UserChangePasswordDto userChangePasswordRequestDto);

    Result deleteUser(Integer userId);

    Result updateUser(Integer userId, UserUpdateDto userUpdateRequestDto);
}
