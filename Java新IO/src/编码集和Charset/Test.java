package 编码集和Charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.SortedMap;

public class Test {

	/**
	 * 编码Encode和解码Decode 把明文的字符串序列转换成计算机理解的字节序列(二进制文件，普通人看不懂)称为编码
	 * 把字节序列转化成普通人能看懂的明文字符串称为解码 GBK简体中文字符集 BIG5繁体中文字符集 ISO-8859-1：ISO拉丁字母表
	 * UTF-8：8位UCS转换格式
	 */
	public static void main(String[] args) throws Exception {
		// 获取全部字符集
		SortedMap<String, Charset> map = Charset.availableCharsets();
		for (String item : map.keySet()) {
			System.out.println(item + "----------:" + map.get(item));
		}
		// 创建简体中文对应的Charset
		Charset charset = Charset.forName("GBK");
		// 获取对应的编码器和解码器
		CharsetEncoder encoder = charset.newEncoder();
		CharsetDecoder decoder = charset.newDecoder();
		// 创建一个CharBuffer对象
		CharBuffer charBuffer = CharBuffer.allocate(8);
		charBuffer.put("车");
		charBuffer.put("汽车");
		charBuffer.flip();
		// 将CharBuffer中的字符串序列转换成字节序列
		ByteBuffer byteBuffer = encoder.encode(charBuffer);
		for (int i = 0; i < byteBuffer.capacity(); i++) {
			System.out.println("byteBuffer:" + byteBuffer.get(i));
		}
		// 将ByteBuffer的数据解码成字符序列
		System.out.println(decoder.decode(byteBuffer));
	}
}
