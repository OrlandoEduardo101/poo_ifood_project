package modules.auth.infra.repositories;

import modules.auth.domain.errors.IAuthException;
import modules.auth.domain.models.AuthParam;
import modules.auth.domain.models.UserModel;
import modules.auth.domain.repository.IAuthRepository;
import modules.auth.infra.datasources.IAuthDatasource;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AuthRepositoryTest {
    private IAuthRepository authRepository;
    private AuthParam userParam = new AuthParam();
    private UserModel userLogged = new UserModel();

    @Mock
    IAuthDatasource datasource;

    @BeforeEach
    public void setUp() {
        authRepository = AuthRepository.getInstance(datasource);
        userParam.setEmail("email");
        userParam.setPassword("password");
    }

    @Test
    public void registerUser() throws IAuthException {
        Mockito.lenient().when(datasource.registerUser(any())).thenReturn(userLogged);
        when(datasource.registerUser(any(UserModel.class))).then(new Answer<UserModel>() {
            @Override
            public UserModel answer(InvocationOnMock invocation) throws Throwable {
                UserModel user = (UserModel) invocation.getArgument(0);
                user.setName("sequence++");
                return user;
            }
        });

        UserModel userResult = authRepository.registerUser(userLogged);

        Mockito.verify(datasource).registerUser(userLogged);
        Assertions.assertNotNull(userResult);
        Mockito.verify(datasource).registerUser(Matchers.isA(UserModel.class));
    }

    @Test
    public void loginUser() throws IAuthException {
        Mockito.lenient().when(datasource.loginUser(any())).thenReturn(userLogged);
        when(datasource.loginUser(any(AuthParam.class))).then(new Answer<UserModel>() {
            @Override
            public UserModel answer(InvocationOnMock invocation) throws Throwable {
                //UserModel user = (UserModel) invocation.callRealMethod();
                userLogged.setName("sequence++");
                return userLogged;
            }
        });

        UserModel userResult = datasource.loginUser(userParam);

        Mockito.verify(datasource).loginUser(userParam);
        Assertions.assertNotNull(userResult);
        Mockito.verify(datasource).loginUser(Matchers.isA(AuthParam.class));
    }
}