package modules.home.domain.errors;

public class CreateAnnouncementError extends IHomeException {
    public CreateAnnouncementError(String errorMessage) {
        super(errorMessage);
    }
}