package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdsController {
    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(new AdsDto());
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAd(@RequestPart(name = "image") MultipartFile image,
                                   @RequestPart(name = "properties") CreateOrUpdateAdDto properties) throws IOException {
        return ResponseEntity.ok(new AdDto());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAds(@PathVariable int id) {
        return ResponseEntity.ok(new ExtendedAdDto());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<AdDto> removeAd(@PathVariable int id) throws IOException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAds(@PathVariable int id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        return ResponseEntity.ok(new AdDto());
    }
    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe() {
        return ResponseEntity.ok(new AdsDto());
    }
    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> updateAdImage(@PathVariable Integer id,
                                                @RequestParam MultipartFile image) throws IOException {

        return (ResponseEntity<byte[]>) ResponseEntity.ok();

    }
}
