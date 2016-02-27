package 使用Channel;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class RandomFileChannelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		FileChannel randomChannel = null;
		File file = new File("a.txt");
		// 创建一个RandomAccessFile对象
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		randomChannel=raf.getChannel();
		//将Channel中所有数据映射成ByteBuffer
		ByteBuffer buffer=randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		//把Channel记录指针移动到最后
		randomChannel.position(file.length());
		randomChannel.write(buffer);
		if(randomChannel!=null)
			randomChannel.close();
	}

}
