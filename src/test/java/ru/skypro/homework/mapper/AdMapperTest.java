package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class AdMapperTest {
    private AdMapper adMapper = new AdMapper();

    @Test
    public void testToAdDto() {
        Ad ad = new Ad();
        ad.setPk(1);
        User user = new User();
        user.setId(1);
        ad.setUser(user);
        ad.setImage("/test/image");
        ad.setPrice((int) 100.0);
        ad.setTitle("Test Ad");

        AdDto adDto = adMapper.toAdDto(ad);

        assertEquals(ad.getPk(), adDto.getPk());
        assertEquals(ad.getUser().getId(), adDto.getAuthor());
        assertEquals("/ads/1/image", adDto.getImage());
        assertEquals(ad.getPrice(), adDto.getPrice());
        assertEquals(ad.getTitle(), adDto.getTitle());
    }

    @Test
    public void testToAdsDto() {
        Ad ad1 = new Ad();
        ad1.setPk(1);
        User user1 = new User();
        user1.setId(1);
        ad1.setUser(user1);
        ad1.setImage("/test/image1");
        ad1.setPrice((int) 100.0);
        ad1.setTitle("Test Ad 1");

        Ad ad2 = new Ad();
        ad2.setPk(2);
        User user2 = new User();
        user2.setId(2);
        ad2.setUser(user2);
        ad2.setImage("/test/image2");
        ad2.setPrice((int) 200.0);
        ad2.setTitle("Test Ad 2");

        List<Ad> ads = Arrays.asList(ad1, ad2);

        AdsDto adsDto = adMapper.toAdsDto(ads);

        assertEquals(ads.size(), adsDto.getCount());
        assertEquals(ads.size(), adsDto.getResults().size());
        assertEquals(ad1.getPk(), adsDto.getResults().get(0).getPk());
        assertEquals(ad1.getTitle(), adsDto.getResults().get(0).getTitle());
        assertEquals(ad2.getPk(), adsDto.getResults().get(1).getPk());
        assertEquals(ad2.getTitle(), adsDto.getResults().get(1).getTitle());
    }

    @Test
    public void testToEntity() {
        CreateOrUpdateAdDto createOrUpdateAdDto = new CreateOrUpdateAdDto();
        createOrUpdateAdDto.setTitle("Test Title");
        createOrUpdateAdDto.setDescription("Test Description");
        createOrUpdateAdDto.setPrice((int) 150.0);

        Ad ad = adMapper.toEntity(createOrUpdateAdDto);

        assertEquals(createOrUpdateAdDto.getTitle(), ad.getTitle());
        assertEquals(createOrUpdateAdDto.getDescription(), ad.getDescription());
        assertEquals(createOrUpdateAdDto.getPrice(), ad.getPrice());
    }

    @Test
    public void testToExtendedAdDto() {
        Ad ad = new Ad();
        ad.setPk(1);
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPhone("12345");
        ad.setUser(user);
        ad.setDescription("Test Description");
        ad.setPrice((int) 100.0);
        ad.setTitle("Test Ad");

        ExtendedAdDto extendedAdDto = adMapper.toExtendedAdDto(ad);

        assertEquals(ad.getPk(), extendedAdDto.getPk());
        assertEquals(ad.getUser().getFirstName(), extendedAdDto.getAuthorFirstName());
        assertEquals(ad.getUser().getLastName(), extendedAdDto.getAuthorLastName());
        assertEquals(ad.getUser().getEmail(), extendedAdDto.getEmail());
        assertEquals(ad.getUser().getPhone(), extendedAdDto.getPhone());
        assertEquals("/ads/1/image", extendedAdDto.getImage());
        assertEquals(ad.getPrice(), extendedAdDto.getPrice());
        assertEquals(ad.getTitle(), extendedAdDto.getTitle());
    }
}