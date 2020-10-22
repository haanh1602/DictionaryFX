package helper;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TranslateToHTML {
    private JSONParser jsonParser = new JSONParser();

    public String parserHTML(String textParser) throws ParseException {
        String jsonStringExplain;
        String jsonStringPronounce = null;
        JSONArray jsonArray = (JSONArray) jsonParser.parse(textParser);

        JSONArray jsonArrayWord = (JSONArray) jsonArray.get(0);
        jsonStringExplain = (String) ((JSONArray) jsonArrayWord.get(0)).get(0);

        if (jsonArrayWord.size() > 1) {
            JSONArray jsonMoreWord = (JSONArray) jsonArrayWord.get(1);
            if (jsonMoreWord.get(3) != null)
                jsonStringPronounce = (String) jsonMoreWord.get(3);
        }
        if(jsonStringPronounce == null) {
            jsonStringPronounce = "null";
        }
        if(jsonStringExplain == null) {
            jsonStringExplain = "null";
        }
        if (jsonArray.get(1) == null) {
            String newResult = jsonStringExplain + "\n" + "  /" + jsonStringPronounce + "/";
            return newResult;
        }
        String result = jsonStringExplain + "\n" + " /" + jsonStringPronounce + "/";

        return result;
    }
}
