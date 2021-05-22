package modules.auth.external.datasources;

import modules.auth.domain.errors.LoginUserFailure;
import modules.auth.domain.errors.RegisterUserFailure;
import modules.auth.domain.models.AuthParam;
import modules.auth.domain.models.UserModel;
import modules.auth.infra.datasources.IAuthDatasource;

import java.util.HashMap;
import java.util.Map;

public class AuthDatasource implements IAuthDatasource {


    private static IAuthDatasource instance;

    private AuthDatasource() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static AuthDatasource getInstance(){
        if (instance == null) {
            instance = new AuthDatasource();
        }
        return (AuthDatasource) instance;
    }

    UserModel user1 = new UserModel("User 1", "User 1", "123.456.789-00", "user1@user.com", "user city", "123456");
    UserModel user2 = new UserModel("User 2", "User 2", "123.456.789-00", "user2@user.com", "user city", "123456");
    UserModel user3 = new UserModel("User 3", "User 3", "123.456.789-00", "user3@user.com", "user city", "123456");
    Map<Integer, Map<String, String>> usersList = new HashMap<Integer, Map<String, String>>();



    public void fillDataBase() {
        user1.setId(0);
        user2.setId(1);
        user3.setId(2);
        usersList.put(0, user1.toMap());
        usersList.put(1, user2.toMap());
        usersList.put(2, user3.toMap());
    }

    @Override
    public UserModel registerUser(UserModel userModel) throws RegisterUserFailure {
        if (usersList.isEmpty()) fillDataBase();
        int id = usersList.size();
        int keyLogged = -1;
        userModel.setId(id);
        usersList.put(id, userModel.toMap());

        keyLogged = getKeyLogged(keyLogged, userModel.getEmail(), userModel.getPassword());

        if(keyLogged == -1){
            throw new RegisterUserFailure("Fail on authenticate new user");
        }

        return UserModel.fromMap(usersList.get(keyLogged));
    }

    @Override
    public UserModel loginUser(AuthParam authParam) throws LoginUserFailure {
        if (usersList.isEmpty()) fillDataBase();
        int keyLogged = -1;
        keyLogged = getKeyLogged(keyLogged, authParam.getEmail(), authParam.getPassword());

        if(keyLogged == -1){
            throw new LoginUserFailure("Invalid params, try again.");
        }

        return UserModel.fromMap(usersList.get(keyLogged));
    }

    private int getKeyLogged(int keyLogged, String email, String password) {
        for (Map.Entry<Integer, Map<String, String>> entry : usersList.entrySet()) {
            int key = entry.getKey();
            Map<String, String> value = entry.getValue();
            String localMail = value.get("email");
            String localPassword = value.get("password");
            if(localMail.equalsIgnoreCase(email)  && localPassword.equalsIgnoreCase(password)){
                keyLogged = key;
                return keyLogged;
            }
            //System.out.println(String.format("key: %s | value: %s", key, value));
        }
        return keyLogged;
    }
}
