package com.gsm.platfra.api.data.base;

import com.gsm.platfra.api.data.account.TAccount;
import com.gsm.platfra.system.security.context.UserContextUtil;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import static io.micrometer.common.util.StringUtils.isBlank;

public class AuditBaseEntityListener {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PrePersist
    public void prePersist(Object object) {
        if (object instanceof AuditBaseEntity) {
            Assert.notNull(object, "Entity must not be null.");
            AuditBaseEntity auditBaseEntity = (AuditBaseEntity) object;
            if (UserContextUtil.getUserContext() != null) {
                String regUserId = (StringUtils.hasText(auditBaseEntity.getRegUserId()) && object instanceof TAccount)
                    ? auditBaseEntity.getRegUserId()
                    : UserContextUtil.getUserContext().getUserId();
                String modUserId = (StringUtils.hasText(auditBaseEntity.getModUserId()) && object instanceof TAccount)
                    ? auditBaseEntity.getModUserId()
                    : UserContextUtil.getUserContext().getUserId();
                auditBaseEntity.setRegUserId(regUserId);
                auditBaseEntity.setModUserId(modUserId);
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("UserContext is null.");
                }
            }
        }
    }

    @PreUpdate
    public void preUpdate(Object object) {
        if (object instanceof AuditBaseEntity) {
            Assert.notNull(object, "Entity must not be null.");
            AuditBaseEntity auditBaseEntity = (AuditBaseEntity) object;
            if (UserContextUtil.getUserContext() != null) {
                auditBaseEntity.setModUserId(UserContextUtil.getUserContext().getUserId());
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("UserContext is null.");
                }
            }
        }
    }

}
