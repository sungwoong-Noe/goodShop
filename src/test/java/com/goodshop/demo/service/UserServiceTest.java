package com.goodshop.demo.service;

import com.goodshop.demo.domain.user.User;
import com.goodshop.demo.repository.UserRepository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입()throws Exception{

        //given
        User user = new User();
        user.setUser_name("kim");
        user.setUser_id("woong423");

        //when
        String savedId = userService.join(user);


        //then
        em.flush();
        Assert.assertEquals(user, userRepository.findOne(savedId));
    }

    @Test
    public void 중복회원_예외()throws Exception{

        //given
        User user1 = new User();
        user1.setUser_id("woong");
        user1.setUser_name("noe");

        User user2 = new User();
        user2.setUser_id("woong2");
        user2.setUser_name("noe");

        //when  : 예외가 발생해야 한다.(중복)
        userService.join(user1);



        //then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, ()-> userService.join(user2));
        Assert.assertEquals("이미 존재하는 회원입니다", thrown.getMessage());

    }

}