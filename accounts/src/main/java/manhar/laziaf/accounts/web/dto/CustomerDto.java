package manhar.laziaf.accounts.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends BaseItem {

    private String name;
    private String email;
    private String mobileNumber;

    @Builder
    public CustomerDto(OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, String name, String email,
                       String mobileNumber) {
        super(createdDate, lastModifiedDate);
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
}
