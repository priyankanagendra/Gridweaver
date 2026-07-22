package com.gridweaver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FirstJUnitTest {

    @Test
    void testAddition() {

        int expected = 5;
        int actual = 2 + 3;

        assertEquals(expected, actual);
    }
}