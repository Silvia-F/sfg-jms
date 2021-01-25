package guru.springframework.sfgjms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HelloWorldMessage implements Serializable {

    private UUID id;

    private String message;

    // Best practice when the class implements Serializable. Both the interface and the attribute are not essential for this context.
    private final long serialVersionUID = -2159059784752250475L;
}
