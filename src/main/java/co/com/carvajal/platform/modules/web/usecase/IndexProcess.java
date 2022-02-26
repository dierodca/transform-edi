package co.com.carvajal.platform.modules.web.usecase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;
import co.com.carvajal.foundation.framework.stereotypes.UseCase;
import co.com.carvajal.platform.crosscutting.domain.ValidationResponse;
import co.com.carvajal.platform.crosscutting.domain.constants.BasicConstants;
import co.com.carvajal.platform.crosscutting.messages.IndexMessages;
import co.com.carvajal.platform.modules.web.dataprovider.IndexDataProvider;
import lombok.extern.log4j.Log4j2;

/**
 * Clase para procesar los archivos cargados
 *
 * @author dierodca
 *
 */
@Log4j2
@UseCase
public class IndexProcess {


    @Autowired
    @Qualifier("indexProxy")
    private IndexDataProvider indexProxy;

    public List<ValidationResponse> processDocuments(final List<MultipartFile> files,
            final String config) throws IOException {

        log.info(IndexMessages.START_PROCESS_VALIDATION_FILES);
        final List<ValidationResponse> lstResultValidation = new ArrayList<>();

        if (this.indexProxy.isInvalidURI()) {
            log.error(IndexMessages.API_EDC_NOT_ACCESS);
            return lstResultValidation;
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {

            files.forEach(file -> {
                log.info(IndexMessages.VALIDATING.concat(file.getOriginalFilename()));

                try {
                    lstResultValidation.add(this.indexProxy.generateZipEntry(zipOut, file,
                            this.indexProxy.validate(file, config)));
                } catch (final Exception e) {
                    log.error(e.getMessage());
                    lstResultValidation
                            .add(ValidationResponse.builder().fileName(file.getOriginalFilename())
                                    .status(BasicConstants.NOT_PROCESSED).build());
                }

            });
        }
        this.indexProxy.generateZipFile(baos, config);
        log.info(IndexMessages.END_PROCESS_VALIDATION_FILES);
        return lstResultValidation;
    }

}
