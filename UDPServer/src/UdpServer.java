import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
	public static final int PORT = 30000;
	// 定义每个数据报的最大大小是4k
	private static final int DATA_LEN = 4096;
	// 定义该服务器使用的DatagramSocket
	private DatagramSocket socket = null;
	// 定义接受网络数据的字节数组
	byte[] inBuff = new byte[DATA_LEN];
	// 以指定字节数组创建准备接受数据的DatagramPacket对象
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	// 定义一个用于发送的DatagramPacket对象
	private DatagramPacket outPacket;
	// 定义一个字符串数组,服务器发送该数组的元素
	String[] books = new String[] { "姥姥语录", "单反入门", "ABC英语" };

	public void init() throws IOException {
		try {
			socket = new DatagramSocket(PORT);
			// 采用循环接受数据
			for (int i = 0; i < 1000; i++) {
				// 读取Socket中的数据，读到的数据放在inPacket所封装的字节数组里
				socket.receive(inPacket);
				// 判断inPacket.getData()和inBuff是否在同一个数组中
				System.out.println(inPacket.getData() == inBuff);
				// 将接受到的内容转换成字符串后输出
				System.out.println("接受到:" + new String(inBuff, 0, inPacket.getLength()));
				// 从字符串数组中取出一个元素作为发送的数据
				byte[] sendData = books[i % 3].getBytes();
				// 以指定字节数组作为发送数据，以刚接受到的DatagramPacket的源SocketAddress
				// 作为目标SocketAddress创建DatagramPacket
				outPacket = new DatagramPacket(sendData, sendData.length, inPacket.getSocketAddress());
				// 发送数据
				socket.send(outPacket);
			}
		} finally {
			if (socket != null)
				socket.close();
		}
	}
}
