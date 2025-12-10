package com.rubypaper.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.constant.ItemCategory;
import com.rubypaper.entity.Item;
import com.rubypaper.repository.ItemRepository;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 데이터 생성")
    public void createItemData() {
        createItem("핑크 스트라이프 삭스", 12000, ItemCategory.WOMEN, "regular", "pink", "white", null, "w1.jpg");
        createItem("화이트 베이직 삭스", 5000, ItemCategory.WOMEN, "short", "white", null, null, "w2.jpg");
        createItem("블랙 정장 양말", 15000, ItemCategory.MEN, "regular", "black", null, null, "m1.jpg");
        createItem("네이비 스포츠 삭스", 8000, ItemCategory.MEN, "sports", "navy", "gray", null, "m2.jpg");
        createItem("무지개 곰돌이 삭스", 6000, ItemCategory.KIDS, "color", "yellow", "red", "blue", "k1.jpg");
    }

    private void createItem(String name, int price, ItemCategory category, String type, 
                            String c1, String c2, String c3, String imgName) {
        Item item = new Item();
        item.setItemNm(name);
        item.setPrice(price);
        item.setItemCategory(category);
        item.setItemType(type);
        item.setColor1(c1);
        item.setColor2(c2);
        item.setColor3(c3);
        item.setImgName(imgName);
        itemRepository.save(item);
    }
}

