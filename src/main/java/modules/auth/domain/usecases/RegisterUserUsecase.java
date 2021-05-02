package modules.auth.domain.usecases;

import modules.auth.domain.errors.IAuthException;
import modules.auth.domain.errors.RegisterUserFailure;
import modules.auth.domain.models.UserModel;
import modules.auth.domain.repository.IAuthRepository;

interface IRegisterUserUsecase {
    UserModel registerUser(UserModel userModel) throws IAuthException;
}

public class RegisterUserUsecase implements IRegisterUserUsecase {
    private IAuthRepository _repository;

    public RegisterUserUsecase(IAuthRepository repository){
        this._repository = repository;
    }


    @Override
    public UserModel registerUser(UserModel userModel) throws IAuthException {
        try {
            return _repository.registerUser(userModel);
        } catch (Exception e) {
            throw new RegisterUserFailure(e.getMessage());
        }
    }
}
