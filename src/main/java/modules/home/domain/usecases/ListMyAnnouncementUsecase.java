package modules.home.domain.usecases;

import modules.home.domain.entities.AnnouncementEntity;
import modules.home.domain.errors.CreateAnnouncementError;
import modules.home.domain.errors.IHomeException;
import modules.home.domain.repositories.IHomeRepository;

import java.util.List;

interface IListMyAnnouncementUsecase {
    List<AnnouncementEntity> listMyAnnouncement(int userID) throws IHomeException;
}

public class ListMyAnnouncementUsecase implements IListMyAnnouncementUsecase {
    private IHomeRepository _repository;

    private static ListMyAnnouncementUsecase instance;

    private ListMyAnnouncementUsecase(IHomeRepository repository) {

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this._repository = repository;
    }

    public static ListMyAnnouncementUsecase getInstance(IHomeRepository repository) {
        if (instance == null) {
            instance = new ListMyAnnouncementUsecase(repository);
        }
        return instance;
    }


    @Override
    public List<AnnouncementEntity> listMyAnnouncement(int userID) throws IHomeException {
        try {
            return _repository.listMyAnnouncement(userID);
        } catch (Exception e) {
            throw new CreateAnnouncementError(e.getMessage());
        }
    }
}


