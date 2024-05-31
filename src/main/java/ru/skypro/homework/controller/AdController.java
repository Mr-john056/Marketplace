package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.service.AdService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {
    private final AdService adService;


    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        return ResponseEntity.ok(adService.getAll());
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAdsMe(Authentication authentication) {
        AdsDto dto = adService.getMyAds(authentication.getName());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok(adService.getExtendedAd(id));
    }

    @GetMapping(value = "{id}/image", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) throws IOException {
        return ResponseEntity.ok(adService.getImage(id));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<AdDto> addAd(@RequestPart(value = "properties") CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestPart(value = "image") MultipartFile image,
                                       Authentication authentication) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(adService.addAd(createOrUpdateAdDto, image, authentication));
    }

    @PreAuthorize("hasAuthority('ADMIN') or @adServiceImpl.getAd(#id).user.email == authentication.principal.username")
    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable Integer id,
                                          @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto,
                                          Authentication authentication) {
        return ResponseEntity.ok(adService.updateAd(id, createOrUpdateAdDto, authentication));
    }

    @PreAuthorize("hasAuthority('ADMIN') or @adServiceImpl.getAd(#id).user.email == authentication.principal.username")
    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> updateAdImage(@PathVariable Integer id,
                                                @RequestParam MultipartFile image,
                                                Authentication authentication) throws IOException {

        return ResponseEntity.ok(adService.updateAdImage(id, image, authentication));

    }

    @PreAuthorize("hasAuthority('ADMIN') or @adServiceImpl.getAd(#id).user.email == authentication.principal.username")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable Integer id, Authentication authentication) {
        try {
            adService.deleteAd(id, authentication);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
