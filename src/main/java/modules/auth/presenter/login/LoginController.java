package modules.auth.presenter.login;

import modules.auth.domain.errors.IAuthException;
import modules.auth.domain.models.AuthParam;
import modules.auth.domain.models.UserModel;
import modules.auth.domain.usecases.LoginUserUsecase;

public class LoginController {
    AuthParam authParam = new AuthParam();
    UserModel userModel;
    private LoginUserUsecase _loginUserUsecase;

    public LoginController(LoginUserUsecase loginUserUsecase){
        this._loginUserUsecase = loginUserUsecase;
    }

    public void setEmail(String email){
        authParam.setEmail(email);
    }

    public void setPassword(String password) throws IAuthException {
        authParam.setPassword(password);
        userModel = _loginUserUsecase.loginUser(authParam);
    }
}
