package co.com.carvajal.platform.crosscutting.messages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Mensajes
 *
 * @author dierodca
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IndexMessages {

    public static final String ERROR_SELECT_FILE = "Debe seleccionar un archivo";
    public static final String ORIGINAL_FILE_NAME_NULL = "El documento no posee un nombre original";
    public static final String VALIDATING = "Validando: ";
    public static final String END_PROCESS_VALIDATION_FILES =
            "finaliza proceso de validación de archivos";
    public static final String START_PROCESS_VALIDATION_FILES =
            "Inicia proceso de validación de archivos";
}
