package modules.auth.infra.datasources;
import modules.auth.domain.models.AuthParam;
import modules.auth.domain.models.UserModel;

public interface IAuthDatasource {
    UserModel registerUser(UserModel userModel);
    UserModel loginUser(AuthParam authParam);
}
