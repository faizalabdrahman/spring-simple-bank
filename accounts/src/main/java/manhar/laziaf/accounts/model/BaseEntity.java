package manhar.laziaf.accounts.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @Column(updatable = false)
    private String createdBy;

    @UpdateTimestamp
    @Column(insertable = false)
    private Timestamp lastModifiedDate;

    @Column(insertable = false)
    private String modifiedBy;
}
