package modules.auth.domain.errors;

public class RegisterUserFailure extends IAuthException {
    public RegisterUserFailure(String errorMessage) {
        super(errorMessage);
    }
}