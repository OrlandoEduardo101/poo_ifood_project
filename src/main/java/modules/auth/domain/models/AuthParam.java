package modules.auth.domain.models;

import java.util.HashMap;
import java.util.Map;

public class AuthParam {
    private String email;

    public AuthParam() {}

    public AuthParam(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Map toMap(){

        Map<String, String> map = new HashMap<String, String>();
        map.put("email", this.email);
        map.put("password", this.password);

        return map;
    }

    public AuthParam fromMap(Map<String, String> map){

        AuthParam model = new AuthParam();
        model.setEmail(map.get("email"));
        model.setPassword(map.get("password"));

        return model;
    }

}
