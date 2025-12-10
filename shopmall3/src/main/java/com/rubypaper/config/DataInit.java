package com.rubypaper.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rubypaper.constant.ItemCategory;
import com.rubypaper.entity.Item;
import com.rubypaper.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

	private final ItemRepository itemRepository;

	@Override
	public void run(String... args) throws Exception {
		if (itemRepository.count() == 0) {
			
			// ==========================================
            // [WOMEN] 여성 상품 데이터 (파일명 기준 정렬)
            // ==========================================

            // 1. 레귤러 삭스- itemType: "regular"
            createItem("우먼즈 레귤러 삭스 3P (기본)", 9900, ItemCategory.WOMEN, "regular", "white", "gray", "black", "레귤러삭스_삭스3P.jpg");
            createItem("우먼즈 레귤러 삭스 3P (레이스)", 9900, ItemCategory.WOMEN, "regular", "white", "pink", null, "레귤러삭스_삭스3P(레이스).jpg");
            createItem("우먼즈 레귤러 삭스 3P (립라인)", 9900, ItemCategory.WOMEN, "regular", "beige", "brown", null, "레귤러삭스_삭스3P(립라인).jpg");
            createItem("우먼즈 레귤러 삭스 3P (스트라이프)", 9900, ItemCategory.WOMEN, "regular", "navy", "white", null, "레귤러삭스_삭스3P(스트라이프).jpg");
            createItem("우먼즈 레귤러 삭스 3P (와이드립)", 9900, ItemCategory.WOMEN, "regular", "gray", "charcoal", null, "레귤러삭스_삭스3P(와이드립).jpg");
            createItem("우먼즈 레귤러 삭스 3P (크루시어)", 9900, ItemCategory.WOMEN, "regular", "white", "ivory", null, "레귤러삭스_삭스3P(크루시어).jpg");
            createItem("우먼즈 레귤러 삭스 3P (크무멜로우)", 9900, ItemCategory.WOMEN, "regular", "pink", "beige", null, "레귤러삭스_삭스3P(크무멜로우).jpg");
            createItem("우먼즈 슬라우치 삭스 3P", 9900, ItemCategory.WOMEN, "regular", "white", "gray", null, "레귤러삭스_슬라우치삭스3P.jpg");
            createItem("우먼즈 캐릭터 삭스 (헬로키티)", 5900, ItemCategory.WOMEN, "regular", "white", "red", null, "레귤러삭스_헬로키티.jpg");
            createItem("우먼즈 히트텍 삭스 (스노우)", 12900, ItemCategory.WOMEN, "regular", "navy", "white", null, "레귤러삭스_히트텍삭스(스노우).jpg");
            createItem("우먼즈 히트텍 삭스 (슬라우치)", 12900, ItemCategory.WOMEN, "regular", "gray", "black", null, "레귤러삭스_히트텍삭스(슬라우치).jpg");
            createItem("우먼즈 히트텍 삭스 (케이블)", 12900, ItemCategory.WOMEN, "regular", "red", "gray", null, "레귤러삭스_히트텍삭스(케이블).jpg");
            createItem("우먼즈 히트텍 삭스 (페어아일)", 12900, ItemCategory.WOMEN, "regular", "beige", "brown", null, "레귤러삭스_히트텍삭스(페어아일).jpg");

            // 2. 쇼트 삭스- itemType: "short"
            createItem("우먼즈 쇼트 삭스 (파일라인)", 4900, ItemCategory.WOMEN, "short", "white", "blue", null, "쇼트삭스_(파일라인).jpg");
            createItem("우먼즈 쇼트 삭스 3P (기본)", 8900, ItemCategory.WOMEN, "short", "white", "gray", "black", "쇼트삭스_쇼트삭스3P.jpg");
            createItem("우먼즈 쇼트 삭스 3P (립라인)", 8900, ItemCategory.WOMEN, "short", "pink", "white", null, "쇼트삭스_쇼트삭스3P(립라인).jpg");
            createItem("우먼즈 쇼트 삭스 3P (스트라이프)", 8900, ItemCategory.WOMEN, "short", "navy", "white", null, "쇼트삭스_쇼트삭스3P(스트라이프).jpg");
            createItem("우먼즈 쇼트 삭스 3P (플라워)", 8900, ItemCategory.WOMEN, "short", "yellow", "white", null, "쇼트삭스_쇼트삭스3P(플라워).jpg");
            createItem("우먼즈 히트텍 쇼트 삭스", 6900, ItemCategory.WOMEN, "short", "black", "gray", null, "쇼트삭스_히트텍쇼트삭스.jpg");


            // 3. 컬러 삭스 (단색) - itemType: "color"
            createItem("우먼즈 컬러 삭스 (갈색)", 3900, ItemCategory.WOMEN, "color", "brown", null, null, "컬러삭스갈색.jpg");
            createItem("우먼즈 컬러 삭스 (검정색)", 3900, ItemCategory.WOMEN, "color", "black", null, null, "컬러삭스검정색.jpg");
            createItem("우먼즈 컬러 삭스 (남색)", 3900, ItemCategory.WOMEN, "color", "navy", null, null, "컬러삭스남색.jpg");
            createItem("우먼즈 컬러 삭스 (민트색)", 3900, ItemCategory.WOMEN, "color", "mint", "green", null, "컬러삭스민트색.jpg");
            createItem("우먼즈 컬러 삭스 (벽돌색)", 3900, ItemCategory.WOMEN, "color", "red", "brown", null, "컬러삭스벽돌색.jpg");
            createItem("우먼즈 컬러 삭스 (보라색)", 3900, ItemCategory.WOMEN, "color", "purple", null, null, "컬러삭스보라색.jpg");
            createItem("우먼즈 컬러 삭스 (분홍색)", 3900, ItemCategory.WOMEN, "color", "pink", null, null, "컬러삭스분홍색.jpg");
            createItem("우먼즈 컬러 삭스 (빨간색)", 3900, ItemCategory.WOMEN, "color", "red", null, null, "컬러삭스빨간색.jpg");
            createItem("우먼즈 컬러 삭스 (아이보리)", 3900, ItemCategory.WOMEN, "color", "ivory", "white", null, "컬러삭스아이보리색.jpg");
            createItem("우먼즈 컬러 삭스 (연노란색)", 3900, ItemCategory.WOMEN, "color", "yellow", null, null, "컬러삭스연노란색.jpg");
            createItem("우먼즈 컬러 삭스 (연보라색)", 3900, ItemCategory.WOMEN, "color", "purple", null, null, "컬러삭스연보라색.jpg");
            createItem("우먼즈 컬러 삭스 (주황색)", 3900, ItemCategory.WOMEN, "color", "orange", null, null, "컬러삭스주황색.jpg");
            createItem("우먼즈 컬러 삭스 (진회색)", 3900, ItemCategory.WOMEN, "color", "gray", "black", null, "컬러삭스진회색.jpg");
            createItem("우먼즈 컬러 삭스 (청록색)", 3900, ItemCategory.WOMEN, "color", "green", "blue", null, "컬러삭스청록색.jpg");
            createItem("우먼즈 컬러 삭스 (청색)", 3900, ItemCategory.WOMEN, "color", "blue", null, null, "컬러삭스청색.jpg");
            createItem("우먼즈 컬러 삭스 (초록색)", 3900, ItemCategory.WOMEN, "color", "green", null, null, "컬러삭스초록색.jpg");
            createItem("우먼즈 컬러 삭스 (카키색)", 3900, ItemCategory.WOMEN, "color", "khaki", "green", null, "컬러삭스카키색.jpg");
            createItem("우먼즈 컬러 삭스 (하늘색)", 3900, ItemCategory.WOMEN, "color", "skyblue", "blue", null, "컬러삭스하늘색.jpg");
            createItem("우먼즈 컬러 삭스 (회색)", 3900, ItemCategory.WOMEN, "color", "gray", null, null, "컬러삭스회색.jpg");
            createItem("우먼즈 컬러 삭스 (흰색)", 3900, ItemCategory.WOMEN, "color", "white", null, null, "컬러삭스흰색.jpg");
			// ==========================================
			// [MEN] 남성 상품 데이터
			// ==========================================

			// 1. 레귤러 삭스 - itemType: "regular"
			createItem("맨즈 레귤러 삭스 (소프트핏)", 5900, ItemCategory.MEN, "regular", "black", "navy", "gray","레귤러삭스_삭스(소프트핏).jpg");
			createItem("맨즈 레귤러 삭스 (아가일)", 6900, ItemCategory.MEN, "regular", "navy", "gray", "red","레귤러삭스_삭스(아가일).jpg");
			createItem("맨즈 레귤러 삭스 (청키)", 6900, ItemCategory.MEN, "regular", "brown", "beige", null, "레귤러삭스_삭스(청키).jpg");
			createItem("맨즈 레귤러 삭스 (체크)", 6900, ItemCategory.MEN, "regular", "black", "white", null, "레귤러삭스_삭스(체크).jpg");
			createItem("맨즈 히트텍 삭스 (소프트핏)", 12900, ItemCategory.MEN, "regular", "black", "gray", null,"레귤러삭스_히트텍삭스(소프트핏).jpg");
			createItem("맨즈 히트텍 삭스 (케이블)", 12900, ItemCategory.MEN, "regular", "navy", "gray", null,"레귤러삭스_히트텍삭스(케이블).jpg");
			createItem("맨즈 히트텍 삭스 (플러피)", 12900, ItemCategory.MEN, "regular", "brown", "beige", null,"레귤러삭스_히트텍삭스(플러피).jpg");
			createItem("맨즈 스포츠 삭스 (파일)", 7900, ItemCategory.MEN, "regular", "white", "black", null,"레귤러삭스_스포츠삭스(파일).jpg");
			createItem("맨즈 스포츠 삭스 (파일라인)", 7900, ItemCategory.MEN, "regular", "white", "navy", "red","레귤러삭스_스포츠삭스(파일라인).jpg");

			// 2. 쇼트 삭스  - itemType: "short"
			createItem("맨즈 쇼트 삭스 (레이어드)", 4900, ItemCategory.MEN, "short", "gray", "black", null, "쇼트삭스_(레이어드).jpg");
			createItem("맨즈 쇼트 삭스 (립)", 4900, ItemCategory.MEN, "short", "navy", "white", null, "쇼트삭스_(립).jpg");
			createItem("맨즈 쇼트 삭스 (파일라인)", 4900, ItemCategory.MEN, "short", "white", "blue", "red", "쇼트삭스_(파일라인).jpg");
			createItem("맨즈 쇼트 삭스 (멜란지)", 4900, ItemCategory.MEN, "short", "gray", "charcoal", null, "쇼트삭스(멜란지).jpg");
			createItem("맨즈 스포츠 쇼트 삭스", 7900, ItemCategory.MEN, "short", "white", "gray", "black", "쇼트삭스_스포츠쇼트삭스.jpg");

			// 3. 컬러 삭스 (단색) - itemType: "color"
			createItem("맨즈 컬러 삭스 (갈색)", 3900, ItemCategory.MEN, "color", "brown", null, null, "컬러삭스갈색.jpg");
			createItem("맨즈 컬러 삭스 (검정색)", 3900, ItemCategory.MEN, "color", "black", null, null, "컬러삭스검정색.jpg");
			createItem("맨즈 컬러 삭스 (남색)", 3900, ItemCategory.MEN, "color", "navy", null, null, "컬러삭스남색.jpg");
			createItem("맨즈 컬러 삭스 (민트색)", 3900, ItemCategory.MEN, "color", "mint", "green", null, "컬러삭스민트색.jpg");
			createItem("맨즈 컬러 삭스 (벽돌색)", 3900, ItemCategory.MEN, "color", "red", "brown", null, "컬러삭스벽돌색.jpg");
			createItem("맨즈 컬러 삭스 (보라색)", 3900, ItemCategory.MEN, "color", "purple", null, null, "컬러삭스보라색.jpg");
			createItem("맨즈 컬러 삭스 (분홍색)", 3900, ItemCategory.MEN, "color", "pink", null, null, "컬러삭스분홍색.jpg");
			createItem("맨즈 컬러 삭스 (빨간색)", 3900, ItemCategory.MEN, "color", "red", null, null, "컬러삭스빨간색.jpg");
			createItem("맨즈 컬러 삭스 (아이보리)", 3900, ItemCategory.MEN, "color", "ivory", "white", null, "컬러삭스아이보리색.jpg");
			createItem("맨즈 컬러 삭스 (연노란색)", 3900, ItemCategory.MEN, "color", "yellow", null, null, "컬러삭스연노란색.jpg");
			createItem("맨즈 컬러 삭스 (연보라색)", 3900, ItemCategory.MEN, "color", "purple", null, null, "컬러삭스연보라색.jpg");
			createItem("맨즈 컬러 삭스 (주황색)", 3900, ItemCategory.MEN, "color", "orange", null, null, "컬러삭스주황색.jpg");
			createItem("맨즈 컬러 삭스 (진회색)", 3900, ItemCategory.MEN, "color", "gray", "black", null, "컬러삭스진회색.jpg");
			createItem("맨즈 컬러 삭스 (청록색)", 3900, ItemCategory.MEN, "color", "green", "blue", null, "컬러삭스청록색.jpg");
			createItem("맨즈 컬러 삭스 (청색)", 3900, ItemCategory.MEN, "color", "blue", null, null, "컬러삭스청색.jpg");
			createItem("맨즈 컬러 삭스 (초록색)", 3900, ItemCategory.MEN, "color", "green", null, null, "컬러삭스초록색.jpg");
			createItem("맨즈 컬러 삭스 (카키색)", 3900, ItemCategory.MEN, "color", "khaki", "green", null, "컬러삭스카키색.jpg");
			createItem("맨즈 컬러 삭스 (하늘색)", 3900, ItemCategory.MEN, "color", "skyblue", "blue", null, "컬러삭스하늘색.jpg");
			createItem("맨즈 컬러 삭스 (회색)", 3900, ItemCategory.MEN, "color", "gray", null, null, "컬러삭스회색.jpg");
			createItem("맨즈 컬러 삭스 (흰색)", 3900, ItemCategory.MEN, "color", "white", null, null, "컬러삭스흰색.jpg");
			
			// ==========================================
			// [KIDS] 키즈 상품 데이터
			// ==========================================

			// 1. 레귤러 삭스 (히트텍, 3P 세트 포함) - itemType: "regular"
			createItem("키즈 삭스 3P (라인)", 9900, ItemCategory.KIDS, "regular", "white", "navy", null, "삭스3P(라인).jpg");
			createItem("키즈 삭스 3P (프릴)", 9900, ItemCategory.KIDS, "regular", "pink", "white", null, "삭스3P(프릴).jpg");
			createItem("키즈 히트텍 삭스 (퍼리)", 5900, ItemCategory.KIDS, "regular", "gray", null, null, "히트텍삭스(퍼리).jpg");
			createItem("키즈 히트텍 삭스 2P", 12900, ItemCategory.KIDS, "regular", "black", "gray", null, "히트텍삭스2p.jpg");
			createItem("키즈 히트텍 삭스 2P (멜란지)", 12900, ItemCategory.KIDS, "regular", "gray", "beige", null,
					"히트텍삭스2P(멜란지).jpg");

			// 2. 쇼트 삭스 - itemType: "short"
			createItem("키즈 쇼트 삭스 (서포트)", 3500, ItemCategory.KIDS, "short", "gray", null, null, "쇼트삭스(서포트).jpg");
			createItem("키즈 쇼트 삭스 3P (기본)", 8900, ItemCategory.KIDS, "short", "white", "gray", "black", "쇼트삭스3P.jpg");
			createItem("키즈 쇼트 삭스 3P (라인)", 8900, ItemCategory.KIDS, "short", "white", "blue", null, "쇼트삭스3P(라인).jpg");

			// 3. 컬러 삭스 (단색) - itemType: "color"
			// 파일명에 있는 한글 색상을 영어 코드로 매핑했습니다.
			createItem("키즈 컬러 삭스 (갈색)", 3500, ItemCategory.KIDS, "color", "brown", null, null, "컬러삭스갈색.jpg");
			createItem("키즈 컬러 삭스 (검정색)", 3500, ItemCategory.KIDS, "color", "black", null, null, "컬러삭스검정색.jpg");
			createItem("키즈 컬러 삭스 (남색)", 3500, ItemCategory.KIDS, "color", "navy", null, null, "컬러삭스남색.jpg");
			createItem("키즈 컬러 삭스 (민트색)", 3500, ItemCategory.KIDS, "color", "mint", "green", null, "컬러삭스민트색.jpg");
			createItem("키즈 컬러 삭스 (벽돌색)", 3500, ItemCategory.KIDS, "color", "red", "brown", null, "컬러삭스벽돌색.jpg");
			createItem("키즈 컬러 삭스 (보라색)", 3500, ItemCategory.KIDS, "color", "purple", null, null, "컬러삭스보라색.jpg");
			createItem("키즈 컬러 삭스 (분홍색)", 3500, ItemCategory.KIDS, "color", "pink", null, null, "컬러삭스분홍색.jpg");
			createItem("키즈 컬러 삭스 (빨간색)", 3500, ItemCategory.KIDS, "color", "red", null, null, "컬러삭스빨간색.jpg");
			createItem("키즈 컬러 삭스 (아이보리)", 3500, ItemCategory.KIDS, "color", "ivory", "white", null, "컬러삭스아이보리색.jpg");
			createItem("키즈 컬러 삭스 (연노란색)", 3500, ItemCategory.KIDS, "color", "yellow", null, null, "컬러삭스연노란색.jpg");
			createItem("키즈 컬러 삭스 (연보라색)", 3500, ItemCategory.KIDS, "color", "purple", null, null, "컬러삭스연보라색.jpg");
			createItem("키즈 컬러 삭스 (주황색)", 3500, ItemCategory.KIDS, "color", "orange", null, null, "컬러삭스주황색.jpg");
			createItem("키즈 컬러 삭스 (진회색)", 3500, ItemCategory.KIDS, "color", "gray", "black", null, "컬러삭스진회색.jpg");
			createItem("키즈 컬러 삭스 (청록색)", 3500, ItemCategory.KIDS, "color", "green", "blue", null, "컬러삭스청록색.jpg");
			createItem("키즈 컬러 삭스 (청색)", 3500, ItemCategory.KIDS, "color", "blue", null, null, "컬러삭스청색.jpg");
			createItem("키즈 컬러 삭스 (초록색)", 3500, ItemCategory.KIDS, "color", "green", null, null, "컬러삭스초록색.jpg");
			createItem("키즈 컬러 삭스 (카키색)", 3500, ItemCategory.KIDS, "color", "khaki", "green", null, "컬러삭스카키색.jpg");
			createItem("키즈 컬러 삭스 (하늘색)", 3500, ItemCategory.KIDS, "color", "skyblue", "blue", null, "컬러삭스하늘색.jpg");
			createItem("키즈 컬러 삭스 (회색)", 3500, ItemCategory.KIDS, "color", "gray", null, null, "컬러삭스회색.jpg");
			createItem("키즈 컬러 삭스 (흰색)", 3500, ItemCategory.KIDS, "color", "white", null, null, "컬러삭스흰색.jpg");

		}
	}

	private void createItem(String name, int price, ItemCategory category, String type, String c1, String c2, String c3,
			String imgName) {
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