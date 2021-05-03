import di.StartModule;
import modules.auth.presenter.Auth;

public class Main {

    public static void main(String[] args) {
        StartModule startModule = StartModule.getInstance();
        startModule.initInjection();

        Auth auth = new Auth();
        auth.loginOrResgister();
    }
}
