package Pack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class De_Comp extends GUI {

	private JFrame frame;
	private JTextField text1;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					De_Comp window = new De_Comp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public De_Comp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("# of char :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		text1 = new JTextField();
		text1.setColumns(10);
		
		JButton btnNewButton = new JButton("SET");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				n=Integer.parseInt(text1.getText());
				text1.setText("");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnNewButton_1 = new JButton("De-Compression");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				DeComp(v , comp_number);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton button = new JButton("Close");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(text1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(text1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)
							.addGap(18)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
							.addComponent(button)))
					.addContainerGap())
		);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	
	public void open()
	{
		De_Comp window = new De_Comp();
		window.frame.setVisible(true);
	}
	
	public void DeComp(Vector<data> vec , float code)
	{
		Formatter x=null;
		
		try 
		{
			x = new Formatter("De-compressionData.txt");
		}
		catch (FileNotFoundException e)
		{
			textArea.append("File cannot open !!!\n");
		}
		
		float l = 0 , h=0  , range = 1  , nCode = 0;
		String  output = "";
		boolean check=false;		
		int stop = 0;
		while (stop != n)
		{
			for(int i = 0; i < vec.size() ; i++)
			{
				if(!check)
				{
					if( vec.get(i).F_lower <= code && code < vec.get(i).F_higher  )
					{
						output+= vec.get(i).c;
						l = vec.get(i).F_lower;
						h = vec.get(i).F_higher;
						nCode =  ( code - l ) / (h - l);
						range = h - l;
						for(int k = 0 ; k < vec.size() ; k++)
						{
							vec.get(k).lower = l + range*vec.get(k).F_lower;
							vec.get(k).higher = l + range*vec.get(k).F_higher;
						}
						check = true;
						break;
					}
				}
				else if (check)
				{
					if (vec.get(i).F_lower <= nCode && nCode < vec.get(i).F_higher) 
					{
						output+= vec.get(i).c;
						l = vec.get(i).lower;
						h = vec.get(i).higher;
						nCode =  ( code - l ) / (h - l);
						range = h - l;
						for(int k = 0 ; k < vec.size() ; k++)
						{
							vec.get(k).lower = l + range*vec.get(k).F_lower;
							vec.get(k).higher = l + range*vec.get(k).F_higher;
						}
						
						break;
					}
				}
			}
		stop++;
		}
		textArea.append("************************\n");
		textArea.append("Output = " + output+"\n");
		textArea.append("************************\n");
		
		x.format("%s", "Output = " + output+"\n");
		x.close();
		
		l = h = 0;
	}
	

}
