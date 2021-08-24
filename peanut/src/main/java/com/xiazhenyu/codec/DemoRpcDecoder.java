package com.xiazhenyu.codec;

import com.xiazhenyu.Constants;
import com.xiazhenyu.compress.Compressor;
import com.xiazhenyu.compress.CompressorFactory;
import com.xiazhenyu.protocol.Header;
import com.xiazhenyu.protocol.Message;
import com.xiazhenyu.protocol.Request;
import com.xiazhenyu.protocol.Response;
import com.xiazhenyu.serialization.Serialization;
import com.xiazhenyu.serialization.SerializationFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DemoRpcDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {

        if (byteBuf.readableBytes() < Constants.HEAD_SIZE) {
            return;
        }

        byteBuf.markReaderIndex();
        short magic = byteBuf.readShort();
        if (magic != Constants.MAGIC) {
            byteBuf.resetReaderIndex();
            throw new RuntimeException("magic number error:" + magic);
        }

        byte version = byteBuf.readByte();
        byte extraInfo = byteBuf.readByte();
        long messageId = byteBuf.readLong();
        int size = byteBuf.readInt();
        Object body = null;
        if (!Constants.isHeartBeat(extraInfo)) {
            if (byteBuf.readableBytes() < size) {
                byteBuf.resetReaderIndex();
                return;
            }
            byte[] payload = new byte[size];
            byteBuf.readBytes(payload);
            Serialization serialization = SerializationFactory.get(extraInfo);
            Compressor compressor = CompressorFactory.get(extraInfo);
            if (Constants.isRequest(extraInfo)) {
                body = serialization.deserialize(compressor.unCompress(payload), Request.class);
            } else {
                body = serialization.deserialize(compressor.unCompress(payload), Response.class);
            }
        }
        Header header = new Header(magic, version, extraInfo, messageId, size);
        Message message = new Message(header, body);
        out.add(message);
    }

}