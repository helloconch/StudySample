import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class MulticastSocketT implements Runnable {

	// 使用常量作为本程序的多点广播IP地址
	private static final String BROADCAST_IP = "230.0.0.1";
	// 使用常量作为本程序的多点广播目的的端口
	private static final int PORT = 30000;
	// 定义每个数据报的最大大小为4k
	private static final int DATA_LEN = 1024 * 4;
	// 定义本程序的MulticastSocket实例
	private MulticastSocket socket = null;
	private InetAddress broadcastAddress = null;
	private Scanner scan = null;
	// 定义接受网路数据的字节数组
	byte[] inBuff = new byte[DATA_LEN];
	// 以指定字节数组创建准备接受数据的DatagramPacket对象
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	// 定义一个用于发送的DatagramPacket对象
	private DatagramPacket outPacket = null;

	public void init() throws IOException {
		// 创建用于发送、接受数据的MulticastSocket对象
		// 因为该MulticastSocket对象需要接受，所以指定端口
		socket = new MulticastSocket(PORT);
		broadcastAddress = InetAddress.getByName(BROADCAST_IP);
		// 将该socket加入指定的多点广播地址
		socket.joinGroup(broadcastAddress);
		// 设置本MulticastSocket发送的数据报被回送到本身
		socket.setLoopbackMode(false);
		// 初始化发送用的DatagramSocket，它包含一个长度为0的字节数组
		outPacket = new DatagramPacket(new byte[0], 0, broadcastAddress, PORT);
		new Thread(this).start();
		// 创建键盘输入流
		scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			try {
				// 将键盘输入的一行字符串转换字节数组
				byte[] buff = scan.nextLine().getBytes();
				// 设置发送用的DatagramPackt里的字节数据
				outPacket.setData(buff);
				// 发送数据报
				socket.send(outPacket);
			} finally {
				if (socket != null)
					socket.close();
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				// 读取Socket中的数据，读到数据放在inPacket所封装的字节数组
				socket.receive(inPacket);
				System.out.println("聊天信息:" + new String(inBuff, 0, inPacket.getLength()));
			} catch (IOException e) {
				try {
					if (socket != null) {
						// 让该Socket离开该多点IP广播地址
						socket.leaveGroup(broadcastAddress);
						socket.close();
					}
					System.exit(1);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
