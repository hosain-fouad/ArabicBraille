package controller;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.RtfWriter2;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by hosainfathelbab on 4/2/15.
 */
@RestController
public class FileDownloaderService {

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response, @RequestParam(value="input") String input) throws IOException {

        String fileName = "translationFile"+System.currentTimeMillis()+".rtf";
        try {
            // Step 1: Create a new Document
            Document document = new Document();

            // Step 2: Create a new instance of the RtfWriter2 with the document
            //         and target output stream.
            RtfWriter2.getInstance(document, new FileOutputStream(fileName));

            // Step 3: Open the document.
            document.open();

            // Step 4: Add content to the document.
            document.add(new Paragraph(input));

            // Step 5: Close the document. It will be written to the target output stream.
            document.close();

            // get your file as InputStream
            InputStream is = new FileInputStream(fileName);
            response.setContentType("application/rtf");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            // copy it to response's OutputStream
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        } catch (DocumentException de) {
            // DocumentExceptions arise if you add content to the document before opening or
            // after closing the document.
            de.printStackTrace();
        }

        // delete the server after sending it , not to make the server full of unneeded files.
        try {
            File file = new File(fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
