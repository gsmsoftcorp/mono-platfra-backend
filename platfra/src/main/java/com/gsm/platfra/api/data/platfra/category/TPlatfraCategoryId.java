package com.gsm.platfra.api.data.platfra.category;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class TPlatfraCategoryId implements Serializable {
    private static final long serialVersionUID = 2490133677857663016L;
    @Size(max = 16)
    @NotNull
    @Column(name = "CATEGORY_CD", nullable = false, length = 16)
    private String categoryCd;
    
    @Size(max = 64)
    @NotNull
    @Column(name = "PLATFRA_ID", nullable = false, length = 64)
    private String platfraId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TPlatfraCategoryId entity = (TPlatfraCategoryId) o;
        return Objects.equals(this.platfraId, entity.platfraId) &&
            Objects.equals(this.categoryCd, entity.categoryCd);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(platfraId, categoryCd);
    }
    
}