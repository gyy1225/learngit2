import com.sun.deploy.util.BlackList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.

public class blackList {
    private String name;
    private String ID;

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }


/*public void setBlackList()
    {
        List blackList=new ArrayList();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("blackList.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                blackList.add(line);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }*/
    private class MyComparator implements Comparator<Integer>
   {
       public int compare(Integer o1, Integer o2)
       {
           if (o1 > o2)
           {
               return 1;
           }
           else if (o1 < o2)
           {
               return -1;
           }
           else
           {
               return 0;
           }
       }


   }
    public void writeIn(String str) throws IOException {
        String path1 = "E/伪熬测/newblacklist.txt";
        File file1 = new File(path1);
        if (!file1.exists()) file1.mkdir();
        String fileName = file1 + "/" + "test.txt";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

    public static void main(String[] args) {
        blackList myblacklist=new blackList();
        List aBlackList=new ArrayList();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("blackList.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                aBlackList.add(line);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        int []len=new int[13];
        for(int i=0;i<13;i++){
            String str1= (String) aBlackList.get(i);
            len[i]=str1.length();
            try {
                myblacklist.writeIn(str1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        aBlackList.sort(len,MyComparator);


   }


}
