package di;

import modules.auth.domain.repository.IAuthRepository;
import modules.auth.domain.usecases.LoginUserUsecase;
import modules.auth.domain.usecases.RegisterUserUsecase;
import modules.auth.external.datasources.AuthDatasource;
import modules.auth.infra.datasources.IAuthDatasource;
import modules.auth.infra.repositories.AuthRepository;
import modules.auth.presenter.login.LoginController;
import modules.auth.presenter.register.RegisterController;

public class StartModule {
    public InjectionDependency serviceLocator = InjectionDependency.getInstance();

    private static StartModule instance;

    private StartModule() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static StartModule getInstance() {
        if (instance == null) {
            instance = new StartModule();
        }

        return instance;
    }

    public void initInjection(){

        //external
        serviceLocator.register("IAuthDatasource", AuthDatasource.getInstance());

        //infra
        serviceLocator.register("IAuthRepository", AuthRepository.getInstance((IAuthDatasource) serviceLocator.get("IAuthDatasource")));

        //domain
        serviceLocator.register("IRegisterUserUsecase", RegisterUserUsecase.getInstance((IAuthRepository) serviceLocator.get("IAuthRepository")));
        serviceLocator.register("ILoginUserUsecase", LoginUserUsecase.getInstance((IAuthRepository) serviceLocator.get("IAuthRepository")));

        //presenter
        serviceLocator.register("LoginController", new LoginController((LoginUserUsecase) serviceLocator.get("ILoginUserUsecase")));
        serviceLocator.register("RegisterController", new RegisterController((RegisterUserUsecase) serviceLocator.get("IRegisterUserUsecase")));

    }

}
