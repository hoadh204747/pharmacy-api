package com.howie.pharmacy.pharmacy_store.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.howie.pharmacy.pharmacy_store.dto.auth.AuthResponseDto;
import com.howie.pharmacy.pharmacy_store.dto.auth.LoginRequestDto;
import com.howie.pharmacy.pharmacy_store.dto.auth.RegisterRequestDto;
import com.howie.pharmacy.pharmacy_store.dto.user.UserDto;
import com.howie.pharmacy.pharmacy_store.entity.User;
import com.howie.pharmacy.pharmacy_store.exception.AppExceptions.BadRequestException;
import com.howie.pharmacy.pharmacy_store.mapper.UserMapper;
import com.howie.pharmacy.pharmacy_store.repository.UserRepository;
import com.howie.pharmacy.pharmacy_store.utils.JwtUtils;

@Service
public class AuthServiceImpl {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;

    @Transactional
    public UserDto register(RegisterRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email is already in use");
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new BadRequestException("Phone number is already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(User.ERole.CUSTOMER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    public AuthResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(() -> new BadRequestException("Invalid phone number or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid phone number or password");
        }

        String accessToken = jwtUtils.generateToken(user);
        String refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        return new AuthResponseDto(accessToken, refreshToken, userMapper.toDto(user));
    }
}
