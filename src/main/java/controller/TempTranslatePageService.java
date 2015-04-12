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
                "      if(grade==2) {\n" +
                "        alert(\"Sorry, Grade2 is under development. Grade1 might be used instead.\");\n" +
                "      }\n" +
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

        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("1","⠁");
        mapping.put("2","⠂");
        mapping.put("12","⠃");
        mapping.put("3","⠄");
        mapping.put("13","⠅");
        mapping.put("23","⠆");
        mapping.put("123","⠇");
        mapping.put("4","⠈");
        mapping.put("14","⠉");
        mapping.put("24","⠊");
        mapping.put("124","⠋");
        mapping.put("34","⠌");
        mapping.put("134","⠍");
        mapping.put("234","⠎");
        mapping.put("1234","⠏");
        mapping.put("5","⠐");
        mapping.put("15","⠑");
        mapping.put("25","⠒");
        mapping.put("125","⠓");
        mapping.put("35","⠔");
        mapping.put("135","⠕");
        mapping.put("235","⠖");
        mapping.put("1235","⠗");
        mapping.put("45","⠘");
        mapping.put("145","⠙");
        mapping.put("245","⠚");
        mapping.put("1245","⠛");
        mapping.put("345","⠜");
        mapping.put("1345","⠝");
        mapping.put("2345","⠞");
        mapping.put("12345","⠟");
        mapping.put("6","⠠");
        mapping.put("16","⠡");
        mapping.put("26","⠢");
        mapping.put("126","⠣");
        mapping.put("36","⠤");
        mapping.put("136","⠥");
        mapping.put("236","⠦");
        mapping.put("1236","⠧");
        mapping.put("46","⠨");
        mapping.put("146","⠩");
        mapping.put("246","⠪");
        mapping.put("1246","⠫");
        mapping.put("346","⠬");
        mapping.put("1346","⠭");
        mapping.put("2346","⠮");
        mapping.put("12346","⠯");
        mapping.put("56","⠰");
        mapping.put("156","⠱");
        mapping.put("256","⠲");
        mapping.put("1256","⠳");
        mapping.put("356","⠴");
        mapping.put("1356","⠵");
        mapping.put("2356","⠶");
        mapping.put("12356","⠷");
        mapping.put("456","⠸");
        mapping.put("1456","⠹");
        mapping.put("2456","⠺");
        mapping.put("12456","⠻");
        mapping.put("3456","⠼");
        mapping.put("13456","⠽");
        mapping.put("23456","⠾");
        mapping.put("123456","⠿");

        String output = "";
        for(String symbol : input.split(","))
            output += mapping.get(symbol);

        return output;
    }
}
