package 使用Channel;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ReadFile {

	/**
	 * 若习惯传统IO“用竹筒多次重复取水”的过程，或担心Channel对应 的文件过大，使用map方法一次将所有文件内容映射到内存中引起性能下降
	 * 也可以使用Channel和Buffer传统的方式
	 */
	public static void main(String[] args) throws Exception {
		// 创建文件输入流
		FileInputStream fis = new FileInputStream("L:\\Users\\workspace\\Java新IO\\src\\使用Channel\\ReadFile.java");
		FileChannel channel = fis.getChannel();
		// 定义一个ByteBuffer对象，用于重复取水
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int has = 0;
		while ((has = channel.read(buffer)) != -1) {
			System.out.println("has:" + has);
			// 锁定Buffer空白区，防止读到null
			buffer.flip();
			Charset charset = Charset.forName("UTF-8");
			// 创建解码器
			CharsetDecoder decoder = charset.newDecoder();
			CharBuffer charBuffer = decoder.decode(buffer);
			System.out.println(charBuffer);
			// 将Buffer初始化，为下一次取数据做准备
			buffer.clear();
		}
		if (channel != null)
			channel.close();
	}

}
