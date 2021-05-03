package modules.auth.presenter.register;

import modules.auth.domain.errors.IAuthException;
import modules.auth.domain.models.AuthParam;
import modules.auth.domain.models.UserModel;
import modules.auth.domain.usecases.LoginUserUsecase;
import modules.auth.domain.usecases.RegisterUserUsecase;

public class RegisterController {

    UserModel userModel = new UserModel();
    UserModel userLogged;
    private RegisterUserUsecase _registerUserUsecase;

    public RegisterController(RegisterUserUsecase registerUserUsecase){
        this._registerUserUsecase = registerUserUsecase;
    }

    public void setName(String name){
        userModel.setName(name);
    }

    public void setCPF(String cpf){
        userModel.setCPF(cpf);
    }

    public void setCity(String city){
        userModel.setCity(city);
    }

    public void setUnderName(String underName){
        userModel.setUndername(underName);
    }

    public void setEmail(String email){
        userModel.setEmail(email);
    }

    public void setPassword(String password) {
        userModel.setPassword(password);
    }

    public void setConfirmPassword(String confirmPassword) throws IAuthException {
        userModel.setConfirmPassword(confirmPassword);
        if (userModel.isValidPassword()) {
            userLogged = _registerUserUsecase.registerUser(userModel);
        }
    }
}
