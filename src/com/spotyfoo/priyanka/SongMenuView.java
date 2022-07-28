package com.spotyfoo.priyanka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;

public class SongMenuView {

    private final SongMenuController songMenuController;

    public SongMenuView(SongMenuController songMenuController) {
        this.songMenuController = songMenuController;
    }

    public void initMenu() {
        List<String> rootMenuList = new ArrayList<>();
        rootMenuList.add(Utils.SONG);
        rootMenuList.add(Utils.ARTIST);
        rootMenuList.add(Utils.ALBUM);
        rootMenuList.add(Utils.GENRE);
        rootMenuList.add(Utils.SEARCH);
        rootMenuList.add(Utils.EXIT);
        Menu rootMenu = new Menu(rootMenuList, Utils.ROOT);
        displayMenu(true, rootMenu);
    }

    public void displayMenu(boolean isRoot, Menu menu) {

        showMessageToUser("Welcome to the Spotifoo music player! \n\n");
        showMessageToUser(menu.getMenuTitle() + " Menu\n");
        List<String> menuList = menu.getMenuList();
        List<String> currentMenu;
        int count = 0;
        for (String menuItem : menuList) {
            if (!menuItem.equalsIgnoreCase(Utils.BACK_TO_MAIN_MENU)) {
                System.out.printf("[%d] " + menuItem + "\n", ++count);
            } else {
                showMessageToUser("[0] " + menuItem + "\n");
            }
        }
        showMessageToUser("\nChoose an option and press enter: ");
        Scanner option = new Scanner(in);
        try {
            int entry = option.nextInt();

            while (true) {
                if (entry == 0 && !isRoot) {
                    clearScreen();
                    initMenu();
                    return;
                } else if (isRoot && entry == 6) {
                    System.exit(0);
                }
                if (0 < entry && entry < menuList.size()) {
                    showMessageToUser("You have chosen : " + menuList.get(entry - 1) + "\n\n");
                    if (isRoot) {
                        String searchString = null;
                        if (menuList.get(entry - 1).equalsIgnoreCase(Utils.SEARCH)) {
                            clearScreen();
                            showMessageToUser("Please enter the song you wish to search\n");
                            option.nextLine();//Clearing buffered "\n"
                            count = 0;
                            do {
                                if (count > 0) {
                                    showMessageToUser("Press enter a valid search string\n");
                                }
                                searchString = option.nextLine();
                                count++;
                            } while (searchString.equalsIgnoreCase("") || searchString.startsWith(" "));

                        }

                        currentMenu = songMenuController.getAppropriateMenuList(menuList.get(entry - 1), searchString);
                        if (currentMenu == null && menuList.get(entry - 1).equalsIgnoreCase(Utils.SEARCH)) {
                            clearScreen();
                            showMessageToUser("Searched song not found\n");
                            initMenu();
                            return;
                        }

                    } else {
                        currentMenu = songMenuController.getSubmenuListOrTakeAction(menu.getMenuTitle(), menuList.get(entry - 1));
                    }
                    Menu childMenu = new Menu(currentMenu, menuList.get(entry - 1));
                    clearScreen();
                    displayMenu(false, childMenu);
                } else {
                    clearScreen();
                    showMessageToUser("Please Enter a Valid Option from the menu\n");
                    displayMenu(isRoot, menu);
                }
            }
        } catch (IOException e) {
            clearScreen();
            showMessageToUser(e.getLocalizedMessage());
            initMenu();
        } catch (InputMismatchException e) {
            clearScreen();
            showMessageToUser("Please Enter a Valid Option from the menu\n");
            displayMenu(isRoot, menu);
        } catch (Exception e) {
            System.exit(0);
        }
    }


    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            //We keep it empty intentionally for a silent exit from
            //Spotifoo when control reaches this block
        }
    }


    public void showMessageToUser(String message) {
        System.out.print(message);
    }
}

