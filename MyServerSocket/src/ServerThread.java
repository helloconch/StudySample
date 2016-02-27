import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

//负责处理每个线程通信的线程类
//输入流 读取Socket中的数据
//输出流向Socket中写数据
public class ServerThread implements Runnable {
	// 定义当前线程所处理的Socket
	Socket socket = null;
	// 该线程所处理的Socket所对应的输入流--读取数据
	BufferedReader br = null;
	// 该线程所处理的Socket所对应的输出流--发送数据
	PrintStream ps = null;

	public ServerThread(Socket socket) throws IOException {
		this.socket = socket;
		// 初始化该Socket对应的输入流
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		ps = new PrintStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		try {
			String content = null;
			// 采用循环不断从Socket中读取客户端发送过来的数据
			while ((content = readFromClient()) != null) {
				// 如果读到的行以CommonProtocol.USER_ROUND开始，并以其结束
				// 可以确定读到的是用户登录的用户名
				if (content.startsWith(CommonProtocol.USER_ROUND) && content.endsWith(CommonProtocol.USER_ROUND)) {
					// 得到真实消息
					String username = getRealMsg(content);
					// 如果用戶名重複
					if (MyServer.clients.containsKey(username)) {
						System.out.println("用户名重复");
						ps.println(CommonProtocol.NAME_REP);
					} else {
						System.out.println("成功");
						ps.println(CommonProtocol.LOGIN_SUCCESS);
						MyServer.clients.put(username, ps);
					}
				}

				// 如果读到的行以CommonProtocol.PRIVATE_ROUND开始，并以其结束
				// 可以确定是私聊信息，私聊信息只向特定输出流发送
				else if (content.startsWith(CommonProtocol.PRIVATE_ROUND) && content.endsWith(CommonProtocol.PRIVATE_ROUND)) {
					// 得到真实消息
					String userAndMsg = getRealMsg(content);
					System.out.println("检测到私有消息"+userAndMsg);
					// 以SPLIT_SIGN来分割字符串，前门部分是私聊用户，后面部分是聊天信息
					String toUser = userAndMsg.split(CommonProtocol.SPLIT_SIGN)[0];
					String msg = userAndMsg.split(CommonProtocol.SPLIT_SIGN)[1];
					// 找到发送者
					String key = MyServer.clients.getKeyByValue(ps);
					MyServer.clients.get(toUser).println(key + " say :" + msg);
				} else {
					String msg = getRealMsg(content);
					// 找到发送者
					String key = MyServer.clients.getKeyByValue(ps);
					for (PrintStream clientPs : MyServer.clients.valueSet()) {
						clientPs.println(key + " say: " + msg);
					}
				}

				// 遍历SocketList中的每一个Socket
				// 将读到的内容向每个Socket发送一次
				// for (Socket each : MyServer.socketList) {
				// PrintStream ps = new PrintStream(each.getOutputStream());
				// ps.println(content);
				// }
			}
		} catch (Exception e) {
			try {
				MyServer.clients.removeByValue(ps);
				if (br != null)
					br.close();
				if (ps != null)
					ps.close();
				if (socket != null)
					socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// 定义读取客户端数据的方法
	private String readFromClient() {
		try {
			return br.readLine();
		} catch (IOException e) {
			// 如果捕捉到异常，表明Socket对应的客户端已经关闭
			MyServer.socketList.remove(socket);
		}
		return null;
	}

	// 将读到的内容去掉前后的协议字符，恢复成真实数据
	public String getRealMsg(String content) {
		return content.substring(CommonProtocol.PROTOCOL_LEN, content.length() - CommonProtocol.PROTOCOL_LEN);
	}

}
