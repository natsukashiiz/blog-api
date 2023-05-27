package com.vv2dev.restfulapi;

import com.vv2dev.blog.entity.Blog;
import com.vv2dev.blog.entity.Social;
import com.vv2dev.blog.entity.User;
import com.vv2dev.blog.exception.BaseException;
import com.vv2dev.blog.service.BlogService;
import com.vv2dev.blog.service.SocialService;
import com.vv2dev.blog.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUserService {

    @Autowired
    private UserService userService;
    @Autowired
    private SocialService socialService;
    @Autowired
    private BlogService blogService;

    @Order(1)
    @Test
    void testCreate() throws BaseException {
        User user = userService.create(
                TestData.email,
                TestData.password,
                TestData.name
        );

        // check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());

        // check equals
        Assertions.assertEquals(TestData.email, user.getEmail());
        boolean isMatched = userService.matchPassword(TestData.password, user.getPassword());
        Assertions.assertTrue(isMatched);
        Assertions.assertEquals(TestData.name, user.getName());
    }

    @Order(2)
    @Test
    void testUpdate() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        user.setName(TestUpdateData.name);

        User updateUser = userService.updateName(user.getId(), TestUpdateData.name);

        Assertions.assertNotNull(updateUser);
        Assertions.assertEquals(TestUpdateData.name, updateUser.getName());
    }

    @Order(3)
    @Test
    void testCreateSocial() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        Social social = user.getSocial();
        Assertions.assertNull(social);

        social = socialService.create(
                user,
                SocialTestCreateData.facebook,
                SocialTestCreateData.line,
                SocialTestCreateData.instagram,
                SocialTestCreateData.tiktok
        );

        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());
    }

    @Order(4)
    @Test
    void testCreateBlog() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        List<Blog> blogs = user.getBlogs();
        Assertions.assertTrue(blogs.isEmpty());

        createAddress(user, BlogTestCreateData.title, BlogTestCreateData.content);
        createAddress(user, BlogTestCreateData2.title, BlogTestCreateData2.content);
    }

    public void createAddress(User user, String title, String content) throws BaseException {
        Blog blog = blogService.create(
                user,
                title,
                content
        );

        Assertions.assertNotNull(blog);
        Assertions.assertEquals(title, blog.getTitle());
        Assertions.assertEquals(content, blog.getContent());
    }

    @Order(9)
    @Test
    void testDelete() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        // check social
        Social social = user.getSocial();
        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());

        // check address
        List<Blog> blogs = user.getBlogs();
        Assertions.assertFalse(blogs.isEmpty());
        Assertions.assertEquals(2, blogs.size());

        userService.deleteById(user.getId());

        Optional<User> optDelete = userService.findByEmail(TestData.email);
        Assertions.assertTrue(optDelete.isEmpty());
    }

    interface TestData {
        String email = "dd@mail.com";
        String password = "dd";
        String name = "dd";
    }

    interface SocialTestCreateData {
        String facebook = "facebook xD";
        String line = "";
        String instagram = "";
        String tiktok = "";
    }

    interface BlogTestCreateData {
        String title = "TEST1";
        String content = "TEST1";
    }

    interface BlogTestCreateData2 {
        String title = "TEST2";
        String content = "TEST2";
    }

    interface TestUpdateData {
        String name = "ddXd";
    }
}
