package by.it_akademy.fitness.builder;

import by.it_akademy.fitness.enams.EStatus;
import by.it_akademy.fitness.storage.entity.User;

import java.util.UUID;

public class UserBuilder {
    
    private UUID id;
    
    private Long dtCrate;
    
    private Long dtUpdate;

    private String username;

    private String login;

    private String password;

    private String role;

    private EStatus status;

    private String activationCode;

    private UserBuilder() {
    }
    
    public static UserBuilder create() { 
        return new UserBuilder();
    }

    public UserBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public UserBuilder setDtCrate(Long dtCrate) {
        this.dtCrate = dtCrate;
        return this;
    }

    public UserBuilder setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public UserBuilder setStatus(EStatus status) {
        this.status = status;
        return this;
    }

    public UserBuilder setActivationCode(String  activationCode) {
        this.activationCode = activationCode;
        return this;
    }
    public User build() {
        return  new User(id,dtCrate,dtUpdate, username,login,activationCode,password,role,status);
    }
}
