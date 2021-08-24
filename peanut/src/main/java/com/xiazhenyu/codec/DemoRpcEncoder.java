package com.xiazhenyu.codec;

import com.xiazhenyu.Constants;
import com.xiazhenyu.compress.Compressor;
import com.xiazhenyu.compress.CompressorFactory;
import com.xiazhenyu.protocol.Header;
import com.xiazhenyu.protocol.Message;
import com.xiazhenyu.serialization.Serialization;
import com.xiazhenyu.serialization.SerializationFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DemoRpcEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {

        Header header = message.getHeader();

        byteBuf.writeShort(header.getMagic());
        byteBuf.writeByte(header.getVersion());
        byteBuf.writeByte(header.getExtraInfo());
        byteBuf.writeLong(header.getMessageId());
        Object content = message.getContent();
        if (Constants.isHeartBeat(header.getExtraInfo())) {
            byteBuf.writeInt(0);
            return;
        }
        Serialization serialization = SerializationFactory.get(header.getExtraInfo());
        Compressor compressor = CompressorFactory.get(header.getExtraInfo());
        byte[] payload = compressor.compress(serialization.serialize(content));
        byteBuf.writeInt(payload.length);
        byteBuf.writeBytes(payload);

    }
}