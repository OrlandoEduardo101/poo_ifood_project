package modules.auth.domain.repository;
import modules.auth.domain.models.AuthParam;
import modules.auth.domain.models.UserModel;

public interface IAuthRepository {
    UserModel registerUser(UserModel userModel);
    UserModel loginUser(AuthParam authParam);
}
