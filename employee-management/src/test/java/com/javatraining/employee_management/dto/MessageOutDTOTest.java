package com.javatraining.employee_management.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MessageOutDTOTest {
    @Test
    void testGetterSetter() {
        MessageOutDto dto = new MessageOutDto();
        dto.setMessage("Success");
        assertEquals("Success", dto.getMessage());
    }

    @Test
    void testParameterizedConstructor() {
        MessageOutDto dto = new MessageOutDto("Created");
        assertEquals("Created", dto.getMessage());
    }

    @Test
    void testNoArgsConstructor() {
        MessageOutDto dto = new MessageOutDto();
        assertNotNull(dto);
    }
}
