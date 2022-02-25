package co.com.carvajal.platform.crosscutting.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para obtener la respuesta de procesamiento
 *
 * @author dierodca
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse implements Serializable {

    private static final long serialVersionUID = 3005195509757783711L;
    private String fileName;
    private String status;
}
