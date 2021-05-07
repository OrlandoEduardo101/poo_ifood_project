package modules.home.presenter;

import modules.auth.domain.usecases.RegisterUserUsecase;
import modules.home.domain.entities.*;
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
    AnnouncementEntity newAnnouncement = new AnnouncementEntity();

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

    public void deleteMyAnnoucement(String code) throws IHomeException {
        announcementEntityList = _deleteMyAnnouncementUsecase.deleteMyAnnouncement(_authStore.getLoggedUser().getId(), code);
    }

    public void setTitle(String title) throws IHomeException {
        newAnnouncement.setSellerName(_authStore.getLoggedUser().getName());
        newAnnouncement.setSellerId(_authStore.getLoggedUser().getId());
        newAnnouncement.setTitle(title);
    }

    public void setDescription(String descritpion) throws IHomeException {
        newAnnouncement.setDescription(descritpion);
    }

    public void setProductCode(String code) throws IHomeException {
        newAnnouncement.setProductCode(code);
    }

    public void setDrink(String name, float price, String quantity) throws IHomeException {
        DrinkModel drink = new DrinkModel();
        drink.setAchoolic(false);
        drink.setName(name);
        drink.setPrice("R$ " + price);
        drink.setQuantity(quantity);
        newAnnouncement.setProduct(drink);
        newAnnouncement = _createAnnouncementUsecase.createAnnouncement(newAnnouncement);
    }

    public void setFood(String name, float price, String quantity) throws IHomeException {
        MealModel food = new MealModel();
        food.setGluten(true);
        food.setName(name);
        food.setPrice("R$ " + price);
        food.setQuantity(quantity);
        newAnnouncement.setProduct(food);
        newAnnouncement = _createAnnouncementUsecase.createAnnouncement(newAnnouncement);
    }

    public void setMarket(String name, String quantity) throws IHomeException {
        MarketModel market = new MarketModel();
        market.setName(name);
        market.setQuantity(quantity);
        newAnnouncement.setProduct(market);
    }

    public void fillMarket(String name, float price) throws IHomeException {
        MarketModel market = (MarketModel) newAnnouncement.getProduct();
        market.fillProductsList(name, price);
        newAnnouncement.setProduct(market);
    }

    public void setNewMarket() throws IHomeException {
        newAnnouncement = _createAnnouncementUsecase.createAnnouncement(newAnnouncement);
    }



}
