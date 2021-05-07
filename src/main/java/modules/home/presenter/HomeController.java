package modules.home.presenter;

import modules.auth.domain.usecases.RegisterUserUsecase;
import modules.home.domain.entities.AnnouncementEntity;
import modules.home.domain.errors.IHomeException;
import modules.home.domain.usecases.CreateAnnouncementUsecase;
import modules.home.domain.usecases.DeleteMyAnnouncementUsecase;
import modules.home.domain.usecases.ListAllAnnouncementUsecase;
import modules.home.domain.usecases.ListMyAnnouncementUsecase;
import shared.AuthStore.AuthStore;

import java.util.ArrayList;
import java.util.List;

public class HomeController {
    private CreateAnnouncementUsecase _createAnnouncementUsecase;
    private DeleteMyAnnouncementUsecase _deleteMyAnnouncementUsecase;
    private ListAllAnnouncementUsecase _listAllAnnouncementUsecase;
    private ListMyAnnouncementUsecase _listMyAnnouncementUsecase;
    private AuthStore _authStore;
    List<AnnouncementEntity> announcementEntityList = new ArrayList<AnnouncementEntity>();

    public HomeController(AuthStore authStore, CreateAnnouncementUsecase createAnnouncementUsecase, DeleteMyAnnouncementUsecase deleteMyAnnouncementUsecase, ListAllAnnouncementUsecase listAllAnnouncementUsecase, ListMyAnnouncementUsecase listMyAnnouncementUsecase){
        this._createAnnouncementUsecase = createAnnouncementUsecase;
        this._deleteMyAnnouncementUsecase = deleteMyAnnouncementUsecase;
        this._listAllAnnouncementUsecase = listAllAnnouncementUsecase;
        this._listMyAnnouncementUsecase = listMyAnnouncementUsecase;
        this._authStore = authStore;
    }

    public void listAll() throws IHomeException {
        announcementEntityList = _listAllAnnouncementUsecase.listAllAnnouncement();
    }

    public void listMyAll() throws IHomeException {
        announcementEntityList = _listMyAnnouncementUsecase.listMyAnnouncement(_authStore.getLoggedUser().getId());
    }

}
