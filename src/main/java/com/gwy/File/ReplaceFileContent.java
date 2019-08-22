package com.gwy.File;

import java.io.*;

/**
 * @Param
 * @return
 * @Date  2019/8/22 14:29
 * @Author guowenyao
 * @Description
 **/
public class ReplaceFileContent {

    private static final String OLDCHART = "qianqian,ningning";
    private static final String NEWCHART = "supermarket";
    /**
     * 主方法测试
     * @param args
     * @author
     * @update 2013-6-26 下午1:36:31
     */
    public static void main(String[] args) {
        String filePath = "G:/code/springSrc/ec-supermarket/"; //给我你要读取的文件夹路径
        File outPath = new File("G:/code/springSrc/ec-supermarket/"); //随便给一个输出文件夹的路径(不存在也可以)
        readFolder(filePath,outPath);
    }

    /**
     * 读取文件夹
     * @return
     */
    public static void readFolder(String filePath,File outPath){
        try {
            //读取指定文件夹下的所有文件
            File file = new File(filePath);
            if (!file.isDirectory()) {
                System.out.println("---------- 该文件不是一个目录文件 ----------");
            } else if (file.isDirectory()) {
                System.out.println("---------- 很好，这是一个目录文件夹 ----------");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filePath + "/" + filelist[i]);
                    //String path = readfile.getPath();//文件路径
                    String absolutepath = readfile.getAbsolutePath();//文件的绝对路径
                    String filename = readfile.getName();//读到的文件名
                    readFile(absolutepath,filename,i,outPath);//调用 readFile 方法读取文件夹下所有文件
                }
                System.out.println("---------- 所有文件操作完毕 ----------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件夹下的文件
     * @return
     */
    public static void readFile(String absolutepath,String filename,int index,File outPath){
        try{
            if("target".contains(absolutepath)){
                return;
            }
            File file = new File(absolutepath);
            if(file != null && file.isDirectory()) {
                String outAbsolutePath = outPath.getAbsolutePath();
                String handledPath = outAbsolutePath+"/"+filename.replaceAll("\\\\", "/");
                File file1 = new File(handledPath.toString());
                file1.mkdirs();
                System.out.println("路径是目录");//如果是文件夹(Midir)
               readFolder(absolutepath,file1);
            }else {
                System.out.println("路径不是目录"); //不是文件夹
              /*  if (!absolutepath.contains(".java")) {
                    return ;
                }*/
                BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(absolutepath)));//数据流读取文件
                StringBuffer strBuffer = new StringBuffer();
//                String empty = "";
//                String tihuan = "";
                for (String temp = null; (temp = bufReader.readLine()) != null; temp = null) {
//                    if (temp.indexOf("\\.java") > 0) { //判断当前行是否存在想要替换掉的字符 -1表示存在
//                        tihuan = temp.substring(0, 9);//这里截取多长自己改
                        String[] oldChartArray  = OLDCHART.split(",");
                        for(String oldchart : oldChartArray){
                            temp = new String(temp.getBytes("utf-8"),"utf-8").replaceAll(oldchart, NEWCHART);//替换为你想要的东东
                        }
//                   }
                    strBuffer.append(temp);
                    strBuffer.append(System.getProperty("line.separator"));//行与行之间的分割
                }
                bufReader.close();
                if (outPath.exists() == false) { //检查输出文件夹是否存在，若不存在先创建
                    outPath.mkdirs();
                    System.out.println("已成功创建输出文件夹：" + outPath);
                }
                PrintWriter printWriter = new PrintWriter(outPath + "\\" + filename);//替换后输出的文件位置（切记这里的E:/ttt 在你的本地必须有这个文件夹）
                printWriter.write(strBuffer.toString().toCharArray());
                printWriter.flush();
                printWriter.close();
                System.out.println("第 " + (index + 1) + " 个文件   " + absolutepath + "  已成功输出到    " + outPath + "\\" + filename);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//    // 获取编码格式 gb2312,UTF-16,UTF-8,Unicode,UTF-8
//    public static String getCode(String path) throws Exception {
//        InputStream inputStream = new FileInputStream(path);
//        byte[] head = new byte[3];
//        inputStream.read(head);
//        String code = "GBK"; // 或GBK
//        if (head[0] == -1 && head[1] == -2)
//            code = "UTF-16";
//        else if (head[0] == -2 && head[1] == -1)
//            code = "Unicode";
//        else if (head[0] == -17 && head[1] == -69 && head[2] == -65)
//            code = "UTF-8";
//        inputStream.close();
//        return code;
//    }


}
