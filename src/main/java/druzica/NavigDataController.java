package druzica;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Juraj on 14.12.2016.
 */
@Controller
public class NavigDataController {

   @RequestMapping(method = RequestMethod.POST, path = "/uploadFile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {
       String cesta = "src\\main\\resources\\file\\" + file.getOriginalFilename();
       Files.copy(file.getInputStream(), Paths.get(cesta), StandardCopyOption.REPLACE_EXISTING); // kopiruje do adresara s projektom java
       //File convFile = new Funkcie().multipartToFile(file);          // convertuje multipartfile na file
       //String skuska = new Funkcie().vypocetNavData(cesta);             // precita file

       return "redirect:/stanica";
   }

}