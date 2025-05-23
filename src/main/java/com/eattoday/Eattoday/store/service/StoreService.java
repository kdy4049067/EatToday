package com.eattoday.Eattoday.store.service;

import com.eattoday.Eattoday.store.dto.StoreDto;
import com.eattoday.Eattoday.store.entity.Store;
import com.eattoday.Eattoday.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public List<Store> index() {
        return storeRepository.findAll();
    }

    @Transactional
    public List<StoreDto> getBoardList() {
        List<Store> storeList = storeRepository.findAll();
        List<StoreDto> storeDtoList = new ArrayList<>();

        for (Store storeEntity : storeList) {
            StoreDto storeDto = StoreDto.builder()
                    .id(storeEntity.getId())
                    .category(storeEntity.getCategory())
                    .store_name(storeEntity.getStore_name())
                    .store_address(storeEntity.getStore_address())
                    .store_phone(storeEntity.getStore_phone())
                    .store_img(storeEntity.getStore_img())
                    .store_star(storeEntity.getStore_star())
                    .store_time(storeEntity.getStore_time())
                    .review_content(storeEntity.getReview_content())
                    .store_menu(storeEntity.getStore_menu())
                    .build();
            storeDtoList.add(storeDto);
        }
        return storeDtoList;
    }

    @Transactional
    public Page<Store> storePage(Pageable pageable) {
        Page<Store> storePage = storeRepository.findAll(pageable);
        return storePage;
    }

    @Transactional
    public Page<Store> allSearch(String keyword, Pageable pageable) {
        Page<Store> allSearch = storeRepository.findByAllContent(keyword, pageable);

        return allSearch;
    }

    @Transactional
    public Page<Store> filterByCategory(String category, Pageable pageable) {
        Page<Store> search = storeRepository.findByCategory(category, pageable);
        return search;
    }

    @Transactional
    public Page<Store> filterByStore_address(String address, Pageable pageable) {
        Page<Store> search2 = storeRepository.findByStore_addressContaining(address, pageable);

        return search2;
    }

    @Transactional
    public List<Store> getRandomStores(String category){
        List<Store> storeOfCategory = storeRepository.findStoreByCategory(category);

        if(storeOfCategory.size() <= 3){
            return storeOfCategory;
        }

        Collections.shuffle(storeOfCategory);
        return storeOfCategory.subList(0, 3);
    }

}
