import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NClient {
	// 定义检测SocketChannel的Selector对象
	private Selector selector = null;
	// 定义处理编码和解码的字符集
	private Charset charset = Charset.forName("UTF-8");
	// 客户端SocketChannel
	private SocketChannel sc = null;
	private final String ADDRESS = "127.0.0.1";
	private final int PORT = 3000;

	public void init() throws IOException {
		selector = Selector.open();
		InetSocketAddress isa = new InetSocketAddress(ADDRESS, PORT);
		// 调用open静态方法创建连接到指定主机的SocketChannel
		sc = SocketChannel.open(isa);
		// 设置sc以非阻塞方式工作
		sc.configureBlocking(false);
		// 将SocketChannel对象注册到指定Selector
		sc.register(selector, SelectionKey.OP_READ);
		new Thread(new ClientT()).start();
		// 创建键盘输入流
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			// 读取键盘输入
			String line = scan.nextLine();
			// 将键盘输入的内容输出到SocketChannel中
			sc.write(charset.encode(line));
		}
	}

	private class ClientT implements Runnable {

		@Override
		public void run() {
			try {
				while (selector.select() > 0) {
					// 遍历每个有可用IO操作Channel对应SelectionKey
					for (SelectionKey sk : selector.selectedKeys()) {
						selector.selectedKeys().remove(sk);
						// 如果该SelectionKey对应的Channel中有可读的数据
						if (sk.isReadable()) {
							// 使用NIO读取Channel中的数据
							SocketChannel sc = (SocketChannel) sk.channel();
							ByteBuffer buff = ByteBuffer.allocate(1024);
							String content = "";
							while (sc.read(buff) > 0) {
								sc.read(buff);
								buff.flip();
								content += charset.decode(buff);
							}
							// 打印输出流读取的内容
							System.out.println("聊天信息:" + content);
							// 为下一次读取做准备
							sk.interestOps(SelectionKey.OP_READ);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
