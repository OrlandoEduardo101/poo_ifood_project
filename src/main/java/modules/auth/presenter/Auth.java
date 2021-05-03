package modules.auth.presenter;
import modules.auth.presenter.login.LoginPage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Auth {
    int option = -1;
    Scanner scanner = new Scanner(System.in);
    public void loginOrResgister(){
        do {
         try{
            System.out.println("Please enter choose a option: ");
            System.out.println("[0] to login page ");
            System.out.println("[1] to register page ");
            System.out.println("[2] to exit");
            option = scanner.nextInt();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (InputMismatchException inputMismatchException) {
             System.err.printf("%nException: %s%n", inputMismatchException);
             scanner.nextLine(); // discard input so user can try again
             System.out.printf("You must only numbers. Please try again.%n%n");
         }
        } while (option != 0 && option != 1 && option != 2);

        if(option == 0){
            System.out.println("[0] to login page ");
            new LoginPage().login();
            //return;
        } else if(option == 1){
            System.out.println("[1] to register page ");
            //return;
        } else if(option == 0){
            System.out.println("[3] to exit ");
            //return;
        }
    }
}
