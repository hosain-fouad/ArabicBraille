package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

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
                "\n" +
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
                "                <td><textarea class=\"translate-output\"></textarea></td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "            <div class=\"buttonDiv\" style=\"width: 36%;margin: 0 auto;\">\n" +
                "              <a href=\"#\" class=\"myButton\">Translate</a>\n" +
                "            </div>\n" +
                "\n" +
                "            <style type=\"text/css\">\n" +
                "              textarea {\n" +
                "                margin: 0px;\n" +
                "                width: 421px;\n" +
                "                height: 113px;\n" +
                "              }\n" +
                "             .translate-output {\n" +
                "               background-color: rgb(250, 250, 214);\n" +
                "               font-size: 3em;\n" +
                "               font-weight: bold;\n" +
                "               border: 1px solid black;\n" +
                "             }\n" +
                "             .myButton {\n" +
                "              \t-moz-box-shadow: 0px 1px 0px 0px #f0f7fa;\n" +
                "              \t-webkit-box-shadow: 0px 1px 0px 0px #f0f7fa;\n" +
                "              \tbox-shadow: 0px 1px 0px 0px #f0f7fa;\n" +
                "              \tbackground:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #33bdef), color-stop(1, #019ad2));\n" +
                "              \tbackground:-moz-linear-gradient(top, #33bdef 5%, #019ad2 100%);\n" +
                "              \tbackground:-webkit-linear-gradient(top, #33bdef 5%, #019ad2 100%);\n" +
                "              \tbackground:-o-linear-gradient(top, #33bdef 5%, #019ad2 100%);\n" +
                "              \tbackground:-ms-linear-gradient(top, #33bdef 5%, #019ad2 100%);\n" +
                "              \tbackground:linear-gradient(to bottom, #33bdef 5%, #019ad2 100%);\n" +
                "              \tfilter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#33bdef', endColorstr='#019ad2',GradientType=0);\n" +
                "              \tbackground-color:#33bdef;\n" +
                "              \t-moz-border-radius:6px;\n" +
                "              \t-webkit-border-radius:6px;\n" +
                "              \tborder-radius:6px;\n" +
                "              \tborder:1px solid #057fd0;\n" +
                "              \tdisplay:inline-block;\n" +
                "              \tcursor:pointer;\n" +
                "              \tcolor:#ffffff;\n" +
                "              \tfont-family:arial;\n" +
                "              \tfont-size:15px;\n" +
                "              \tfont-weight:bold;\n" +
                "              \tpadding:6px 24px;\n" +
                "              \ttext-decoration:none;\n" +
                "              \ttext-shadow:0px -1px 0px #5b6178;\n" +
                "              }\n" +
                "              .myButton:hover {\n" +
                "              \tbackground:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #019ad2), color-stop(1, #33bdef));\n" +
                "              \tbackground:-moz-linear-gradient(top, #019ad2 5%, #33bdef 100%);\n" +
                "              \tbackground:-webkit-linear-gradient(top, #019ad2 5%, #33bdef 100%);\n" +
                "              \tbackground:-o-linear-gradient(top, #019ad2 5%, #33bdef 100%);\n" +
                "              \tbackground:-ms-linear-gradient(top, #019ad2 5%, #33bdef 100%);\n" +
                "              \tbackground:linear-gradient(to bottom, #019ad2 5%, #33bdef 100%);\n" +
                "              \tfilter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#019ad2', endColorstr='#33bdef',GradientType=0);\n" +
                "              \tbackground-color:#019ad2;\n" +
                "              }\n" +
                "              .myButton:active {\n" +
                "              \tposition:relative;\n" +
                "              \ttop:1px;\n" +
                "              }\n" +
                "\n" +
                "            </style>\n" +
                "        </div>\n" +
                "\n";
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

}
