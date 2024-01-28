package com.gsm.platfra.api.entity.base;

public interface AuditBaseEntity {
    
    void setRegUserId(String crtrId);
    void setModUserId(String uptrId);
    String getRegUserId();
    String getModUserId();
}
