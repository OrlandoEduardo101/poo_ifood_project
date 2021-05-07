package modules.home.infra.repositories;

import modules.auth.domain.errors.RegisterUserFailure;
import modules.auth.domain.repository.IAuthRepository;
import modules.auth.infra.datasources.IAuthDatasource;
import modules.auth.infra.repositories.AuthRepository;
import modules.home.domain.entities.AnnouncementEntity;
import modules.home.domain.errors.CreateAnnouncementError;
import modules.home.domain.errors.DeleteAnnouncementError;
import modules.home.domain.errors.IHomeException;
import modules.home.domain.errors.ListAnnouncementError;
import modules.home.domain.repositories.IHomeRepository;
import modules.home.infra.datasource.IHomeDatasource;

import java.util.List;

public class HomeRepository implements IHomeRepository {
    private IHomeDatasource _datasource;

    private static IHomeRepository instance;

    private HomeRepository(IHomeDatasource datasource) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this._datasource = datasource;
    }

    public static AuthRepository getInstance(IHomeDatasource datasource) {
        if (instance == null) {
            instance = new HomeRepository(datasource);
        }
        return (AuthRepository) instance;
    }


    @Override
    public AnnouncementEntity createAnnouncement(AnnouncementEntity announcementParam) throws CreateAnnouncementError, IHomeException {
        try{
            return _datasource.createAnnouncement(announcementParam);
        } catch (Exception e) {
            throw new CreateAnnouncementError(e.getMessage());
        }
    }

    @Override
    public List<AnnouncementEntity> listAllAnnouncement() throws ListAnnouncementError, IHomeException {
        try{
            return _datasource.listAllAnnouncement();
        } catch (Exception e) {
            throw new ListAnnouncementError(e.getMessage());
        }
    }

    @Override
    public List<AnnouncementEntity> listMyAnnouncement(int userID) throws ListAnnouncementError, IHomeException {
        try{
            return _datasource.listMyAnnouncement(userID);
        } catch (Exception e) {
            throw new ListAnnouncementError(e.getMessage());
        }
    }

    @Override
    public List<AnnouncementEntity> deleteMyAnnouncement(int userID, String productCode) throws IHomeException {
        try{
            return _datasource.deleteMyAnnouncement(userID, productCode);
        } catch (Exception e) {
            throw new DeleteAnnouncementError(e.getMessage());
        }
    }
}
