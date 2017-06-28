package util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.junit.Test;
import pojo.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by changyu on 2017/6/26 0026.
 */
public class Junit_test {
    //@Test
    public void test_01()  {
        BufferedReader bufferedReader=null;
        try {
             bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/changyu/Desktop/炫生活java岗位笔试.docx"),"gb2312"));
             while (bufferedReader.readLine()!=null){
                System.out.println(bufferedReader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
   // @Test
    public void test_02(){
        try {
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("doctor_user_logo");
            MongoCollection<Document> list = mongoDatabase.getCollection("logo_info");
            //插入文档
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            Document document = new Document("title", "MongoDB2").
                    append("description", "database2").
                    append("likes", 102).
                    append("by", "changyu");
            list.insertOne(document);
            System.out.println("文档插入成功");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    @Test
    public void test_03(){
        Logger log=Logger.getLogger(Junit_test.class);
        User user=new User();
        user.setUser_name("changyu");
        user.setPhone("177");
        User user2=new User();
        user.setUser_name("chan12121gyu");
        user.setPhone("1772121");
        List<User> list=new ArrayList<User>();
        list.add(user);
        list.add(user2);
        log.info(list.toString());
        JSONArray jsonArray=JSONArray.fromObject(list);
        log.info(jsonArray.toString());
    }
}
