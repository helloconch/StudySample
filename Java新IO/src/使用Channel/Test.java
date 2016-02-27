package 使用Channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class Test {

	/**
	 * Channel类似于传统的流对象 Channel可以直接将指定文件的部分或全部直接映射成Buffer
	 * 程序不能直接访问Channel中的数据，包括读取、写入都不行，Channel只能和Buffer
	 * 进行交互，也就是说，如果要从Channel中取得数据，必须先用Buffer从Channel中取出
	 * 一些数据，然后让程序从Buffer中取出这些数据。如果要将程序中的数据写入Channel，
	 * 一样先让程序将数据放入Buffer中，程序再将Buffer里的输入写入Channel中
	 */
	public static void main(String[] args) throws Exception {
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		File file = new File("L:/Users/workspace/Java新IO/src/使用Channel/Test.java");
		// 创建FileInputStream，已该文件输入流创建FileChannel
		inChannel = new FileInputStream(file).getChannel();
		// 将FileChannel里的全部数据映射成ByteBuffer
		MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		// 使用GBK的字符集來創建解码器
		Charset charset = Charset.forName("UTF-8");
		//以文件输出流创建
		outChannel=new FileOutputStream("a.txt").getChannel();
		//直接將buffer里的数据全部输出
		outChannel.write(buffer);
		System.out.println("---------------------");
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
		//再次调用buffer的clear()方法，复原limit、position的位置
		buffer.clear();
		//创建解码器CharsetDecoder的对象
		CharsetDecoder decoder=charset.newDecoder();
		//使用解码器将ByteBuffer转换成CharBuffer
		CharBuffer charBuffer=null;
		try {
			charBuffer = decoder.decode(buffer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(charBuffer);
		if(inChannel!=null)
			inChannel.close();
		if(outChannel!=null)
			outChannel.close();
	}

}
