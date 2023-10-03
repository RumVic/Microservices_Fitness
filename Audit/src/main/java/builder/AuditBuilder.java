package builder;

import enams.EntityType;
import org.springframework.security.core.userdetails.User;
import storage.entity.Audit;


import java.util.UUID;

public class AuditBuilder {

    private UUID id;

    private Long dtCreate;

    private Long dtUpdate;

    private User user;

    private String text;

    private EntityType type;

    private String uid;

    private AuditBuilder() {
    }
    
    public static AuditBuilder create () {
        return new AuditBuilder();
    }

    public AuditBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public AuditBuilder setDtCreate(Long dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public AuditBuilder setDtUpdate(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public AuditBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public AuditBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public AuditBuilder setType(EntityType type) {
        this.type = type;
        return this;
    }

    public AuditBuilder setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public Audit build() {
        return new Audit(id,dtCreate,dtUpdate,user,text,type,uid);
    }
}
