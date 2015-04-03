package controller;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Paragraph;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.rtf.RtfWriter2;

import static java.net.URLDecoder.*;


@RestController
public class TranslationService {

    @RequestMapping(value="/translation", produces = "text/html; charset=UTF-8")
    public @ResponseBody String getIndexPage() {
        return "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>\n" +
                "    <script>\n" +
                "    $(document).ready(function() {\n" +
                "      $( \".myButton\" ).click(function() {\n" +
                "        var x = encodeURIComponent($('.translate-input').val());\n" +
                "        $.ajax({\n" +
                "          url: \"/translateEncoded?input=\"+x\n" +
                "        }).then(function(data) {\n" +
                "          $('.translate-output').html(data).text();\n" +
                "        });\n" +
                "      });\n" +
                "      \n" +
                "      $( \"#downloadTranslation\" ).click(function() {\n" +
                "        var x = encodeURIComponent($('.translate-output').val());\n" +
                "        $.ajax({\n" +
                "          url: \"/downloadFile?input=\"+x\n" +
                "        }).then(function() {\n" +
                "          \n" +
                "        });\n" +
                "      });\n" +
                "      \n" +
                "\n" +
                "      $(\".downloadIt\").click(function(e) {\n" +
                "          e.preventDefault();  //stop the browser from following\n" +
                "          var x = encodeURIComponent($('.translate-output').val());      \n" +
                "          window.location.href = '/downloadFile?input='+x;;\n" +
                "      });\n" +
                "      \n" +
                "    });\n" +
                "    </script>\n" +
                "        <div>\n" +
                "            <div class=\"headerDiv\" style=\"width: 37%;margin: 0 auto;\">\n" +
                "            <h2>برايل ب العربي</h2>\n" +
                "            </div>\n" +
                "            <table>\n" +
                "              <tr>\n" +
                "                <td><textarea class=\"translate-input\"></textarea></td>\n" +
                "                <td><img src=\"http://icons.iconarchive.com/icons/custom-icon-design/pretty-office-13/72/Arrows-Sync-icon.png\"/></td>\n" +
                "                <td><textarea readonly class=\"translate-output\"></textarea></td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td></td>\n" +
                "                <td></td>\n" +
                "                <td><button class=\"downloadIt\" style=\"float: right;\" >Download translation as .rtf</button></td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "            <div class=\"buttonDiv\" style=\"width: 36%;margin: 0 auto;\">\n" +
                "              <button class=\"myButton\">Translate</button>\n" +
                "            </div>\n" +
                "\n" +
                "            <style type=\"text/css\">\n" +
                "              textarea {\n" +
                "                margin: 0px;\n" +
                "                width: 421px;\n" +
                "                height: 113px;\n" +
                "                border: 2px solid #32409E;\n" +
                "\t              border-radius: 10px;\n" +
                "                font-size: 2em;\n" +
                "              }\n" +
                "             .translate-output {\n" +
                "               background-color: rgb(250, 250, 214);\n" +
                "               font-size: 2em;\n" +
                "               font-weight: bold;\n" +
                "               border: 1px solid black;\n" +
                "             }    \n" +
                "                  \n" +
                "             .myButton {\n" +
                "                width: 120px;\n" +
                "                height: 30px;\n" +
                "              }    \n" +
                "            </style>\n" +
                "        </div>";
    }

    @RequestMapping(value="/translate", produces = "text/html; charset=UTF-8")
    public @ResponseBody String translate (@RequestParam(value="input") String input) throws UnsupportedEncodingException {
        String result = decode(input, "ASCII");
        HashMap<Character, Character> table = new HashMap<Character, Character>();
        table.put('ي','⠊');
        table.put('و','⠺');
        table.put('ه','⠓');
        table.put('ن','⠝');
        table.put('م','⠍');
        table.put('ل','⠇');
        table.put('ك','⠅');
        table.put('ق','⠟');
        table.put('ف','⠋');
        table.put('غ','⠣');
        table.put('ع','⠷');
        table.put('ظ','⠿');
        table.put('ط','⠾');
        table.put('ض','⠫');
        table.put('ص','⠯');
        table.put('ش','⠩');
        table.put('س','⠎');
        table.put('ز' ,'⠵');
        table.put('ر','⠗');
        table.put('ذ','⠮');
        table.put('د','⠙');
        table.put('خ','⠭');
        table.put('ح','⠱');
        table.put('ج','⠚');
        table.put('ث','⠹');
        table.put('ت','⠞');
        table.put('ب','⠃');
        table.put('ا','⠁');
        table.put('َ','⠂');
        table.put('ِ','⠑');
        table.put('ُ','⠥');
        table.put('ً','⠆');
        table.put('ٍ','⠔');
        table.put('ٌ','⠢');
        table.put('ّ','⠠');
        table.put('ْ','⠒');
        table.put('.','⠲');
        table.put('،','⠐');


        StringBuffer sb = new StringBuffer();

        for (char c : input.toCharArray()){
            if(table.containsKey(c)){
                sb.append(table.get(c));
            }
            else{
                sb.append(c);
            }
        }

    	return sb.toString();
    }

    @RequestMapping(value="/translateEncoded", produces = "text/html; charset=UTF-8")
    public @ResponseBody String translateEnocoded (@RequestParam(value="input") String input) throws UnsupportedEncodingException {
        input = decode(input, "ASCII");
        return translate(input);
    }

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
//            doc.write(response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        } catch (DocumentException de) {
            // DocumentExceptions arise if you add content to the document before opening or
            // after closing the document.
            de.printStackTrace();
        }

        try {
            File file = new File(fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
