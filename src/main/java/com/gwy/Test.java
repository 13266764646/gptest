//package com.gwy;
//
//import com.gwy.bean.User;
//
//import java.net.URL;
//
//public class Test {
//
//   /* static{
//        try{
//        Class aa = Class.forName(User.class.getName());
//        System.out.println("-------"+aa.getName());
//        }catch (Exception e){
//            System.out.println("-------"+e);
//        }
//        try {
//            URL bb =  ClassLoader.getSystemClassLoader().getResource(User.class.getName());
//            System.out.println("---------123456--------"+bb);
//        }catch (Exception e){
//            System.out.println("--------123------"+e);
//        }
//    }*/
//
//    public static void main(String[] args) throws CloneNotSupportedException {
//    /*    String  receivedOrderCode = "F2019102377853486-1";
//        String aa =  receivedOrderCode.substring(0,receivedOrderCode.indexOf("-"));
//        System.out.println("---------------"+aa);
//
//
//*/
//       User user = new User();
//       user.setId("1");
//       user.setName("zhangsan");
//       user.setTel("13266764646");
//       User user1 = (User)user.clone();
//       System.out.println("--------user----------"+user+"--------"+user1);
//       System.out.println(user == user1);
//       System.out.println(user.equals(user1));
//       user.setName("lishi");
//        System.out.println("--------user----------"+user.toString()+"--------"+user1.toString());
//    }
//}