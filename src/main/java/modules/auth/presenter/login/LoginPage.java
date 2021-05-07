package modules.auth.presenter.login;

import di.InjectionDependency;
import modules.auth.domain.errors.IAuthException;
import modules.home.domain.errors.IHomeException;
import modules.home.presenter.HomePage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginPage {
    LoginController loginController = (LoginController) InjectionDependency.getInstance().get("LoginController");
    Scanner scanner = new Scanner(System.in);
    int isLoop = 0;
    public void login() throws IHomeException {
        do {
            try{
                System.out.println("Please enter your email: ");
                loginController.setEmail(scanner.next());
                System.out.println("Please enter your password: ");
                loginController.setPassword(scanner.next());
                System.out.println("User Logged: " + loginController.userModel.getName());
                System.out.print("\033[H\033[2J");
                System.out.flush();
                isLoop = 1;
            } catch (InputMismatchException | IAuthException inputMismatchException) {
                System.err.printf("%nException: %s%n", inputMismatchException);
                scanner.nextLine(); // discard input so user can try again
                System.out.printf("Invalid credentios. Please try again.%n%n");
            }
        } while (isLoop == 0);
        new HomePage().menu();
    }
}
