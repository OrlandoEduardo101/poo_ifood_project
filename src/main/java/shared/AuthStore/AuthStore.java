package shared.AuthStore;

import modules.auth.domain.models.UserModel;
import modules.auth.external.datasources.AuthDatasource;
import modules.auth.infra.datasources.IAuthDatasource;

public class AuthStore {

    private static AuthStore instance;

    private AuthStore() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static AuthStore getInstance(){
        if (instance == null) {
            instance = new AuthStore();
        }
        return (AuthStore) instance;
    }


    public UserModel getLoggedUser() {
        return _userLogged;
    }

    public void setLoggedUser(UserModel _userModel) {
        this._userLogged = _userLogged;
    }

    private UserModel _userLogged = new UserModel();
}
