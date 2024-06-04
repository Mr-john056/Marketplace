package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdDtoTest {

    @Test
    public void testGetSetAuthor() {
        AdDto adDto = new AdDto();
        adDto.setAuthor(123);
        assertEquals(123, adDto.getAuthor());
    }

    @Test
    public void testGetSetImage() {
        AdDto adDto = new AdDto();
        adDto.setImage("example.jpg");
        assertEquals("example.jpg", adDto.getImage());
    }

    @Test
    public void testGetSetPk() {
        AdDto adDto = new AdDto();
        adDto.setPk(456);
        assertEquals(456, adDto.getPk());
    }

    @Test
    public void testGetSetPrice() {
        AdDto adDto = new AdDto();
        adDto.setPrice(789);
        assertEquals(789, adDto.getPrice());
    }

    @Test
    public void testGetSetTitle() {
        AdDto adDto = new AdDto();
        adDto.setTitle("Example Ad");
        assertEquals("Example Ad", adDto.getTitle());
    }

}