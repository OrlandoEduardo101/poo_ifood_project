package modules.auth.domain.models;

import java.util.HashMap;
import java.util.Map;

public class UserModel {

    public UserModel() {}

    public UserModel(String name, String undername, String CPF, String email, String city, String password) {
        this.name = name;
        this.undername = undername;
        this.CPF = CPF;
        this.email = email;
        this.city = city;
        this.password = password;
    }

    private String name;
    private String undername;
    private String CPF;
    private String email;
    private String city;
    private String password;
    private String confirmPassword;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUndername() {
        return undername;
    }

    public void setUndername(String undername) {
        this.undername = undername;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isValidPassword() {
        return getPassword().equalsIgnoreCase(getConfirmPassword());
    }



    public Map toMap(){

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", String.valueOf(this.id));
        map.put("name", this.name);
        map.put("undername", this.undername);
        map.put("CPF", this.CPF);
        map.put("email", this.email);
        map.put("city", this.city);
        map.put("password", this.password);

        return map;
    }

    public static UserModel fromMap(Map<String, String> map){

        UserModel model = new UserModel();
        model.setId(Integer.parseInt(map.get("id")));
        model.setName(map.get("name"));
        model.setUndername(map.get("undername"));
        model.setCPF(map.get("CPF"));
        model.setCity(map.get("city"));
        model.setEmail(map.get("email"));
        model.setPassword(map.get("password"));

        return model;
    }

}
