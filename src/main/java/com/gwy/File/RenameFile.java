//package com.gwy.File;
//
//import java.io.File;
//
///**
// * @Param
// * @return
// * @Date  2019/8/22 14:31
// * @Author guowenyao
// * @Description 重新命名文件夹的名字
// **/
//public class RenameFile {
//    private static final String OLDNAMES = "qianmi,ningpai";
//    private static final String NEWNAME = "supermarket";
//
//    static int count = 0;
//    static boolean isShowDir = false; //是否显示子文件夹的名称
//
//
//    public static void main(String[] args) {
//        String path = "G:/code/springSrc/ec-supermarket"; // 路径
//        getFileName(path);
//    }
//
//    /*
//     * 文件重命名
//     * file 原文件
//     * toFile 要修改为的文件
//     */
//    public static boolean renameFile(String file, String toFile) {
//
//        File toBeRenamed = new File(file);
//
//        File newFile = new File(toFile);
//
//        // 修改文件名
//        if (toBeRenamed.renameTo(newFile)) {
//            return true;
//        }
//        return false;
//    }
//
//    /*
//     * 文件夹下文件所有文件展示
//     */
//    public static void getFileName(String path) {
//        File f = new File(path);
//        File fa[] = f.listFiles(); //获取该文件对象下所有的目录和文件
//        for (int i = 0; i < fa.length; i++) {
//            File fs = fa[i];
////            String keyWord = "supermarket"; //修改文件名称的关键字
////            String newName = "supermarkt";
//            String[] oldNames = OLDNAMES.split(",");
//            String nameString = fs.getName();
//            for(String keyWord : oldNames){
//                if (nameString.contains(keyWord)) {
//                    //部分文件名修改
//                    nameString = nameString.replaceAll(keyWord, NEWNAME);
//                    if (renameFile(path + File.separator + fs.getName(), path + File.separator + nameString)) {
//                        System.out.println(++count + "  success ====> " + fs.getName() + "  重命名为 ： "
//                                + nameString);
//                    } else {
//                        System.err.println("  ===========>>>>>>>>>>>>>>> " + fs.getName() + "  重命名为 ： "
//                                + nameString + " 失败 <<<<<<<<<<=============");
//                    }
//                }
//            }
//            if (fs.isDirectory()) {
//
//                getFileName(fs.getAbsolutePath());//递归遍历子文件夹
//
//                if (isShowDir)
//                    System.out.println(fs.getName() + " [目录]");
//
//            }
//        }
//    }
//}
