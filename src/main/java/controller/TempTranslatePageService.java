package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hosainfathelbab on 4/2/15.
 */
@RestController
public class TempTranslatePageService {

    @RequestMapping(value="/translation", produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String getIndexPage() {
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

}
