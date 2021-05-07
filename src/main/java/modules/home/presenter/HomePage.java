package modules.home.presenter;

import di.InjectionDependency;
import modules.auth.domain.errors.IAuthException;
import modules.auth.presenter.login.LoginPage;
import modules.auth.presenter.register.RegisterPage;
import modules.home.domain.errors.IHomeException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HomePage {
    public void menu(){
        int option = -1;
        Scanner scanner = new Scanner(System.in);
        HomeController controller = (HomeController) InjectionDependency.getInstance().get("HomeController");
        do {
            try{
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

        if(option == 0){
            System.out.println("[0] Create Annoucement ");
            menu();
            //return;
        } else if(option == 1){
            System.out.println("[1] List All Annoucement");
            try{
                controller.listAll();
                for (int i = 0; i < controller.announcementEntityList.size(); i++) {
                    System.out.println(controller.announcementEntityList.get(i).toString() + "\n");
                }
            } catch (InputMismatchException | IHomeException inputMismatchException) {
                System.err.printf("%nException: %s%n", inputMismatchException);
                scanner.nextLine(); // discard input so user can try again
            }
            menu();
            //return;
        } else if(option == 2){
            System.out.println("[2] List My Annoucement");
            try{
                controller.listMyAll();
                for (int i = 0; i < controller.announcementEntityList.size(); i++) {
                    System.out.println(controller.announcementEntityList.get(i).toString() + "\n");
                }
            } catch (InputMismatchException | IHomeException inputMismatchException) {
                System.err.printf("%nException: %s%n", inputMismatchException);
                scanner.nextLine(); // discard input so user can try again
            }
            menu();
            //return;
        } else if(option == 3){
            System.out.println("[3] Delete My Annoucement");
            menu();
            //return;
        } else if(option == 4){
            System.out.println("[4] to exit");
            //return;
        }
    }
}
