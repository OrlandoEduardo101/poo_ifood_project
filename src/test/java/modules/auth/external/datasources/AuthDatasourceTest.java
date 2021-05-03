package modules.auth.external.datasources;

import modules.auth.domain.errors.IAuthException;
import modules.auth.domain.models.AuthParam;
import modules.auth.domain.models.UserModel;
import modules.auth.domain.repository.IAuthRepository;
import modules.auth.infra.datasources.IAuthDatasource;
import modules.auth.infra.repositories.AuthRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(JUnitPlatform.class)
class AuthDatasourceTest {
    private IAuthDatasource authDatasource;
    private AuthParam userParam = new AuthParam("user2@user.com","123456");
    private UserModel userLogged = new UserModel("User 4", "User 4", "123.456.789-00", "user4@user.com", "user city", "123456");

    @BeforeEach
    public void setUp() {
        authDatasource = AuthDatasource.getInstance();
    }

    @Test
    public void registerUser() throws IAuthException {

        UserModel userResult = authDatasource.registerUser(userLogged);

        assertEquals(userResult.getEmail(), userLogged.getEmail());

       // Mockito.verify(authDatasource).registerUser(userLogged);
        Assertions.assertNotNull(userResult);
       // Mockito.verify(authDatasource).registerUser(Matchers.isA(UserModel.class));
    }

    @Test
    public void loginUser() throws IAuthException {
        //authDatasource.registerUser(userLogged);
        UserModel userResult = authDatasource.loginUser(userParam);
        assertEquals(userResult.getEmail(), userParam.getEmail());
        Assertions.assertNotNull(userResult);
    }
}