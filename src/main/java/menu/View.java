package menu;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class View {
    public static void Print_Title() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public static List<String> Start_View() {
        System.out.println("\n코치의 이름을 입력해 주세요. (, 로 구분)");
        String input = Console.readLine();
        List<String> names = Application.splitAndTrim(input);


        return names;
    }

    public static List<String> One_CantEat_Input(String name) {
        System.out.println("\n" + name + "(이)가 못 먹는 메뉴를 입력해 주세요.");
        String input = Console.readLine();

        List<String> cant_eat = Application.splitAndTrim(input);
        Application.valid_foods(cant_eat);

        return cant_eat;
    }

    public static void Result_Print(List<Integer> forcheck, List<Person> people){
        // 첫줄
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");

        // 둘째줄
        System.out.print("[ " + "카테고리");
        for (Integer i : forcheck) {
            Menu menu = Menu.values()[i - 1];
            String food = menu.getKorean_name();
            System.out.print(" | " + food);
        }
        System.out.println(" ]");

        // 다음 줄들
        for (Person p : people) {
            p.print();
        }
    }


}
