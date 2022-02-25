package co.com.carvajal.platform.modules.web.dataprovider.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.multipart.MultipartFile;
import co.com.carvajal.foundation.framework.stereotypes.DataProvider;
import co.com.carvajal.platform.crosscutting.domain.ValidationResponse;
import co.com.carvajal.platform.crosscutting.domain.constants.BasicConstants;
import co.com.carvajal.platform.crosscutting.messages.IndexMessages;
import co.com.carvajal.platform.modules.integration.validation.ApiValidationGateway;
import co.com.carvajal.platform.modules.web.dataprovider.IndexDataProvider;

/**
 *
 * @author dierodca
 *
 */
@DataProvider
public class IndexProxy implements IndexDataProvider {

    @Autowired
    private ApiValidationGateway validationGateway;

    @Override
    public String validate(final MultipartFile file, final String config) throws Exception {
        try {
            return this.validationGateway.validateDocument(
                    new String(Base64.encodeBase64(file.getBytes()), StandardCharsets.UTF_8),
                    config);
        } catch (final HttpStatusCodeException e) {
            return BasicConstants.ERROR.concat(e.getResponseBodyAsString());
        } catch (final Exception e) {
            throw e;
        }
    }

    @Override
    public ValidationResponse generateZipEntry(final ZipOutputStream zipOut,
            final MultipartFile file, final String content) throws IOException {

        final String newName = this.renameFile(file.getOriginalFilename());
        final String fileName = content.startsWith(BasicConstants.ERROR)
                ? BasicConstants.ERROR.concat(BasicConstants.UNDERSCORE).concat(newName)
                : newName;

        final ZipEntry zipEntry = new ZipEntry(fileName);
        zipEntry.setSize(content.getBytes(StandardCharsets.UTF_8).length);
        zipOut.putNextEntry(zipEntry);
        zipOut.write(content.getBytes(StandardCharsets.UTF_8));

        return ValidationResponse.builder().fileName(file.getOriginalFilename())
                .status(content.startsWith(BasicConstants.ERROR) ? BasicConstants.ERROR
                        : BasicConstants.PROCESSED)
                .build();
    }

    @Override
    public void generateZipFile(final ByteArrayOutputStream baos, final String config)
            throws IOException {
        if (baos.toByteArray().length > 0) {
            final Path rootPath = Paths.get(BasicConstants.UPLOADS)
                    .resolve(config.contains(BasicConstants.ORDERS.toLowerCase(Locale.US))
                            ? this.generateZipName(BasicConstants.ORDERS)
                            : this.generateZipName(BasicConstants.RETANN));
            try (FileOutputStream fos =
                    new FileOutputStream(rootPath.toAbsolutePath().toString())) {
                fos.write(baos.toByteArray());
            }

        }
    }

    private String renameFile(final String originalFilename) {

        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new NullPointerException(IndexMessages.ORIGINAL_FILE_NAME_NULL);
        }

        final int index = originalFilename.lastIndexOf(BasicConstants.POINT);
        if (index > 0) {
            return originalFilename.substring(0, index).concat(BasicConstants.JSON_EXTENSION);
        }
        return originalFilename;
    }

    private String generateZipName(final String name) {
        final String uuid = UUID.randomUUID().toString().replace(BasicConstants.MID_DASH,
                BasicConstants.EMPTY_STRING);
        return uuid.concat(BasicConstants.UNDERSCORE).concat(name)
                .concat(BasicConstants.ZIP_EXTENSION);
    }

}
