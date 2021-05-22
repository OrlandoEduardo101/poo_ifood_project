import di.StartModule;
import modules.auth.presenter.Auth;
import modules.home.domain.errors.IHomeException;

public class Main {

    public static void main(String[] args) throws IHomeException {
        StartModule startModule = StartModule.getInstance();
        startModule.initInjection();

        Auth auth = new Auth();
        auth.loginOrResgister();

    }
}
