package com.gsm.platfra.api.data.base;

import com.gsm.platfra.system.security.context.UserContextUtil;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class AuditBaseEntityListener {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PreUpdate
	public void beforeAnyUpdate(Object object) {

		if(object instanceof AuditBaseEntity) {
			Assert.notNull(object, "Entity must not be null.");
			if(UserContextUtil.getUserContext() != null) {
				((AuditBaseEntity)object).setModUserId(UserContextUtil.getUserContext().getUserId());
			}else {
				if(log.isDebugEnabled()) {
					log.debug("UserContext is null.");
				}
			}
		}
	}
	
	@PrePersist
	public void prePersist(Object object) {
		if(object instanceof AuditBaseEntity) {
			Assert.notNull(object, "Entity must not be null.");
			if(UserContextUtil.getUserContext() != null) {
				((AuditBaseEntity)object).setRegUserId(UserContextUtil.getUserContext().getUserId());
				((AuditBaseEntity)object).setModUserId(UserContextUtil.getUserContext().getUserId());
			}else {
				if (log.isDebugEnabled()) {
					log.debug("UserContext is null.");
				}
			}
		}
	}
}
