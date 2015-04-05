package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tables.Grade1Map;
import translators.Translator;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import static java.net.URLDecoder.decode;

@RestController
public class TranslationService {

    @Autowired
    Translator grade1Translator;

    @Autowired
    Translator grade2Translator;

    @RequestMapping(value = "/translate", produces = "text/html; charset=UTF-8")
    public
    @ResponseBody
    String translate(@RequestParam(value = "input") String input, @RequestParam(value = "grade") String grade) throws UnsupportedEncodingException {
        String translation = "";
        switch (Integer.parseInt(grade)){
            case 1:
                translation = grade1Translator.translate(input);
                break;
            case 2:
                translation = grade2Translator.translate(input);
                break;
        }

        return translation;
    }

    @RequestMapping(value = "/translateEncoded", produces = "text/html; charset=UTF-8")
    public
    @ResponseBody
    String translateEnocoded(@RequestParam(value = "input") String input, @RequestParam(value = "grade") String grade) throws UnsupportedEncodingException {
        input = decode(input, "ASCII");
        return translate(input, grade);
    }
}
