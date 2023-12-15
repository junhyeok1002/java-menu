package menu;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Menu {
    Japanese(1, Arrays.asList("규동", "우동", "미소시루", "스시", "가츠동", "오니기리", "하이라이스", "라멘", "오코노미야끼")),
    Korean(2, Arrays.asList("김밥", "김치찌개", "쌈밥", "된장찌개", "비빔밥", "칼국수", "불고기", "떡볶이", "제육볶음")),
    Chinese(3, Arrays.asList("깐풍기", "볶음면", "동파육", "짜장면", "짬뽕", "마파두부", "탕수육", "토마토 달걀볶음", "고추잡채")),
    Asian(4, Arrays.asList("팟타이", "카오 팟", "나시고렝", "파인애플 볶음밥", "쌀국수", "똠얌꿍", "반미", "월남쌈", "분짜")),
    Western(5, Arrays.asList("라자냐", "그라탱", "뇨끼", "끼슈", "프렌치 토스트", "바게트", "스파게티", "피자", "파니니")),
    ;

    private final int number;
    private final List<String> foods;

    // Enum 생생자
    Menu(int number, List<String> foods) {
        this.number = number;
        this.foods = foods;
    }

    public int getNumber() {
        return number;
    }

    public List<String> getFoods() {
        return foods;
    }

    public static int IsIn(String input){
        for (Menu p : Menu.values()) {
            for(String food : p.getFoods()){
                if (Objects.equals(food, input)){
                    return 1;
                }
            }
        }
        return 0;
    }
}

