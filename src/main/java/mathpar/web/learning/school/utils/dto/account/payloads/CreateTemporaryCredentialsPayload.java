package mathpar.web.learning.school.utils.dto.account.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTemporaryCredentialsPayload {
    private long userId;
    private String email;
    private String institutionType;
}
