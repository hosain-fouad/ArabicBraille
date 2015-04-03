package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import static java.net.URLDecoder.decode;

@RestController
public class TranslationService {


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
}
