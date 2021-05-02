package modules.auth.domain.usecases;


import modules.auth.domain.errors.IAuthException;
import modules.auth.domain.models.AuthParam;
import modules.auth.domain.models.UserModel;
import modules.auth.domain.repository.IAuthRepository;
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
public class LoginUserUsecaseTest {
    private ILoginUserUsecase loginUserUsecase;
    private AuthParam userParam = new AuthParam();
    private UserModel userLogged = new UserModel();
    //user.setName('name');

    @Mock
    IAuthRepository repository;

    @BeforeEach
    public void setUp() {
        loginUserUsecase = new LoginUserUsecase(repository);
        userParam.setEmail("email");
        userParam.setPassword("password");

    }


    //@DisplayName("Should return a userModel")
    @Test
    public void testLoginSucces() throws IAuthException {
        Mockito.lenient().when(repository.loginUser(any())).thenReturn(userLogged);
        when(repository.loginUser(any(AuthParam.class))).then(new Answer<UserModel>() {
            //int sequence = 1;

            @Override
            public UserModel answer(InvocationOnMock invocation) throws Throwable {
                //UserModel user = (UserModel) invocation.callRealMethod();
                userLogged.setName("sequence++");
                return userLogged;
            }
        });

        //when(repository.registerUser(any())).thenReturn(user);
        UserModel userResult = loginUserUsecase.loginUser(userParam);

        Mockito.verify(repository).loginUser(userParam);
        Assertions.assertNotNull(userResult);
        Mockito.verify(repository).loginUser(Matchers.isA(AuthParam.class));

        //Mockito.verify(mailClient).sendUserRegistrationMail(insertedUser);

        /*assertEquals("Register user should work", user,
                registerUserUsecase.registerUser(user));*/
    }

    @Test//(assertThrow = IAuthException.class)
    public void testRegisterFailed() throws IAuthException {

       /* Mockito.lenient().when(repository.registerUser(any())).thenThrow(new RegisterUserFailure("error"));

       when(repository.registerUser(any(UserModel.class))).then(new Answer<RegisterUserFailure>() {
            @Override
            public RegisterUserFailure answer(InvocationOnMock invocation) throws Throwable {
                throw new RegisterUserFailure("error");
            }
        });*/

        /*Mockito.doThrow(RegisterUserFailure.class).when(
                repository
        ).registerUser(any());*/

        /*Assertions.assertThrows(RegisterUserFailure.class,
                () -> registerUserUsecase.registerUser(user));*/
       // assertTrue(result.getMessage().contains("error"));

        //when(repository.registerUser(any())).thenReturn(user);
        //UserModel userResult = registerUserUsecase.registerUser(user);

        //Mockito.verify(repository).registerUser(user);
        //Mockito.isA(userResult);

        //Mockito.doThrow();
        //Assertions.assertNotNull(userResult);
        //Mockito.verify(repository).registerUser(Matchers.isA(UserModel.class));
        //Mockito.verify(mailClient).sendUserRegistrationMail(insertedUser);

    }


}