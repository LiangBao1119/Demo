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
    
    public static boolean createJsonFile(String [][] Array
    		/*,String jsonString*/, String filePath, String fileName) {
        // 標記檔案生成是否成功
        boolean flag = true;
        
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
            String jsonString_Msg = "";
            String jsonString_Name = "";
            String jsonString_Cus_id = "";
            String jsonString_Cus_Name = "";
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            JsonObject root = new JsonObject();
            
            for (int i = 0; i < Array.length; i++) {
            	jsonString_Name = Array [0][i];
            	jsonString_Cus_id = Array[1][i];
            	jsonString_Cus_Name = Array [2][i];
            	jsonString_Msg = Array [3][i];
            	
            	jsonString_Name = Check_jsonString(jsonString_Name);
            	jsonString_Cus_id = Check_jsonString(jsonString_Cus_id);
            	jsonString_Cus_Name = Check_jsonString(jsonString_Cus_Name);
            	jsonString_Msg = Check_jsonString(jsonString_Msg);
            	
            	System.out.println(jsonString_Name);
                System.out.println(jsonString_Cus_id);
                System.out.println(jsonString_Cus_Name);
                System.out.println(jsonString_Msg);
                
                root.addProperty("userid","發言者ID");
                root.addProperty("username",jsonString_Name);
                root.addProperty("cus_userid",jsonString_Cus_id);
                root.addProperty("cus_username",jsonString_Cus_Name);
                root.addProperty("message",jsonString_Msg);
                root.addProperty("time","留言時間");
                root.addProperty("video_url","影片的URL");
                
                //假設身高是double，我们取小數點後一位
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
                //假設位置x,y都是double型的,现在對他們取整
//                double x=30.0045;
//                double y=30.1123;
//                JsonObject houloc=new JsonObject();
//                houloc.addProperty("x",Math.round(x));
//                houloc.addProperty("y",Math.round(y));
//                root.add("houseLocation",houloc);
                
                // 格式化json字串
//                jsonString = Json_Format_Tool.formatJson(jsonString);
//              jsonString_Name = Json_Format_Tool.formatJson(root.toString());
//              jsonString_Cus_id = Json_Format_Tool.formatJson(root.toString());
//              jsonString_Cus_Name = Json_Format_Tool.formatJson(root.toString());
//              jsonString_Msg = Json_Format_Tool.formatJson(root.toString());
                jsonString = Json_Format_Tool.formatJson(root.toString());
                
                // 將格式化後的字串寫入檔案
                write.write(jsonString);
//              write.write(jsonString_Name);
//              write.write(jsonString_Cus_id);
//              write.write(jsonString_Cus_Name);
//              write.write(jsonString_Msg);
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
    
    public static String Check_jsonString(String jsonString) {
		
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
    	return jsonString;
	}
       
}