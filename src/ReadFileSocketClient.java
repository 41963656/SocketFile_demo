import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ReadFileSocketClient {
	private static final String FILE_NAME = "d:\\record.log";
	private static final String IP_ADDR = "127.0.0.1";
	private static final int PORT = 11177;
	private static final int BUFFER_SIZE = 512;

	public static void main(String[] args) {
		new ReadFileSocketClient().createSocketClinet();
	}

	public void createSocketClinet() {
		Socket socket = null;
		OutputStream os = null;
		try {
			socket = new Socket(IP_ADDR, PORT);
			os = socket.getOutputStream();
			this.ReadTextFile(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void ReadTextFile(OutputStream os) {
		InputStream in = null;
		byte[] bytes = new byte[BUFFER_SIZE];
		File file = new File(FILE_NAME);
		int offset = 0;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			while ((offset = in.read(bytes, 0, bytes.length)) != -1) {
				os.write(bytes, 0, offset);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}
}
