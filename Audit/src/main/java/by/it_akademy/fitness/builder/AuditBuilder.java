package by.it_akademy.fitness.builder;

import by.it_akademy.fitness.enams.EntityType;
import by.it_akademy.fitness.storage.entity.Audit;


import java.util.MissingFormatArgumentException;
import java.util.UUID;

public class AuditBuilder {

    private UUID id;

    private Long dtCreate;

    private Long dtUpdate;

    private String user;

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

    public AuditBuilder setUser(String user) {
        this.user = user;
        return this;
    }

    public AuditBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public AuditBuilder setType(String type) {
        if (type.equals("PRODUCT")){
        this.type = EntityType.PRODUCT;
        }else throw new MissingFormatArgumentException("not valid EntityType");
        return this;
    }

    public AuditBuilder setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public Audit build() {
        return new Audit(id,dtCreate,dtUpdate,text,type,uid);
    }//user
}
