package com.gapple.backend.authentication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class NicknameUtils {

    private static final String[] ANIMALS = {

            "사자", "호랑이", "고래", "코끼리", "침팬지",
            "코알라", "캥거루", "늑대", "박쥐", "판다", "해달", "수달",

            "래브라도리트리버", "골든리트리버", "푸들", "시베리안허스키",
            "비글", "도베르만", "치와와", "불독", "닥스훈트",

            "참새", "독수리", "펭귄", "앵무새", "비둘기",
            "타조", "백조", "벌새", "플라밍고", "까치",

            "고양이", "여우", "고슴도치", "사슴", "기린",
            "하마", "얼룩말", "코뿔소", "바다코끼리", "돌고래",
            "바다사자", "스컹크", "너구리", "알파카", "라마",

            "미어캣", "두더지", "판골린", "고래상어", "마나티",
            "아르마딜로", "침팬지", "오랑우탄", "오소리", "하이에나",
            "두루미", "바다거북", "살모사", "개구리",
            "카멜레온", "가젤", "비버", "붉은여우", "바다사자"
    };

    private static final String[] TRAITS = {

            "용감한", "친절한", "영리한", "침착한", "외향적인",
            "사교적인", "조용한", "장난기 많은", "근면한", "유쾌한",

            "빠른", "강한", "작은", "큰", "우아한",
            "활발한", "느긋한", "예리한", "귀여운",

            "배려심 깊은", "호기심 많은", "냉철한", "결단력 있는", "정직한",
            "신중한", "성실한", "낙천적인", "차분한", "책임감 있는",

            "용의주도한", "활기찬", "포용력 있는", "끈기 있는", "긍정적인",
            "창의적인", "헌신적인", "격려하는", "자립적인", "성급한"
    };

    public static String generate() {

        Random random = new Random();

        String randomAnimal = ANIMALS[random.nextInt(ANIMALS.length)];
        String randomTraits = TRAITS[random.nextInt(TRAITS.length)];

        return randomTraits + " " + randomAnimal;
    }
}
