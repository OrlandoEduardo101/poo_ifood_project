package modules.auth.presenter.register;

import di.InjectionDependency;
import modules.auth.domain.errors.IAuthException;
import modules.auth.presenter.login.LoginController;
import modules.home.domain.errors.IHomeException;
import modules.home.presenter.HomePage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RegisterPage {
    RegisterController controller = (RegisterController) InjectionDependency.getInstance().get("RegisterController");
    Scanner scanner = new Scanner(System.in);
    int isLoop = 0;
    public void register() throws IHomeException {
        do {
            try{
                System.out.println("Please enter your name: ");
                controller.setName(scanner.next());
                System.out.println("Please enter your undername: ");
                controller.setUnderName(scanner.next());
                System.out.println("Please enter your CPF: ");
                controller.setCPF(scanner.next());
                System.out.println("Please enter your city: ");
                controller.setCity(scanner.next());
                System.out.println("Please enter your email: ");
                controller.setEmail(scanner.next());
                System.out.println("Please enter your password: ");
                controller.setPassword(scanner.next());
                System.out.println("Please confirm your password: ");
                controller.setConfirmPassword(scanner.next());
                System.out.println("User registered and logged: " + controller.userLogged.getName());
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
