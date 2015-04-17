package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hosainfathelbab on 4/2/15.
 */
@RestController
public class TempTranslatePageService {

    @RequestMapping(value="/translation", produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String getIndexPage() {
        return "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>\n" +
                "  <script>\n" +
                "  $(document).ready(function() {\n" +
                "    $( \".myButton\" ).click(function() {\n" +
                "      var x = encodeURIComponent($('.translate-input').val());\n" +
                "      var grade = $('input[name=grade]:checked').val();\n" +
                "      $.ajax({\n" +
                "        url: \"/translateEncoded?grade=\"+grade+\"&&input=\"+x\n" +
                "      }).then(function(data) {\n" +
                "        $('.translate-output').html(data).text();\n" +
                "      });\n" +
                "\n" +
                "    });\n" +
                "\n" +
                "    $(\".downloadIt\").click(function(e) {\n" +
                "        e.preventDefault();  //stop the browser from following\n" +
                "        var x = encodeURIComponent($('.translate-output').val());\n" +
                "        window.location.href = '/downloadFile?input='+x;;\n" +
                "    });\n" +
                "\n" +
                "  });\n" +
                "  </script>\n" +
                "      <div>\n" +
                "          <div class=\"headerDiv\" style=\"width: 37%;margin: 0 auto;\">\n" +
                "          <h2>برايل ب العربي</h2>\n" +
                "          </div>\n" +
                "          <table>\n" +
                "            <tr>\n" +
                "              <td><textarea class=\"translate-input\"></textarea></td>\n" +
                "              <td align=\"center\">\n" +
                "                  <img src=\"http://icons.iconarchive.com/icons/custom-icon-design/pretty-office-13/72/Arrows-Sync-icon.png\"/>\n" +
                "              </td>\n" +
                "              <td><textarea readonly class=\"translate-output\"></textarea></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td></td>\n" +
                "              <td>\n" +
                "                <input type=\"radio\" name=\"grade\" value=\"1\" checked>Grade1 <br/>\n" +
                "                <input type=\"radio\" name=\"grade\" value=\"2\">Grade2\n" +
                "              </td>\n" +
                "              <td><button class=\"downloadIt\" style=\"float: right;\" >Download translation as .rtf</button></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td></td>\n" +
                "              <td align=\"center\">\n" +
                "                <button class=\"myButton\">Translate</button>\n" +
                "              </td>\n" +
                "              <td></td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "\n" +
                "\n" +
                "          <style type=\"text/css\">\n" +
                "            textarea {\n" +
                "              margin: 0px;\n" +
                "              width: 421px;\n" +
                "              height: 113px;\n" +
                "              border: 2px solid #32409E;\n" +
                "              border-radius: 10px;\n" +
                "              font-size: 1em;\n" +
                "            }\n" +
                "           .translate-output {\n" +
                "             background-color: rgb(250, 250, 214);\n" +
                "             font-size: 2em;\n" +
                "             font-weight: bold;\n" +
                "             border: 1px solid black;\n" +
                "           }\n" +
                "\n" +
                "           .myButton {\n" +
                "              width: 120px;\n" +
                "              height: 30px;\n" +
                "            }\n" +
                "          </style>\n" +
                "      </div>";
    }


    @RequestMapping(value = "/getSymbol", produces = "text/html; charset=UTF-8")
    public
    @ResponseBody
    String translate(@RequestParam(value = "input") String input) throws UnsupportedEncodingException {

        Map<String, String> brailleSymbolMapping = new HashMap<String, String>();
        brailleSymbolMapping.put("1","⠁");
        brailleSymbolMapping.put("2","⠂");
        brailleSymbolMapping.put("12","⠃");
        brailleSymbolMapping.put("3","⠄");
        brailleSymbolMapping.put("13","⠅");
        brailleSymbolMapping.put("23","⠆");
        brailleSymbolMapping.put("123","⠇");
        brailleSymbolMapping.put("4","⠈");
        brailleSymbolMapping.put("14","⠉");
        brailleSymbolMapping.put("24","⠊");
        brailleSymbolMapping.put("124","⠋");
        brailleSymbolMapping.put("34","⠌");
        brailleSymbolMapping.put("134","⠍");
        brailleSymbolMapping.put("234","⠎");
        brailleSymbolMapping.put("1234","⠏");
        brailleSymbolMapping.put("5","⠐");
        brailleSymbolMapping.put("15","⠑");
        brailleSymbolMapping.put("25","⠒");
        brailleSymbolMapping.put("125","⠓");
        brailleSymbolMapping.put("35","⠔");
        brailleSymbolMapping.put("135","⠕");
        brailleSymbolMapping.put("235","⠖");
        brailleSymbolMapping.put("1235","⠗");
        brailleSymbolMapping.put("45","⠘");
        brailleSymbolMapping.put("145","⠙");
        brailleSymbolMapping.put("245","⠚");
        brailleSymbolMapping.put("1245","⠛");
        brailleSymbolMapping.put("345","⠜");
        brailleSymbolMapping.put("1345","⠝");
        brailleSymbolMapping.put("2345","⠞");
        brailleSymbolMapping.put("12345","⠟");
        brailleSymbolMapping.put("6","⠠");
        brailleSymbolMapping.put("16","⠡");
        brailleSymbolMapping.put("26","⠢");
        brailleSymbolMapping.put("126","⠣");
        brailleSymbolMapping.put("36","⠤");
        brailleSymbolMapping.put("136","⠥");
        brailleSymbolMapping.put("236","⠦");
        brailleSymbolMapping.put("1236","⠧");
        brailleSymbolMapping.put("46","⠨");
        brailleSymbolMapping.put("146","⠩");
        brailleSymbolMapping.put("246","⠪");
        brailleSymbolMapping.put("1246","⠫");
        brailleSymbolMapping.put("346","⠬");
        brailleSymbolMapping.put("1346","⠭");
        brailleSymbolMapping.put("2346","⠮");
        brailleSymbolMapping.put("12346","⠯");
        brailleSymbolMapping.put("56","⠰");
        brailleSymbolMapping.put("156","⠱");
        brailleSymbolMapping.put("256","⠲");
        brailleSymbolMapping.put("1256","⠳");
        brailleSymbolMapping.put("356","⠴");
        brailleSymbolMapping.put("1356","⠵");
        brailleSymbolMapping.put("2356","⠶");
        brailleSymbolMapping.put("12356","⠷");
        brailleSymbolMapping.put("456","⠸");
        brailleSymbolMapping.put("1456","⠹");
        brailleSymbolMapping.put("2456","⠺");
        brailleSymbolMapping.put("12456","⠻");
        brailleSymbolMapping.put("3456","⠼");
        brailleSymbolMapping.put("13456","⠽");
        brailleSymbolMapping.put("23456","⠾");
        brailleSymbolMapping.put("123456","⠿");

        String output = "";
        for(String symbol : input.split(","))
            output += brailleSymbolMapping.get(symbol);

        return output;
    }
}
