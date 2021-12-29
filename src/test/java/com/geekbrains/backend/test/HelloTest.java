package com.geekbrains.backend.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloTest {

    @Test
    void foo(){
        Hello hello = new Hello();
        Assertions.assertEquals(3, hello.sum(2,1));
    }
}