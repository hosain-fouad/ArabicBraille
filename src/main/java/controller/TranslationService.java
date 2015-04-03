package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tables.Grade1Map;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import static java.net.URLDecoder.decode;

@RestController
public class TranslationService {

    @RequestMapping(value = "/translate", produces = "text/html; charset=UTF-8")
    public
    @ResponseBody
    String translate(@RequestParam(value = "input") String input) throws UnsupportedEncodingException {
        HashMap<Character, Character> table = Grade1Map.table;
        StringBuffer sb = new StringBuffer();

        for (char c : input.toCharArray()) {
            if (table.containsKey(c)) {
                sb.append(table.get(c));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    @RequestMapping(value = "/translateEncoded", produces = "text/html; charset=UTF-8")
    public
    @ResponseBody
    String translateEnocoded(@RequestParam(value = "input") String input) throws UnsupportedEncodingException {
        input = decode(input, "ASCII");
        return translate(input);
    }
}
