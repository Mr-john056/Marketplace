package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedAdDtoTest {
    @Test
    public void testGetPk() {
        ExtendedAdDto ad = new ExtendedAdDto();
        ad.setPk(1);
        assertEquals(1, ad.getPk());
    }

    @Test
    public void testGetAuthorFirstName() {
        ExtendedAdDto ad = new ExtendedAdDto();
        ad.setAuthorFirstName("John");
        assertEquals("John", ad.getAuthorFirstName());
    }

    @Test
    public void testGetAuthorLastName() {
        ExtendedAdDto ad = new ExtendedAdDto();
        ad.setAuthorLastName("Doe");
        assertEquals("Doe", ad.getAuthorLastName());
    }

    @Test
    public void testGetDescription() {
        ExtendedAdDto ad = new ExtendedAdDto();
        ad.setDescription("Description");
        assertEquals("Description", ad.getDescription());
    }

    @Test
    public void testGetEmail() {
        ExtendedAdDto ad = new ExtendedAdDto();
        ad.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", ad.getEmail());
    }

    @Test
    public void testGetImage() {
        ExtendedAdDto ad = new ExtendedAdDto();
        ad.setImage("image.jpg");
        assertEquals("image.jpg", ad.getImage());
    }

    @Test
    public void testGetPhone() {
        ExtendedAdDto ad = new ExtendedAdDto();
        ad.setPhone("123456789");
        assertEquals("123456789", ad.getPhone());
    }

    @Test
    public void testGetPrice() {
        ExtendedAdDto ad = new ExtendedAdDto();
        ad.setPrice(100);
        assertEquals(100, ad.getPrice());
    }

    @Test
    public void testGetTitle() {
        ExtendedAdDto ad = new ExtendedAdDto();
        ad.setTitle("Title");
        assertEquals("Title", ad.getTitle());
    }

}