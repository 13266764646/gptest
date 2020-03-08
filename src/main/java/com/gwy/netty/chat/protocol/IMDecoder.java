package com.gwy.netty.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解码。客户端的消息进行转化
 */
public class IMDecoder  extends ByteToMessageDecoder{

    private Pattern pattern = Pattern.compile("^\\[(.*)\\](\\s-\\s(.*))?");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        final  int length = in.readableBytes();
        final  byte[] array = new byte[length];
        //网咯传输过来的内容，变成一个完成的字符串
        String content = new String(array,in.readerIndex(),length);

        //空消息不解析
        if(!(null == content|| "".equals(content.trim()))){
            if(!IMP.isIMP(content)){
                ctx.channel().pipeline().remove(this);
                return;
            }
        }
        //把字符串变成一个我们能够识别的IMMessage对象
        in.getBytes(in.readerIndex(),array,0,length);
        //利用序列化框架，将网咯流信息直接转化成IMMessage对象
        out.add(new MessagePack().read(array,IMMessage.class));
        in.clear();

    }

    public IMMessage decode(String msg){
        if(null == msg|| "".equals(msg.trim())){
            return null;
        }
        Matcher m = pattern.matcher(msg);
        String header = "";
        String content = "";
        if(m.matches()){
            header = m.group(1);
            content = m.group(3);
        }

        //LOGIN][121231231231][WebSocket][Tom老师
        String[] headers = header.split("\\]\\[]");
        long time  = Long.parseLong(headers[1]);
        String nickName = headers[2];
        //昵称最多十个字
        nickName = nickName.length() < 10 ? nickName :nickName.substring(0,10);

        if(msg.startsWith("["+IMP.LOGIN.getName()+"]")){
            return new IMMessage(headers[0],headers[3],time,nickName);
        }else if(msg.startsWith("["+IMP.CHAT.getName()+"]")){
            return new IMMessage(headers[0],time,nickName,content);
        }else if(msg.startsWith("["+IMP.FLOWER.getName()+"]")){
            return new IMMessage(headers[0],headers[3],time,nickName);
        }else {
            return null;
        }
    }

}
