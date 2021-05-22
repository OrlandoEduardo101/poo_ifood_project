package modules.home.external.datasource;

import modules.home.domain.entities.AnnouncementEntity;
import modules.home.domain.entities.DrinkModel;
import modules.home.domain.entities.MarketModel;
import modules.home.domain.entities.MealModel;
import modules.home.domain.errors.CreateAnnouncementError;
import modules.home.domain.errors.DeleteAnnouncementError;
import modules.home.domain.errors.IHomeException;
import modules.home.domain.errors.ListAnnouncementError;
import modules.home.infra.datasource.IHomeDatasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeDatasource implements IHomeDatasource {

    private static IHomeDatasource instance;

    private HomeDatasource() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static IHomeDatasource getInstance() {
        if (instance == null) {
            instance = new HomeDatasource();
        }
        return (HomeDatasource) instance;
    }

    Map<Integer, Map<String, Object>> announcementList = new HashMap<Integer, Map<String, Object>>();

    public void fillDataBase() {
        MealModel hamburger = new MealModel();
        MealModel hamburger2 = new MealModel();
        DrinkModel coke = new DrinkModel();
        MarketModel foodStamp = new MarketModel();

        hamburger.setGluten(true);
        hamburger.setName("Hamburger");
        hamburger.setPrice("R$ 10,00");
        hamburger.setQuantity("500 g");
        hamburger2.setGluten(true);
        hamburger2.setName("Hamburger 2");
        hamburger2.setPrice("R$ 15,00");
        hamburger2.setQuantity("600 g");
        coke.setAchoolic(false);
        coke.setName("Coke");
        coke.setPrice("R$ 08,00");
        coke.setQuantity("1 L");
        foodStamp.setName("Month Fair");
        foodStamp.setPrice("R$ 50,00");
        foodStamp.setQuantity("5 items");
        foodStamp.fillProductsList("rice", 10.0F);
        foodStamp.fillProductsList("beans", 10.0F);
        foodStamp.fillProductsList("coke", 10.0F);
        foodStamp.fillProductsList("potato", 10.0F);
        foodStamp.fillProductsList("yakult", 10.0F);

        AnnouncementEntity announcement1 = new AnnouncementEntity(0, "Hamburger", "pão, egg e meal", 0, "Hamburger123",
                "User 1", hamburger);
        AnnouncementEntity announcement2 = new AnnouncementEntity(1, "Coke", "Coke freezed 1 L", 0, "Coke123", "User 1",
                coke);
        AnnouncementEntity announcement3 = new AnnouncementEntity(2, "Food Stamp", "food for a month", 0,
                "FoodStamp123", "User 1", hamburger);
        AnnouncementEntity announcement4 = new AnnouncementEntity(3, "Hamburger 2", "pão, egg, salad e meal", 0,
                "Hamburger2123", "User 1", hamburger2);

        AnnouncementEntity announcement5 = new AnnouncementEntity(4, "Hamburger", "pão, egg e meal", 1, "Hamburger456",
                "User 2", hamburger);
        AnnouncementEntity announcement6 = new AnnouncementEntity(5, "Coke", "Coke freezed 1 L", 1, "Coke456", "User 2",
                coke);
        AnnouncementEntity announcement7 = new AnnouncementEntity(6, "Food Stamp", "food for a month", 1,
                "FoodStamp456", "User 2", hamburger);
        AnnouncementEntity announcement8 = new AnnouncementEntity(7, "Hamburger 2", "pão, egg, salad e meal", 1,
                "Hamburger2456", "User 2", hamburger2);

        AnnouncementEntity announcement9 = new AnnouncementEntity(8, "Hamburger", "pão, egg e meal", 2, "Hamburger789",
                "User 3", hamburger);
        AnnouncementEntity announcement10 = new AnnouncementEntity(9, "Coke", "Coke freezed 1 L", 2, "Coke789",
                "User 3", coke);
        AnnouncementEntity announcement11 = new AnnouncementEntity(10, "Food Stamp", "food for a month", 2,
                "FoodStamp789", "User 3", hamburger);
        AnnouncementEntity announcement12 = new AnnouncementEntity(11, "Hamburger 2", "pão, egg, salad e meal", 2,
                "Hamburger789", "User 3", hamburger2);

        announcementList.put(announcement1.getId(), announcement1.toMap());
        announcementList.put(announcement2.getId(), announcement2.toMap());
        announcementList.put(announcement3.getId(), announcement3.toMap());
        announcementList.put(announcement4.getId(), announcement4.toMap());
        announcementList.put(announcement5.getId(), announcement5.toMap());
        announcementList.put(announcement6.getId(), announcement6.toMap());
        announcementList.put(announcement7.getId(), announcement7.toMap());
        announcementList.put(announcement8.getId(), announcement8.toMap());
        announcementList.put(announcement9.getId(), announcement9.toMap());
        announcementList.put(announcement10.getId(), announcement10.toMap());
        announcementList.put(announcement11.getId(), announcement11.toMap());
        announcementList.put(announcement12.getId(), announcement12.toMap());
    }

    @Override
    public AnnouncementEntity createAnnouncement(AnnouncementEntity announcementParam)
            throws CreateAnnouncementError, IHomeException {
        if (announcementList.isEmpty())
            fillDataBase();

        int id = announcementList.size();

        while (announcementList.containsKey(id)) {
            id++;
        }

        int keyLogged = -1;
        announcementParam.setId(id);
        announcementList.put(id, announcementParam.toMap());

        return announcementParam;
    }

    @Override
    public List<AnnouncementEntity> listAllAnnouncement() throws ListAnnouncementError, IHomeException {
        if (announcementList.isEmpty())
            fillDataBase();
        List<AnnouncementEntity> listAnonouncementEntity = new ArrayList<AnnouncementEntity>();

        for (Map.Entry<Integer, Map<String, Object>> entry : announcementList.entrySet()) {
            int key = entry.getKey();
            Map<String, Object> value = entry.getValue();
            listAnonouncementEntity.add(AnnouncementEntity.fromMap(value));
        }

        return listAnonouncementEntity;
    }

    @Override
    public List<AnnouncementEntity> listMyAnnouncement(int userID) throws ListAnnouncementError, IHomeException {
        if (announcementList.isEmpty())
            fillDataBase();
        List<AnnouncementEntity> listAnonouncementEntity = new ArrayList<AnnouncementEntity>();

        for (Map.Entry<Integer, Map<String, Object>> entry : announcementList.entrySet()) {
            int key = entry.getKey();
            Map<String, Object> value = entry.getValue();
            AnnouncementEntity tempEntity = AnnouncementEntity.fromMap(value);
            if (tempEntity.getSellerId() == userID) {
                listAnonouncementEntity.add(tempEntity);
            }
        }

        return listAnonouncementEntity;
    }

    @Override
    public List<AnnouncementEntity> deleteMyAnnouncement(int userID, String productCode)
            throws ListAnnouncementError, IHomeException {
        if (announcementList.isEmpty())
            fillDataBase();

        boolean removed = false;
        int removeKey = -1;

        for (Map.Entry<Integer, Map<String, Object>> entry : announcementList.entrySet()) {
            int key = entry.getKey();
            Map<String, Object> value = entry.getValue();
            AnnouncementEntity tempEntity = AnnouncementEntity.fromMap(value);
            if (tempEntity.getSellerId() == userID && tempEntity.getProductCode().equalsIgnoreCase(productCode)) {
                // announcementList.keySet().removeIf(keyValue -> keyValue == key);
                removeKey = key;
                removed = true;
            }
        }

        if (removeKey != -1) {
            announcementList.remove(removeKey);
        }

        if (!removed) {
            throw new DeleteAnnouncementError("no items found");
        }

        return listMyAnnouncement(userID);
    }

}
