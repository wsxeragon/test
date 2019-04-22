package com.wsx.test.NettyTest.netty.private_protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

public class MarshallingCodecFactory {

    /**
     * 创建Jboss Marshalling解码器MarshallingDecoder
     */
    public static MyMarshallingDecoder buildMarshallingDecoder() {
        //首先通过Marshalling工具类的getProvidedMarshallerFactory静态方法获取MarshallerFactory实例
        //参数“serial”表示创建的是Java序列化工厂对象，它由jboss-marshalling-serial-1.3.0.CR9.jar提供。
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        //创建了MarshallingConfiguration对象
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        //将它的版本号设置为5
        configuration.setVersion(5);
        //然后根据MarshallerFactory和MarshallingConfiguration创建UnmarshallerProvider实例
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
        //最后通过构造函数创建Netty的MarshallingDecoder对象
        //它有两个参数，分别是UnmarshallerProvider和单个消息序列化后的最大长度。
        MyMarshallingDecoder decoder = new MyMarshallingDecoder(provider, 1024);
        return decoder;
    }


    /**
     * 创建Jboss Marshalling编码器MarshallingEncoder
     */
    public static MyMarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        //创建MarshallerProvider对象，它用于创建Netty提供的MarshallingEncoder实例
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
        //MarshallingEncoder用于将实现序列化接口的POJO对象序列化为二进制数组。
        MyMarshallingEncoder encoder = new MyMarshallingEncoder(provider);
        return encoder;
    }


    public static class MyMarshallingDecoder extends MarshallingDecoder{
        public MyMarshallingDecoder(UnmarshallerProvider provider,int len) {
            super(provider,len);
        }

        //将proteced方法变为public
        @Override
        public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
            return super.decode(ctx, in);
        }
    }

    public static class MyMarshallingEncoder  extends MarshallingEncoder {
        public MyMarshallingEncoder(MarshallerProvider provider) {
            super(provider);
        }

        //将proteced方法变为public
        @Override
        public void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
            super.encode(ctx, msg, out);
        }
    }
}