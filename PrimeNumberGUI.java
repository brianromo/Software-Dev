package socket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class PrimeNumberGUI implements ActionListener {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				constructGUI();
			}
		});

	}
	static JLabel result = new JLabel("");
	static JTextField f1 = new JTextField();
	
	public static void constructGUI(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Prime Checker");
		frame.setLayout(new GridLayout(5, 2));
		frame.add(new JLabel("Number To Test For Prime:"));
		frame.add(f1);
		frame.add(new JLabel(""));
		frame.add(new JLabel(""));		
		frame.add(new JLabel(""));
		JButton B = new JButton("Get Answer");
		frame.add(B);
		B.addActionListener(new PrimeNumberGUI());
		frame.add(result);
		int frameWidth = 350;
		int frameHeight = 200;
		Dimension screenSize =
				Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((int) screenSize.getWidth() - frameWidth,
				0, frameWidth, frameHeight);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		double firstNum = Double.parseDouble(f1.getText());

		String userString = f1.getText();
		try
		{
			Socket connection = new Socket("127.0.0.1", 1234);
			InputStream input = connection.getInputStream();
			OutputStream output = connection.getOutputStream();
			
			output.write(userString.length());
			output.write(userString.getBytes());
			
			int n = input.read();
			byte[] data = new byte[n];
			input.read(data);			
							
			String serverResponse = new String(data, StandardCharsets.UTF_8);
			System.out.println("Server said: " + serverResponse);
			result.setText(serverResponse);
			
			if(!connection.isClosed());
				connection.isClosed();
				
			
			
			
		} catch (IOException ei) {
			ei.printStackTrace();
		}

	}

}



