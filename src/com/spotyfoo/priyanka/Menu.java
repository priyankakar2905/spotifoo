package com.spotyfoo.priyanka;

import java.util.List;

public class Menu {

    private final String menuTitle;
    private final List<String> menuList;

    public Menu(List<String> menuList, String menuTitle) {
        this.menuList = menuList;
        this.menuTitle = menuTitle;
        if (!menuTitle.equalsIgnoreCase(Utils.ROOT)) {
            this.menuList.add(Utils.BACK_TO_MAIN_MENU);
        }
    }

    public List<String> getMenuList() {
        return menuList;
    }

    public String getMenuTitle() {
        return menuTitle;
    }


}
