package Demo_Selenium;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Demo_Action.Json_Format_Tool;

public class Message_Output_Json {
    /**
     * 生成.json格式檔案
     */
    public static boolean createJsonFile(ArrayList<String> Array_Msg/*,String jsonString*/, String filePath, String fileName) {
        // 標記檔案生成是否成功
        boolean flag = true;

        // 拼接檔案完整路徑
        String fullPath = filePath + File.separator + fileName + ".json";

        // 生成json格式檔案
        try {
            // 保證建立一個新檔案
            File file = new File(fullPath);
            if (!file.getParentFile().exists()) { // 如果父目錄不存在，建立父目錄
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,刪除舊檔案
                file.delete();
            }
            file.createNewFile();

            String jsonString = "";
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            JsonObject root = new JsonObject();
            
            for (int i = 0; i < Array_Msg.size(); i++) {
            	jsonString = Array_Msg.get(i);
            	
            	if(jsonString.indexOf("'")!=-1){  
                    //將單引號轉義一下，因為JSON串中的字串型別可以單引號引起來的  
                    jsonString = jsonString.replaceAll("'", "\\'");  
                }  
                if(jsonString.indexOf("\"")!=-1){  
                    //將雙引號轉義一下，因為JSON串中的字串型別可以單引號引起來的  
                    jsonString = jsonString.replaceAll("\"", "\\\"");  
                }  
                  
                if(jsonString.indexOf("\r\n")!=-1){  
                    //將回車換行轉換一下，因為JSON串中字串不能出現顯式的回車換行  
                    jsonString = jsonString.replaceAll("\r\n", "\\u000d\\u000a");  
                }  
                if(jsonString.indexOf("\n")!=-1){  
                    //將換行轉換一下，因為JSON串中字串不能出現顯式的換行  
                    jsonString = jsonString.replaceAll("\n", "\\u000a");  
                }  
                
                root.addProperty("userid","發言者ID");
                root.addProperty("username","發言者姓名");
                root.addProperty("cus_userid","被回覆者ID");
                root.addProperty("cus_username","被回覆者姓名");
                root.addProperty("message",jsonString);
                root.addProperty("time","留言時間");
                root.addProperty("video_url","影片的URL");
                
                //假设身高是double，我们取小数点后一位
//                double height=185.5345;
//                root.addProperty("height",(double)(Math.round(height*10)/10.0));
//                JsonArray array = new JsonArray();
//                JsonObject major1 = new JsonObject();
//                major1.addProperty("留言",jsonString);
//                major1.addProperty("job2","doctor");
//                JsonObject major2 = new JsonObject();
//                major2.addProperty("job3","teacher");
//                major2.addProperty("job4","student");
//                array.add(major1);
//                array.add( major2);
//                root.add("major",array);
                //假设位置x,y都是double型的,现在对他们取整
//                double x=30.0045;
//                double y=30.1123;
//                JsonObject houloc=new JsonObject();
//                houloc.addProperty("x",Math.round(x));
//                houloc.addProperty("y",Math.round(y));
//                root.add("houseLocation",houloc);
                
                // 格式化json字串
//                jsonString = Json_Format_Tool.formatJson(jsonString);
                jsonString = Json_Format_Tool.formatJson(root.toString());
                
                // 將格式化後的字串寫入檔案
                write.write(jsonString);
            }
            
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        // 返回是否成功的標記
        return flag;
    }
    
    
       
}