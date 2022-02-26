package co.com.carvajal.platform.modules.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import co.com.carvajal.platform.crosscutting.domain.ValidationResponse;
import co.com.carvajal.platform.crosscutting.domain.constants.BasicConstants;
import co.com.carvajal.platform.crosscutting.messages.IndexMessages;
import co.com.carvajal.platform.modules.web.usecase.IndexProcess;

/**
 * Index Controller
 *
 * @author dierodca
 *
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {



    private List<ValidationResponse> lstFiles;

    @Autowired
    private IndexProcess process;

    @PostConstruct
    public void init() {
        this.lstFiles = new ArrayList<>();
    }

    @ModelAttribute("options")
    public List<String> options() {
        final List<String> response = new ArrayList<>();
        response.add(BasicConstants.CONFIG_ORDERS);
        response.add(BasicConstants.CONFIG_RETANN);
        return response;
    }

    @GetMapping()
    public String getIndex(final Model model) {
        this.setListFiles(model, this.lstFiles);
        this.setErrorMessage(model, BasicConstants.EMPTY_STRING);
        return BasicConstants.INDEX_PAGE;
    }

    @PostMapping(params = "submit")
    public String uploadFile(final List<MultipartFile> files, final String config,
            final Model model) {

        if (files.get(0) == null || files.get(0).getOriginalFilename() == null) {
            this.setListFiles(model, this.lstFiles);
            this.setErrorMessage(model, IndexMessages.ERROR_SELECT_FILE);
            return BasicConstants.INDEX_PAGE;
        }

        try {
            this.setErrorMessage(model, BasicConstants.EMPTY_STRING);
            this.lstFiles = this.process.processDocuments(files, config);
            if (this.lstFiles.isEmpty()) {
                this.setErrorMessage(model, IndexMessages.API_EDC_NOT_FOUND);
            }
            this.setListFiles(model, this.lstFiles);

        } catch (final IOException e) {
            this.setListFiles(model, this.lstFiles);
            this.setErrorMessage(model, e.getMessage());
        }

        return BasicConstants.INDEX_PAGE;
    }

    @PostMapping(params = "clean")
    public String cleanFiles(final Model model) {
        this.lstFiles.clear();
        this.setErrorMessage(model, BasicConstants.EMPTY_STRING);
        this.setListFiles(model, this.lstFiles);
        return BasicConstants.INDEX_PAGE;
    }

    private void setListFiles(final Model model, final List<ValidationResponse> value) {
        model.addAttribute(BasicConstants.RESULT_FILES_ATTRIBUTE, value);
    }

    private void setErrorMessage(final Model model, final String message) {
        model.addAttribute(BasicConstants.ERROR_ATTRIBUTE, message);
    }
}
