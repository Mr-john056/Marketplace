package ru.skypro.homework.entity;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.Role;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void testGettersAndSetters() {
        User user = new User();

        user.setId(1);
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhone("1234567890");
        user.setRole(Role.ADMIN);
        user.setImage("/images/user.jpg");

        assertEquals(1, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("1234567890", user.getPhone());
        assertEquals(Role.ADMIN, user.getRole());
        assertEquals("/images/user.jpg", user.getImage());
    }


}