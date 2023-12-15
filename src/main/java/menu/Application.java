package menu;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(); // 사람 객체 리스트
        List<Integer> forcheck = new ArrayList<>(); // 카테고리 체크용 리스트

        View.Print_Title(); // 첫번째 뷰 프린트
        List<String> names = Name_Input(); // 식사 사람들 입력
        HashMap<String, List<String>> cant_eats = CantEat(names); // 사람별 못먹는 음식 입력
        Make_People(cant_eats, people); // 못먹는 음식으로 사람 객체를 만들고 리스트에 담기
        SelectMenu(forcheck,people); // 중복 카테고리 체크 및 메뉴 체크 후 메뉴 선정
        View.Result_Print(forcheck, people); // 결과 출력
    }

    private static void Make_People(HashMap<String, List<String>> cant_eats, List<Person> people){
        // 사람 객체에 못먹는것을 담아 리스트에 넣어두기
        Set<Entry<String, List<String>>> entrySet = cant_eats.entrySet();
        for (Map.Entry<String, List<String>> entry : entrySet) {
            String name = entry.getKey();
            List<String> foods = entry.getValue();
            Person temp = new Person(name, foods);
            people.add(temp);
        }
    }

    private static void SelectMenu(List<Integer> forcheck , List<Person> people){
        for (String day : Arrays.asList("월요일", "화요일", "수요일", "목요일", "금요일")) {
            int i = catagory_valid(forcheck);

            for (Person p : people) {
                String menu = p.selectMenu(day, i);
            }
        }
    }


    private static int catagory_valid(List<Integer> forcheck) {
        int i = Randoms.pickNumberInRange(1, 5);
        long cnt = forcheck.stream().filter(element -> element.equals(i)).count();
        if (cnt < 2) {
            forcheck.add(i);
            return i;
        }
        return catagory_valid(forcheck);
    }

    private static HashMap<String, List<String>> CantEat(List<String> names) {
        HashMap<String, List<String>> cant_eats = new HashMap<>();
        for (String name : names) {
            cant_eats.put(name, OneCantEat(name));
        }
        return cant_eats;
    }

    private static List<String> OneCantEat(String name) {
        try {
            return View.One_CantEat_Input(name);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 못 먹는 음식입력 오류");
            return OneCantEat(name);
        }
    }

    private static List<String> Name_Input() {
        List<String> names = View.Start_View();

        try {
            valid_names(names);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 잘못된 입력입니다");
            return Name_Input();
        }
        return names;
    }

    private static void valid_names(List<String> names) {
        // 명 수 밸리 데이션
        if (names.size() < 2 || names.size() > 5) {
            throw new IllegalArgumentException("2~5사이여야 합닌다");
        }
        // 이름 밸리데이션
        for (String name : names) {
            if (name.length() >= 2 && name.length() <= 4) {
                continue;
            }
            throw new IllegalArgumentException("이름 글자수는 2~4 여야 합니다");
        }
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException("중복된 입력 값이 있다.");
        }
    }

    public static List<String> splitAndTrim(String input) {
        List<String> resultList = new ArrayList<>();

        // 콤마로 문자열 분리
        String[] parts = input.split(",");

        // 각 부분의 좌우 공백 제거 후 리스트에 추가
        for (String part : parts) {
            String trimmedPart = part.trim();
            resultList.add(trimmedPart);
        }

        return resultList;
    }


    public static void valid_foods(List<String> foods) {
        // 메뉴판에 있는 메뉴인지 확인
        for (String food : foods) {
            if (Objects.equals(food, "") && foods.size() > 1) { // 빈칸 넣고 다른거 넣지 못하게끔
                throw new IllegalArgumentException("없으면 없다고 하지, 빈칸은 왜 넣음");
            }
            if (!Objects.equals(food, "") && Menu.IsIn(food) == 0) { // 메뉴판에 없으면 걸러주지를 못하잖아
                throw new IllegalArgumentException("메뉴판에 없는 메뉴");
            }
        }
        if (foods.size() > 2) { // 숫자 맞춰서
            throw new IllegalArgumentException("0~2개까지만 입력가능");
        }
        if (foods.size() != new HashSet<>(foods).size()) {
            throw new IllegalArgumentException("중복된 입력 값이 있다.");
        }
    }
}
