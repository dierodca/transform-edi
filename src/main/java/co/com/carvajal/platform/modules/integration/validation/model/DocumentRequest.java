package co.com.carvajal.platform.modules.integration.validation.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Modelo de parametros para consumir el api de validaciones
 *
 * @author dierodca
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequest implements Serializable {

    private static final long serialVersionUID = -7690349068049191580L;
    private String setupId;
    private String file;
}
