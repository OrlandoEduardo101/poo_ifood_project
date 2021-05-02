package modules.auth.domain.repository;
import modules.auth.domain.errors.IAuthException;
import modules.auth.domain.errors.LoginUserFailure;
import modules.auth.domain.errors.RegisterUserFailure;
import modules.auth.domain.models.AuthParam;
import modules.auth.domain.models.UserModel;

public interface IAuthRepository {
    UserModel registerUser(UserModel userModel) throws RegisterUserFailure, IAuthException;
    UserModel loginUser(AuthParam authParam) throws LoginUserFailure, IAuthException;
}
