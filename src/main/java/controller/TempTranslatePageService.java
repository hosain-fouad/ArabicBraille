package controller;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hosainfathelbab on 4/2/15.
 */
@RestController
public class TempTranslatePageService {

    @RequestMapping(value="/translation", produces = "text/html; charset=UTF-8")
    public @ResponseBody
    String getIndexPage() throws IOException {
        String text = Files.toString(new File("tempWebPage.html"), Charsets.UTF_8);
        return text;
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
