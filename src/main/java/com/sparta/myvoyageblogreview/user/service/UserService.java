package com.sparta.myvoyageblogreview.user.service;

import com.sparta.myvoyageblogreview.user.dto.SignupRequestDto;
import com.sparta.myvoyageblogreview.user.entity.User;
import com.sparta.myvoyageblogreview.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);

        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        if (!requestDto.getPassword().equals(requestDto.getPasswordCheck())) {
            throw new IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        if (requestDto.getPassword().contains(username)) {
            throw new IllegalArgumentException("비밀번호에는 닉네임과 같은 값이 포함될 수 없습니다.");
        }

        // 사용자 등록
         User user = new User(username, password);
        userRepository.save(user);
    }
}