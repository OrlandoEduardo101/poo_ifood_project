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

    private static RegisterUserUsecase instance;

    private RegisterUserUsecase(IAuthRepository repository) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this._repository = repository;
    }

    public static RegisterUserUsecase getInstance(IAuthRepository repository) {
        if (instance == null) {
            instance = new RegisterUserUsecase(repository);
        }
        return instance;
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
