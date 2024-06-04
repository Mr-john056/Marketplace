package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrUpdateAdDtoTest {
    @Test
    public void testGetSetTitle() {
        CreateOrUpdateAdDto ad = new CreateOrUpdateAdDto();
        ad.setTitle("Test Title");
        assertEquals("Test Title", ad.getTitle());
    }

    @Test
    public void testGetSetPrice() {
        CreateOrUpdateAdDto ad = new CreateOrUpdateAdDto();
        ad.setPrice(100);
        assertEquals(100, ad.getPrice());
    }

    @Test
    public void testGetSetDescription() {
        CreateOrUpdateAdDto ad = new CreateOrUpdateAdDto();
        ad.setDescription("Test Description");
        assertEquals("Test Description", ad.getDescription());
    }

}