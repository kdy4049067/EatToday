package com.eattoday.Eattoday.like.controller;

import com.eattoday.Eattoday.like.dto.LikeDto;
import com.eattoday.Eattoday.store.repository.StoreRepository;
import com.eattoday.Eattoday.like.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j //로그를 찍기 위한 어노테이션
@RestController

public class LikeApiController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("api/{id}/{storeId}/likes")
    public ResponseEntity<LikeDto> Likes(@PathVariable Long id, @PathVariable Long storeId){
        LikeDto likes = likeService.storeLike(id, storeId);
        return ResponseEntity.status(HttpStatus.OK).body(likes);
    }

    @PostMapping("api/{id}/{storeId}/likes")
    public ResponseEntity<LikeDto> createLike(@PathVariable Long id, @PathVariable Long storeId
            ,@RequestBody LikeDto likeDto){
        LikeDto likeDto1 = likeService.create(storeId, id, likeDto);
        return ResponseEntity.status(HttpStatus.OK).body(likeDto1);
    }

    @DeleteMapping("api/{id}/{storeId}/likes")
    public ResponseEntity<LikeDto> deleteLike(@PathVariable Long id, @PathVariable Long storeId){
        LikeDto likeDto = likeService.deleteLike(id, storeId);
        log.info("id" + id);
        return ResponseEntity.status(HttpStatus.OK).body(likeDto);
    }

}
