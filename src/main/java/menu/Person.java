package menu;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private List<String> cant_eat;
    private HashMap<String, String> day_menu = new HashMap<>();

    public Person(String name, List<String> cant_eat) {
        this.name = name;
        this.cant_eat = cant_eat;
    }

    public String selectMenu(String day, int i) {
        Menu catagory = Menu.values()[i-1];
        String menu = Randoms.shuffle(catagory.getFoods()).get(0);
        System.out.println(catagory.getKorean_name()+"  "+ menu);
        try {
            Menu_valid(menu);
        } catch (IllegalArgumentException e) {
            return selectMenu(day, i);
        }
        System.out.println(day+"  "+ menu);
        day_menu.put(day, menu);
        return menu;
    }

    private void Menu_valid(String menu) {
        for (String m : day_menu.values()) {
            if (Objects.equals(m, menu)) {
                throw new IllegalArgumentException("이번주에 이미 먹은 메뉴 데스네");
            }
        }
    }

    public void print() {
        System.out.print("[ " + name);

        for (String food : day_menu.keySet()) {
            System.out.print(" | " + food);
        }
        System.out.println(" ]");
    }
}
