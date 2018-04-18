import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestSocketClient {
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			System.out.println("generator Client " + i);
			if (i % 50 == 0) {
				Thread.sleep(300l);
			}
			new TestSocketClient().app(i);
		}
	}

	public void app(int i) {
		new TestClient(i).start();
	}

	class TestClient extends Thread {

		private int i;

		public TestClient(int i) {
			this.i = i;
		}

		public TestClient() {
			super();
			// TODO Auto-generated constructor stub
		}

		public TestClient(Runnable target, String name) {
			super(target, name);
			// TODO Auto-generated constructor stub
		}

		public TestClient(Runnable target) {
			super(target);
			// TODO Auto-generated constructor stub
		}

		public TestClient(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}

		public TestClient(ThreadGroup group, Runnable target, String name, long stackSize) {
			super(group, target, name, stackSize);
			// TODO Auto-generated constructor stub
		}

		public TestClient(ThreadGroup group, Runnable target, String name) {
			super(group, target, name);
			// TODO Auto-generated constructor stub
		}

		public TestClient(ThreadGroup group, Runnable target) {
			super(group, target);
			// TODO Auto-generated constructor stub
		}

		public TestClient(ThreadGroup group, String name) {
			super(group, name);
			// TODO Auto-generated constructor stub
		}

		public void run() {
			Socket socket = null;
			BufferedReader in = null;
			PrintWriter write = null;
			try {
				socket = new Socket("127.0.0.1", 11177);
				System.out.println("connection[" + i + "] successful.");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				// ��ϵͳ��׼�����豸����BufferedReader����
				write = new PrintWriter(socket.getOutputStream());
				// ��Socket����õ��������������PrintWriter����
				// 3����ȡ������������ȡ�������˵���Ӧ��Ϣ
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// ��Socket����õ�����������������Ӧ��BufferedReader����
				// String readline;
				// readline = br.readLine(); // ��ϵͳ��׼�������һ�ַ���
				// while (!readline.equals("end")) {
				// // ���ӱ�׼���������ַ���Ϊ "end"��ֹͣѭ��
				// write.println(readline);
				// // ����ϵͳ��׼���������ַ��������Server
				// write.flush();
				// // ˢ���������ʹServer�����յ����ַ���
				// System.out.println("Client:" + readline);
				// // ��ϵͳ��׼����ϴ�ӡ������ַ���
				// System.out.println("Server:" + in.readLine());
				// // ��Server����һ�ַ���������ӡ����׼�����
				// readline = br.readLine(); // ��ϵͳ��׼�������һ�ַ���
				// }
				// // ����ѭ��
				int i = 0;
				while (true) {
					int byt = (int) (Math.random() * (95 - 65 + 1) + 65);
					write.print((char) byt);
					if (i++ >= 200) {
						i = 0;
						write.println();
						write.flush();
						Thread.sleep(3000);
					}
				}
				// 4���ر���Դ

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeWrite(write);
				closeIn(in);
				closeSocket(socket);
			}
		}

		public void closeWrite(PrintWriter write) {
			if (write != null) {
				write.close();
			}
		}

		public void closeIn(BufferedReader in) {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void closeSocket(Socket s) {
			try {
				if (s != null) {
					s.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
