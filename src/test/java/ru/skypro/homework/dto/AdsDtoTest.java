package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdsDtoTest {
    @Test
    public void testGetCount() {
        AdsDto adsDto = new AdsDto();
        adsDto.setCount(5);
        assertEquals(5, adsDto.getCount());
    }

    @Test
    public void testGetResults() {
        AdsDto adsDto = new AdsDto();
        List<AdDto> adDtoList = new ArrayList<>();
        AdDto adDto1 = new AdDto();
        AdDto adDto2 = new AdDto();
        adDtoList.add(adDto1);
        adDtoList.add(adDto2);
        adsDto.setResults(adDtoList);
        assertEquals(adDtoList, adsDto.getResults());
    }

}