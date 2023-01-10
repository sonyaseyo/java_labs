package com.example.laba4;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Laba4ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void incorrectData() {
        canonicalLine line = new canonicalLine(1, 2, 3, 8, 0, -15);
        assertFalse(line.valid);
    }

    @Test
    void correctData() {
        assertTrue(new canonicalLine(1, 0, 3, 8, -1, -15).valid);
    }

    @Test
    void checkParallel() {
        canonicalLine line1 = new canonicalLine(123, 18, 3, 6, 9, -15);
        canonicalLine line2 = new canonicalLine(48, 44, 3, 2, 3, -5);
        assertEquals(canonicalLine.relation.Parallel, line1.check(line2));
    }

    @Test
    void checkCoplIntersect() {
        canonicalLine line1 = new canonicalLine(1, 2, 3, 1, 4, 2);
        canonicalLine line2 = new canonicalLine(1, 2, 3, 2, 2, 1);
        assertEquals(canonicalLine.relation.Copl_intersect, line1.check(line2));
    }


    @Test
    void checkError() {
        canonicalLine line1 = new canonicalLine(3, 2, 8, 0, 2, -1);
        canonicalLine line2 = new canonicalLine(6, 0, 9, -3, 1, 0);
        assertEquals(canonicalLine.relation.Error, line1.check(line2));
    }

    @Test
    void checkFleeting() {
        canonicalLine line1 = new canonicalLine(3, 2, 8, 15, 2, -1);
        canonicalLine line2 = new canonicalLine(6, 0, 9, -3, 1, 4);
        assertEquals(canonicalLine.relation.Fleeting, line1.check(line2));
    }


}
