package com.goodshop.demo.service;

import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.UserRepository;
import com.goodshop.demo.user.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//jpa 모든 로직은 트랜잭션 안에서 실행되야함.
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {


//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }


    private final UserRepository userRepository;
    //생성자 인젝션
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    //회원 가입
    @Transactional
    public String join(User user){
        validateDuplicateUser(user);// 중복 회원 검증
        userRepository.save(user);

        return user.getUser_id();
    }

    //중복 회원 검증
    private void validateDuplicateUser(User user){
        List<User> findUsers = userRepository.findByName(user.getUser_name());
        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }


    //회원 전체 조회
    public List<User> findMember(){
        return userRepository.findAll();
    }

    //한 건만 조회
    public User findOne(String user_id){
        return userRepository.findOne(user_id);
    }

    //로그인
    public User loginForm(String userId, String userPasswd){
        return userRepository.findByLoginId(userId)
                .filter(m -> m.getUser_passwd().equals(userPasswd))
                .orElse(null);
    }

}
