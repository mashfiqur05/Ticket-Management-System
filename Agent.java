// package Classes;


public class Agent {
    private String name;
    private String age;
    private String mobile;
    private String email;
    private String password;
    private String userType;

    public Agent (String name, String age, String mobile, String email, String password, String userType){
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    /// set and get method

    public void setName (String name){
        this.name = name;
    }
    public void setAge(String age){
        this.age = age;
    }
    public void setMobile (String mobile){
        this.mobile = mobile;
    }
    public void setEmail (String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setUserType (String userType){
        this.userType = userType;
    }

    public String getName(){
        return name;
    }
    public String getAge(){
        return age;
    }
    public String getMobile(){
        return mobile;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getUserType(){
        return userType;
    }
}
