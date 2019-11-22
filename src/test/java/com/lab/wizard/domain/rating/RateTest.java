package com.lab.wizard.domain.rating;

import org.junit.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RateTest {

    @Test
    public void testGetterWithNoArgs() {
        //given
        Rate rate = new Rate();
        //when
        //then
        assertNotNull(rate);
        assertNull(rate.getId());
        assertNull(rate.getName());
        assertNull(rate.getComment());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        Rate rate = new Rate(1L, "name", 3L, "comment");

        //when
        //then
        assertEquals(1L, rate.getId().longValue());
        assertEquals("name", rate.getName());
        assertEquals("comment", rate.getComment());
    }
}