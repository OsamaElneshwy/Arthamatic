package Pack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Comp extends GUI {

	private JFrame frame;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextArea textarea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comp window = new Comp();
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
	public Comp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 471, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		text1 = new JTextField();
		text1.setColumns(10);
		
		text2 = new JTextField();
		text2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Char :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblNewLabel_1 = new JLabel("Prop. :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				load();
				text1.setText("");
				text2.setText("");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				frame.setVisible(false);
			}
		});
		
		text3 = new JTextField();
		text3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("String :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton btnNewButton_2 = new JButton("Compression");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				build();
				
				for(int n=0;n < v.size();n++)
				{
					textarea.append("char "+v.get(n).c+" prob "+v.get(n).probability+" lower "+v.get(n).lower+" higher "+v.get(n).higher+"\n");
				}
				textarea.append("**********************\n");
				try 
				{
					comp();
				}
				catch (IOException e1)
				{
					textarea.append("Error while openning the File !!!");
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(text1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_1)
											.addGap(23)
											.addComponent(text2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(31)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_2)
									.addGap(18)
									.addComponent(text3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))
					.addGap(26)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel)
										.addComponent(text1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(text2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(text3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
							.addGap(18)
							.addComponent(btnNewButton_2)
							.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
							.addComponent(btnNewButton_1)))
					.addContainerGap())
		);
		
		textarea = new JTextArea();
		scrollPane.setViewportView(textarea);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	
	public void open()
	{
		Comp window = new Comp();
		window.frame.setVisible(true);
	}
	
	public void load()
	{
		data x = new data();
		
		x.c= text1.getText();
		
		x.probability=Float.parseFloat(text2.getText());

		v.add(x);	
	}
	
	public void build()
	{
		for(int i=0;i<v.size();i++)
		{
			if(i == 0)
			{
				v.get(i).lower = 0;
				v.get(i).higher = v.get(i).probability;
				v.get(i).F_lower=v.get(i).lower;
				v.get(i).F_higher=v.get(i).higher;
			}
			else
			{
				v.get(i).lower = v.get(i-1).higher;
				v.get(i).higher = v.get(i).lower+v.get(i).probability;
				v.get(i).F_lower=v.get(i).lower;
				v.get(i).F_higher=v.get(i).higher;
			}
		}
	}
	
	public int search(String b)
	{
		int z=0;
		for(int i=0;i<v.size();i++)
		{
			if(v.get(i).c.equals(b))
			{
				z=i;
			}
		}
		return z;
	}
	
	public void comp() throws IOException
	{
		 FileOutputStream x = new FileOutputStream("Compression.txt");
		 DataOutputStream out  = new DataOutputStream(x);
		
		s=text3.getText();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			String l = String.valueOf(c);
			int a=search(l);
			
			if(i == s.length()-1)
			{
				float b = new Random().nextFloat()*((v.get(a).higher - v.get(a).lower)  ) + v.get(a).lower;
				comp_number=b;
				textarea.append("--------------------------\n");
				textarea.append("Compressed Code For Symbols "+s+" is "+ b +"\n" );
				textarea.append("--------------------------\n");
				break;
			}
			
			v.get(0).lower=v.get(a).lower;
			v.get(v.size()-1).higher=v.get(a).higher;
			
			for(int j=1;j<v.size()-1;j++)
			{	
				v.get(j).lower = v.get(0).lower+( v.get(v.size()-1).higher - v.get(0).lower)*v.get(j).F_lower;
				v.get(j).higher = v.get(0).lower+( v.get(v.size()-1).higher - v.get(0).lower)*v.get(j).F_higher;
				
				if( j == 1 )
				{
					v.get(0).higher =v.get(j).lower;
				}
				
				if( j == v.size()-2 )
				{
					v.get(v.size()-1).lower = v.get(j).higher;
				}
			}
			for(int n=0;n < v.size();n++)
			{
				textarea.append("char "+v.get(n).c+" prob "+v.get(n).probability+" lower "+v.get(n).lower+" higher "+v.get(n).higher+"\n");
			}
			textarea.append("**********************\n");
		}
		 out.writeFloat(comp_number);
		 out.close();
		
	}
	
	
}
