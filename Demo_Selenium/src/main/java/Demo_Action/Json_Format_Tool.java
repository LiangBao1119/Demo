package Demo_Action;

public class Json_Format_Tool {
    
//	單位縮排字串。
     
    private static String SPACE = "   ";

//  返回格式化JSON字串。
//  @param json 未格式化的JSON字串。
//  @return 格式化的JSON字串。
     
    public static String formatJson(String json) {
        StringBuffer result = new StringBuffer();

        int length = json.length();
        int number = 0;
        char key = 0;

        // 遍歷輸入字串。
        for (int i = 0; i < length; i++) {
            // 1、獲取當前字元。
            key = json.charAt(i);
            // 2、如果當前字元是前方括號、前花括號做如下處理：
            if ((key == '[') || (key == '{')) {
                // （1）如果前面還有字元，並且字元為“：”，列印：換行和縮排字元字串。
                if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                    result.append('\n');
                    result.append(indent(number));
                }

                // （2）列印：當前字元。
                result.append(key);
                // （3）前方括號、前花括號，的後面必須換行。列印：換行。
                result.append('\n');
                // （4）每出現一次前方括號、前花括號；縮排次數增加一次。列印：新行縮排。
                number++;
                result.append(indent(number));
                // （5）進行下一次迴圈。
                continue;
            }

            // 3、如果當前字元是後方括號、後花括號做如下處理：
            if ((key == ']') || (key == '}')) {
                // （1）後方括號、後花括號，的前面必須換行。列印：換行。
                result.append('\n');
                // （2）每出現一次後方括號、後花括號；縮排次數減少一次。列印：縮排。
                number--;
                result.append(indent(number));
                // （3）列印：當前字元。
                result.append(key);
                // （4）如果當前字元後面還有字元，並且字元不為“，”，列印：換行。
                if (((i + 1) < length) && (json.charAt(i + 1) != ',')) {
                    result.append('\n');
                }
                // （5）繼續下一次迴圈。
                continue;
            }

            // 4、如果當前字元是逗號。逗號後面換行，並縮排，不改變縮排次數。
            /*if ((key == ',')) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }*/

            // 5、列印：當前字元。
            result.append(key);
        }

        return result.toString();
    }
    
//    返回指定次數的縮排字串。每一次縮排三個空格，即SPACE。
      
//    @param number 縮排次數。
//    @return 指定縮排次數的字串。
    
    private static String indent(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append(SPACE);
        }
        return result.toString();
    }
}
