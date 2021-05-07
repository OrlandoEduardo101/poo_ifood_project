package di;

import modules.auth.domain.repository.IAuthRepository;
import modules.auth.domain.usecases.LoginUserUsecase;
import modules.auth.domain.usecases.RegisterUserUsecase;
import modules.auth.external.datasources.AuthDatasource;
import modules.auth.infra.datasources.IAuthDatasource;
import modules.auth.infra.repositories.AuthRepository;
import modules.auth.presenter.login.LoginController;
import modules.auth.presenter.register.RegisterController;
import modules.home.domain.repositories.IHomeRepository;
import modules.home.domain.usecases.CreateAnnouncementUsecase;
import modules.home.domain.usecases.DeleteMyAnnouncementUsecase;
import modules.home.domain.usecases.ListAllAnnouncementUsecase;
import modules.home.domain.usecases.ListMyAnnouncementUsecase;
import modules.home.external.datasource.HomeDatasource;
import modules.home.infra.datasource.IHomeDatasource;
import modules.home.infra.repositories.HomeRepository;
import modules.home.presenter.HomeController;
import shared.AuthStore.AuthStore;

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

        //Global
        serviceLocator.register("AuthStore", AuthStore.getInstance());

        /* Auth Binds */

        //external
        serviceLocator.register("IAuthDatasource", AuthDatasource.getInstance());

        //infra
        serviceLocator.register("IAuthRepository", AuthRepository.getInstance((IAuthDatasource) serviceLocator.get("IAuthDatasource")));

        //domain
        serviceLocator.register("IRegisterUserUsecase", RegisterUserUsecase.getInstance((IAuthRepository) serviceLocator.get("IAuthRepository")));
        serviceLocator.register("ILoginUserUsecase", LoginUserUsecase.getInstance((IAuthRepository) serviceLocator.get("IAuthRepository")));

        //presenter
        serviceLocator.register("LoginController", new LoginController((LoginUserUsecase) serviceLocator.get("ILoginUserUsecase"), (AuthStore) serviceLocator.get("AuthStore")));
        serviceLocator.register("RegisterController", new RegisterController((RegisterUserUsecase) serviceLocator.get("IRegisterUserUsecase"), (AuthStore) serviceLocator.get("AuthStore")));

        /* Home Binds */

        //external
        serviceLocator.register("IHomeDatasource", HomeDatasource.getInstance());

        //infra
        serviceLocator.register("IHomeRepository", HomeRepository.getInstance((IHomeDatasource) serviceLocator.get("IHomeDatasource")));

        //domain
        serviceLocator.register("IRegisterUserUsecase", CreateAnnouncementUsecase.getInstance((IHomeRepository) serviceLocator.get("IHomeRepository")));
        serviceLocator.register("IListAllAnnouncementUsecase", ListAllAnnouncementUsecase.getInstance((IHomeRepository) serviceLocator.get("IHomeRepository")));
        serviceLocator.register("IListMyAnnouncementUsecase", ListMyAnnouncementUsecase.getInstance((IHomeRepository) serviceLocator.get("IHomeRepository")));
        serviceLocator.register("IDeleteMyAnnouncementUsecase", DeleteMyAnnouncementUsecase.getInstance((IHomeRepository) serviceLocator.get("IHomeRepository")));
        serviceLocator.register("ICreateAnnouncementUsecase", CreateAnnouncementUsecase.getInstance((IHomeRepository) serviceLocator.get("IHomeRepository")));

        //presenter
        serviceLocator.register("HomeController", new HomeController(
                (AuthStore) serviceLocator.get("AuthStore"),
                (CreateAnnouncementUsecase) serviceLocator.get("ICreateAnnouncementUsecase"),
                (DeleteMyAnnouncementUsecase) serviceLocator.get("IDeleteMyAnnouncementUsecase"),
                (ListAllAnnouncementUsecase) serviceLocator.get("IListAllAnnouncementUsecase"),
                (ListMyAnnouncementUsecase) serviceLocator.get("IListMyAnnouncementUsecase")
                ));




    }

}
