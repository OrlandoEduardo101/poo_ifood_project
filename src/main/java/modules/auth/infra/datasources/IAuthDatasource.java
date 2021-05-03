package modules.auth.infra.datasources;
import modules.auth.domain.errors.LoginUserFailure;
import modules.auth.domain.errors.RegisterUserFailure;
import modules.auth.domain.models.AuthParam;
import modules.auth.domain.models.UserModel;

public interface IAuthDatasource {
    UserModel registerUser(UserModel userModel) throws RegisterUserFailure;
    UserModel loginUser(AuthParam authParam) throws LoginUserFailure;
}
