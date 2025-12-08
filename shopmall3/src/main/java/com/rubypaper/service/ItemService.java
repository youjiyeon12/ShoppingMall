package com.rubypaper.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rubypaper.entity.Item;
import com.rubypaper.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final FileService fileService;

    public void saveItem(Item item, MultipartFile imageFile) throws Exception {

        // 이미지 파일명 저장
        String savedFileName = fileService.saveImage(imageFile, item.getItemCategory());

        item.setImgName(savedFileName);

        // DB 저장
        itemRepository.save(item);
    }
}