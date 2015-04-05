package tables;

import java.util.Collections;
import java.util.Map;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
public class Grade2Table {

    private static Map<String, Grade2Appreviation> grade2Table;

    static{

    }

    public static Map<String, Grade2Appreviation> getTable() {
        return Collections.unmodifiableMap(grade2Table);
    }
}
