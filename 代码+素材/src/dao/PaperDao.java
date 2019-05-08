package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class PaperDao {
    //生成缓存库
    private static HashSet<String> hashPaper = new HashSet<>();
    private static HashMap<String, Paper> hashMappaper = new HashMap<>();
    private static ArrayList arrayListpapers=null;
    static {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("src//filedata//Paper.txt"));
            String papers = bufferedReader.readLine();
            while (papers != null) {
                hashPaper.add(papers);
                papers = bufferedReader.readLine();
            }
            arrayListpapers = new ArrayList(hashPaper);
            for (int i = 0; i < arrayListpapers.size(); i++) {
                String[] paper = arrayListpapers.get(i).toString().split("<ar>");
                String title = paper[0];
                String answer = paper[1];
                if (paper.length == 2) {
                    hashMappaper.put(title, new Paper(title, answer));
                } else {
                    String icon = paper[2];
                    hashMappaper.put(title, new Paper(title, answer, icon));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //设置一个方法，随机生成n道题
    private static Random random = new Random();
    public static ArrayList<Paper> getPapers(int index) {
        HashSet<Paper> paper_idx = new HashSet<>();
        while (paper_idx.size() < index) {
               String[] pas= arrayListpapers.get(random.nextInt(hashMappaper.size())).toString().split("<ar>");
               if (pas.length==2) paper_idx.add(new Paper(pas[0],pas[1]));
            System.out.println(paper_idx.size());
               if (pas.length==3) {
                paper_idx.add(new Paper(pas[0],pas[1],pas[2]));
            }
        }
        //重新构建回来，方便索引
        return new ArrayList<>(paper_idx);
    }
    private static HashMap<String,Paper> getHashPaper(){
        return hashMappaper;
    }
}