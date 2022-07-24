/************************************************************
 * Brian Banfield
 * 6/19/2022
 * Updated word occurrences application. 
 ***********************************************************/
import javax.swing.*;

//import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class WordSorterUnitGUI implements ActionListener {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				constructGUI();
			}
		});

	}
	
	static JTextArea textArea = new JTextArea(5, 20);
	static JScrollPane scrollPane = new JScrollPane(textArea); 
	
	public static void constructGUI(){
		textArea.setEditable(false);
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Raven Word Occurrences");
		textArea.setEditable(false);
		frame.add(scrollPane);
		JButton B = new JButton("Get Sorted Word Occurrences");
		frame.add(B, BorderLayout.SOUTH);
		B.addActionListener(new WordSorterUnitGUI());
		int frameWidth = 300;
		int frameHeight = 200;
		Dimension screenSize =
				Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((int) screenSize.getWidth() - frameWidth,
				0, frameWidth, frameHeight);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String result = WordSorter.Main("");
			textArea.append(result);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
	}

}
