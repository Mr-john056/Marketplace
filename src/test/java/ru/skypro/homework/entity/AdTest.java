package ru.skypro.homework.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdTest {
    @Test
    public void testEqualsAndHashCode() {
        Ad ad1 = new Ad();
        Ad ad2 = new Ad();

        ad1.setPk(1);
        ad2.setPk(1);

        assertEquals(ad1, ad2);
        assertEquals(ad1.hashCode(), ad2.hashCode());
    }

    @Test
    public void testGettersAndSetters() {
        Ad ad = new Ad();

        ad.setPk(1);
        ad.setImage("path/to/image.jpg");
        ad.setPrice(100);
        ad.setTitle("Test ad");
        ad.setDescription("Description of test ad");

        assertEquals(1, ad.getPk());
        assertEquals("path/to/image.jpg", ad.getImage());
        assertEquals(100, ad.getPrice());
        assertEquals("Test ad", ad.getTitle());
        assertEquals("Description of test ad", ad.getDescription());
    }

}