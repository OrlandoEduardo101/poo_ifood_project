package modules.home.presenter;

import di.InjectionDependency;
import modules.auth.presenter.Auth;
import modules.home.domain.errors.IHomeException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HomePage {

    Scanner scanner = new Scanner(System.in);
    HomeController controller = (HomeController) InjectionDependency.getInstance().get("HomeController");

    public void menu() throws IHomeException {
        int option = -1;

        do {
            try {
                System.out.println("Please enter choose a option: ");
                System.out.println("[0] Create Annoucement ");
                System.out.println("[1] List All Annoucement");
                System.out.println("[2] List My Annoucement");
                System.out.println("[3] Delete My Annoucement");
                System.out.println("[4] to exit");
                option = scanner.nextInt();
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } catch (InputMismatchException inputMismatchException) {
                System.err.printf("%nException: %s%n", inputMismatchException);
                scanner.nextLine(); // discard input so user can try again
                System.out.printf("You must only numbers. Please try again.%n%n");
            }
        } while (option != 0 && option != 1 && option != 2 && option != 3 && option != 4);

        if (option == 0) {
            System.out.println("[0] Create Annoucement ");
            createAnnoucement();

            //return;
        } else if (option == 1) {
            System.out.println("[1] List All Annoucement");
            try {
                controller.listAll();
                if (controller.announcementEntityList.size() > 10) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(controller.announcementEntityList.get(i).toString() + "\n");
                    }
                } else {
                    for (int i = 0; i < controller.announcementEntityList.size(); i++) {
                        System.out.println(controller.announcementEntityList.get(i).toString() + "\n");
                    }
                }
            } catch (InputMismatchException | IHomeException inputMismatchException) {
                System.err.printf("%nException: %s%n", inputMismatchException);
                scanner.nextLine(); // discard input so user can try again
            }
            menu();
            //return;
        } else if (option == 2) {
            System.out.println("[2] List My Annoucement");
            try {
                controller.listMyAll();
                if(controller.announcementEntityList.size() == 0) {
                    System.out.println("You have no announcement");
                }
                else if (controller.announcementEntityList.size() > 10) {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(controller.announcementEntityList.get(i).toString() + "\n");
                    }
                } else {
                    for (int i = 0; i < controller.announcementEntityList.size(); i++) {
                        System.out.println(controller.announcementEntityList.get(i).toString() + "\n");
                    }
                }
            } catch (InputMismatchException | IHomeException inputMismatchException) {
                System.err.printf("%nException: %s%n", inputMismatchException);
                scanner.nextLine(); // discard input so user can try again
            }
            menu();
            //return;
        } else if (option == 3) {
            System.out.println("[3] Delete My Annoucement");
            deleteAnnoucement();
            //menu();
            //return;
        } else if (option == 4) {
            System.out.println("[4] to exit");
            new Auth().loginOrResgister();
            //return;
        }
    }

    public void createAnnoucement() throws IHomeException {
        try {
            System.out.println("Enter announcement title:");
            String title = scanner.next();
            controller.setTitle(title);
            System.out.println("Enter announcement description:");
            scanner.next();
            String description = scanner.next();
            controller.setDescription(description);
            System.out.println("Enter announcement product code:");
            String code = scanner.next();
            controller.setProductCode(code);
            createProduct();
            controller.setNewMarket();
            System.err.println("\nSuccessfully registered");
        } catch (InputMismatchException | IHomeException inputMismatchException) {
            System.err.printf("%nException: %s%n", inputMismatchException);
            scanner.nextLine(); // discard input so user can try again
        }
        menu();
    }

    ;

    public void createProduct() throws IHomeException {
        int option = -1;

        do {
            try {
                System.out.println("Please enter choose a option: ");
                System.out.println("[0] Drink");
                System.out.println("[1] Food");
                System.out.println("[2] Market");
                option = scanner.nextInt();
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } catch (InputMismatchException inputMismatchException) {
                System.err.printf("%nException: %s%n", inputMismatchException);
                scanner.nextLine(); // discard input so user can try again
                System.out.printf("You must only numbers. Please try again.%n%n");
            }
        } while (option != 0 && option != 1 && option != 2 && option != 3 && option != 4);

        if (option == 0) {
            System.out.println("[0] Drink");
            System.out.println("Name (ex.: coke): ");
            String name = scanner.next();
            System.out.println("Price (ex.: 05): ");
            String price = scanner.next();
            System.out.println("Quantity (ex.: 600 mL): ");
            String quantity = scanner.next();
            controller.setDrink(name, Float.parseFloat(price), quantity);
            //return;
        } else if (option == 1) {
            System.out.println("[1] Food");
            System.out.println("Name (ex.: Hamburger): ");
            String name = scanner.next();
            System.out.println("Price (ex.: 15): ");
            String price = scanner.next();
            System.out.println("Quantity (ex.: 300 g): ");
            String quantity = scanner.next();
            controller.setFood(name, Float.parseFloat(price), quantity);
        } else if (option == 2) {
            int flagToStop = 0;
            System.out.println("[3] Market");
            System.out.println("Name (ex.: Food Stamp): ");
            String name = scanner.next();
            System.out.println("Quantity (ex.: 5 items): ");
            String quantity = scanner.next();
            controller.setMarket(name, quantity);

            do {
                Scanner sc = new Scanner(System.in);
                try {
                    System.out.println("Product Name");
                    String nameProduct = sc.next();
                    //sc.next();
                    System.out.println("Price (ex.: 15):");
                    float price = sc.nextFloat();
                    System.out.println("-1 to exit, 0 to be continued");
                    //sc.next();
                    flagToStop = sc.nextInt();
                    controller.fillMarket(nameProduct, price);

                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                } catch (InputMismatchException inputMismatchException) {
                    System.err.printf("%nException: %s%n", inputMismatchException);
                    sc.nextLine(); // discard input so user can try again
                    System.out.printf("You must only numbers. Please try again.%n%n");
                }

            } while (flagToStop != -1);
        }

    }

    ;

    public void deleteAnnoucement() throws IHomeException {
        try {
            System.out.println("Enter product code to delete");
            String code = scanner.next();
            controller.deleteMyAnnoucement(code);
            System.err.println("\nSuccessful delete");
        } catch (InputMismatchException | IHomeException inputMismatchException) {
            System.err.printf("%nException: %s%n", inputMismatchException);
            scanner.nextLine(); // discard input so user can try again
        }
        menu();
    }

    ;

}
