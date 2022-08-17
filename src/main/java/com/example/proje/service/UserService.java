package com.example.proje.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.proje.repository.UserRepository;
import com.example.proje.model.entity.User;
import com.example.proje.model.request.user.UserAddDto;
import com.example.proje.model.request.user.UserChangePasswordDto;
import com.example.proje.model.request.user.UserLoginRequestDto;
import com.example.proje.model.request.user.UserUpdateDto;
import com.example.proje.model.response.user.UserListDto;
import com.example.proje.model.response.user.UserLoginResponseDto;
import com.example.proje.security.jwt.JwtUtils;
import com.example.proje.utilities.converters.EntityDtoConverter;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.ErrorDataResult;
import com.example.proje.utilities.results.ErrorResult;
import com.example.proje.utilities.results.Result;
import com.example.proje.utilities.results.SuccessDataResult;
import com.example.proje.utilities.results.SuccessResult;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    private EntityDtoConverter<UserAddDto, User> userAddRequestDtoToUserConverter = new EntityDtoConverter(User.class);

    private EntityDtoConverter<UserUpdateDto, User> userUpdateRequestDtoToUserConverter = new EntityDtoConverter(User.class);


    public DataResult<List<UserListDto>> getAllUser() {
        try {
            List<UserListDto> userListResponseDtos = userRepository.findByUserListDto();
            return new SuccessDataResult<List<UserListDto>>(userListResponseDtos);
        } catch (Exception ex) {
            return new ErrorDataResult<List<UserListDto>>("Bilinmeyen Bir Hata Oluştu");
        }
    }


    public Result singup(UserAddDto userAddRequestDto) {
        try {
            if (userRepository.existsByEmail(userAddRequestDto.getEmail())) {
                return new ErrorResult("Bu Email Kullanılıyor");
            } else {
                User newUser = userAddRequestDtoToUserConverter.convert(userAddRequestDto);
                newUser.setPassword(passwordEncoder.encode("123456789"));
                newUser.setIsPasswordChanged(true);
                userRepository.save(newUser);
                return new SuccessResult();
            }
        } catch (Exception ex) {
            return new ErrorResult("Bilinmeyen Bir Hata Oluştu");
        }
    }


    public DataResult<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto) {

        try {
            User user = userRepository.findByEmail(userLoginRequestDto.getEmail());
            if (Objects.isNull(user)) {
                return new ErrorDataResult<UserLoginResponseDto>("Kullanıcı Bulunamadı");
            }

            try {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword()));
            } catch (Exception ex) {
                return new ErrorDataResult<UserLoginResponseDto>("Giriş yapmadan önce şifrenizi değiştiriniz.");
            }

            String jwt = jwtUtils.generateJwtToken(user);
            UserListDto userListResponseDto = userRepository.findByEmailToUserListDto(userLoginRequestDto.getEmail());

            UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto(
                    userListResponseDto.getUserId(),
                    userListResponseDto.getEmail(),
                    userListResponseDto.getFirstName(),
                    userListResponseDto.getLastName(),
                    userListResponseDto.getPhoneNumber(),
                    jwt
            );

            userLoginResponseDto.setJwt(jwt);
            return new SuccessDataResult<UserLoginResponseDto>(userLoginResponseDto);
        } catch (Exception ex) {
            return new ErrorDataResult<UserLoginResponseDto>("Bilinmeyen Bir Hata Oluştu : " + ex);
        }
    }


    public Result changePassword(UserChangePasswordDto userChangePasswordRequestDto) {
        try {
            User user = userRepository.findByEmail(userChangePasswordRequestDto.getEmail());

            if (Objects.isNull(user)) {
                return new ErrorResult("Kullanıcı Bulunamadı");
            } else if (!user.getIsPasswordChanged()) {
                return new ErrorResult("Şifre Değiştirilemez");
            }
            user.setPassword(passwordEncoder.encode(userChangePasswordRequestDto.getPassword()));
            user.setIsPasswordChanged(false);
            userRepository.save(user);
            return new SuccessResult();
        } catch (Exception ex) {
            return new ErrorResult("Bilinmeyen Bir Hata Oluştu");
        }
    }


    public Result deleteUser(Integer userId) {
        try {
            User user = userRepository.findByUserId(userId);
            if (Objects.isNull(user)) {
                return new ErrorResult("Kullanıcı Bulunamadı");
            }
            userRepository.deleteById(userId);
            return new SuccessResult("Kullanıcı Silindi");
        } catch (Exception ex) {
            return new ErrorResult("Bilinmeyen Bir Hata Oluştu");
        }
    }


    public Result updateUser(Integer userId, UserUpdateDto userUpdateRequestDto) {
        try {
            User oldUser = userRepository.findByUserId(userId);

            if (Objects.isNull(oldUser)) {
                return new ErrorResult("Kullanıcı Bulunamadı");
            }
            User findUser = userRepository.findByEmail(userUpdateRequestDto.getEmail());

            if (!Objects.isNull(findUser)) {
                if (findUser.getUserId() != userId) {
                    return new ErrorResult("Bu Email Kullanılıyor");
                }
            }
            User updateUser = userUpdateRequestDtoToUserConverter.convert(userUpdateRequestDto);
            updateUser.setUserId(userId);
            updateUser.setPassword(oldUser.getPassword());
            updateUser.setIsPasswordChanged(oldUser.getIsPasswordChanged());
            userRepository.save(updateUser);
            return new SuccessResult();
        } catch (Exception ex) {
            return new ErrorResult("Bilinmeyen Bir Hata Oluştu");
        }
    }
}
