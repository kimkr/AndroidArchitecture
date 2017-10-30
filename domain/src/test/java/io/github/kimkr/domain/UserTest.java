package io.github.kimkr.domain;

import org.junit.Before;
import org.junit.Test;

import io.github.kimkr.domain.entity.User;
import io.github.kimkr.domain.model.user.User;

public class UserTest {

    User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void testUserConstructorHappyCase() {
    }
}
