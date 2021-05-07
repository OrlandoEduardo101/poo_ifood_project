package modules.home.domain.repositories;

import modules.auth.domain.errors.IAuthException;
import modules.auth.domain.errors.LoginUserFailure;
import modules.auth.domain.models.AuthParam;
import modules.auth.domain.models.UserModel;
import modules.home.domain.entities.AnnouncementEntity;
import modules.home.domain.errors.CreateAnnouncementError;
import modules.home.domain.errors.IHomeException;
import modules.home.domain.errors.ListAnnouncementError;

import java.util.List;

public interface IHomeRepository {
    AnnouncementEntity createAnnouncement(AnnouncementEntity announcementParam) throws CreateAnnouncementError, IHomeException;
    List<AnnouncementEntity> listAllAnnouncement() throws ListAnnouncementError, IHomeException;
    List<AnnouncementEntity> listMyAnnouncement(int userID) throws ListAnnouncementError, IHomeException;
    List<AnnouncementEntity> deleteMyAnnouncement(int userID, String productCode) throws ListAnnouncementError, IHomeException;
}
