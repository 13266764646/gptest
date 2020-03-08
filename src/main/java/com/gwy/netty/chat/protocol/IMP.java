package com.gwy.netty.chat.protocol;

/**
 * 聊天的协议
 */
public enum IMP {
    /**
     * 系统消息
     */
    SYSTEM("SYSTEM"),
    LOGIN("LOGIN"),
    LOGOUT("LOGOUT"),
    CHAT("CHAT"),
    FLOWER("FLOWER");

    private String name;

    /**
     * 判断消息内容是不是自己能认识的协议内容
     * @param content
     * @return
     */
    public static  boolean isIMP(String content){
       //用一句正则就可以
        return content.matches("^\\[ SYSTEM|LOGIN|LOGOUT|CHAT|FLOWER\\]");
    }

    IMP(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
