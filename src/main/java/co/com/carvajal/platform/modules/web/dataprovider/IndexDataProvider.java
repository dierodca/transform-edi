package co.com.carvajal.platform.modules.web.dataprovider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import org.springframework.web.multipart.MultipartFile;
import co.com.carvajal.platform.crosscutting.domain.ValidationResponse;

/**
 * Proxy interface
 *
 * @author dierodca
 *
 */
public interface IndexDataProvider {

    public String validate(final MultipartFile file, final String config) throws IOException;

    public ValidationResponse generateZipEntry(final ZipOutputStream zipOut,
            final MultipartFile file, final String content) throws IOException;

    public void generateZipFile(final ByteArrayOutputStream baos, final String config)
            throws IOException;

    public boolean isInvalidURI();

}
