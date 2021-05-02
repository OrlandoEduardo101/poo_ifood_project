package modules.auth.domain.usecases;


import modules.auth.domain.errors.IAuthException;
import modules.auth.domain.errors.RegisterUserFailure;
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
public class RegisterUserUsecaseTest {
    private IRegisterUserUsecase registerUserUsecase;
    private UserModel user = new UserModel();
    //user.setName('name');

    @Mock
    IAuthRepository repository;

    @BeforeEach
    public void setUp() {
        registerUserUsecase = new RegisterUserUsecase(repository);
        Mockito.lenient().when(repository.registerUser(any())).thenReturn(user);
    }


    //@DisplayName("Should return a userModel")
    @Test
    public void testRegisterSucces() throws IAuthException {

        when(repository.registerUser(any(UserModel.class))).then(new Answer<UserModel>() {
            //int sequence = 1;

            @Override
            public UserModel answer(InvocationOnMock invocation) throws Throwable {
                UserModel user = (UserModel) invocation.getArgument(0);
                user.setName("sequence++");
                return user;
            }
        });

        //when(repository.registerUser(any())).thenReturn(user);
        UserModel userResult = registerUserUsecase.registerUser(user);

        Mockito.verify(repository).registerUser(user);
        Assertions.assertNotNull(userResult);
        Mockito.verify(repository).registerUser(Matchers.isA(UserModel.class));

        //Mockito.verify(mailClient).sendUserRegistrationMail(insertedUser);

        /*assertEquals("Register user should work", user,
                registerUserUsecase.registerUser(user));*/
    }

    /*@Test
    public void testRegisterFailed() throws IAuthException {

        Mockito.lenient().when(repository.registerUser(any())).thenThrow(new RegisterUserFailure("teste"));

        when(repository.registerUser(any(UserModel.class))).then(new Answer<RegisterUserFailure>() {
            //int sequence = 1;

            @Override
            public RegisterUserFailure answer(InvocationOnMock invocation) throws Throwable {
                UserModel user = (UserModel) invocation.getArgument(0);
                user.setName("sequence++");
                return new RegisterUserFailure("error");
            }
        });

        //when(repository.registerUser(any())).thenReturn(user);
        UserModel userResult = registerUserUsecase.registerUser(user);

        //Mockito.verify(repository).registerUser(user);
       // Mockito.isA(userResult);
        Assertions.assertNotNull(userResult);
        Mockito.verify(repository).registerUser(Matchers.isA(UserModel.class));
        //Mockito.verify(mailClient).sendUserRegistrationMail(insertedUser);

        assertEquals("Register user should work", user,
                registerUserUsecase.registerUser(user));
    }*/


}