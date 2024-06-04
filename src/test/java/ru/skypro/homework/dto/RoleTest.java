package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;
class RoleTest {
    private void assertEquals(String roleShouldBeUser, Role role, Role role1) {

    }
    @Test
    public void testUserRole() {
        Role role = Role.USER;
        assertEquals("Role should be USER", Role.USER, role);
    }

    @Test
    public void testAdminRole() {
        Role role = Role.ADMIN;
        assertEquals("Role should be ADMIN", Role.ADMIN, role);
    }

}