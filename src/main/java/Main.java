import di.StartModule;
import modules.auth.presenter.Auth;
import modules.home.presenter.HomePage;

public class Main {

    public static void main(String[] args) {
        StartModule startModule = StartModule.getInstance();
        startModule.initInjection();

        Auth auth = new Auth();
        auth.loginOrResgister();
        new HomePage().menu();
    }
}
