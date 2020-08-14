
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class General extends JFrame{
	
	Simpleclient client = new Simpleclient();
	
	public void General_run(){
		 Toolkit toolkit = Toolkit.getDefaultToolkit();
	     Image imgg = toolkit.getImage("img//logo.jpg");
	     setIconImage(imgg);
		String title[] = new String[50];
		try {
			client.Client_connect();
		JPanel p = new JPanel();
		getContentPane().add(p);
		p.setLayout(null);
			try{
				client.ClientRun("general");
		    	
		    	String receiveData = "";
		    	receiveData = client.br.readLine();
		    	System.out.println("제목 받은것 : "+receiveData);	
		    	String General_split[] = receiveData.split("!!!");
		    	System.out.println("자른것 : "+General_split[0]);
		    	for(int count=0;count<General_split.length;count++) {
		    		title[count]=General_split[count];
		    		System.out.println("자른것 : "+General_split[count]);
		    		System.out.println("저장된 제목 : "+title[count]);
		    	}
				
			}catch(Exception e) {
				e.printStackTrace();
			}

			JButton bt1 = new JButton(title[0]);
			p.add(bt1);
			bt1.setFont(new Font("메이플스토리", Font.BOLD, 35));
			bt1.setBounds(34, 40, 1200, 100);
			JButton j2 = new JButton(title[1]);
			p.add(j2);
			j2.setFont(new Font("메이플스토리", Font.BOLD, 35));
			j2.setBounds(34, 170, 1200, 100);
		
			JButton bt3 = new JButton(title[2]);
			bt3.setFont(new Font("메이플스토리", Font.BOLD, 35));
			bt3.setBounds(34, 300, 1200, 100);
			p.add(bt3);

			JButton bt2 = new JButton(title[3]);
			bt2.setFont(new Font("메이플스토리", Font.BOLD, 35));
			bt2.setBounds(34, 430, 1200, 100);
			p.add(bt2);

			JButton bt4 = new JButton(title[4]);
			bt4.setFont(new Font("메이플스토리", Font.BOLD, 35));
			bt4.setBounds(34, 560, 1200, 100);
			p.add(bt4);

			JButton bt5 = new JButton(title[5]);
			bt5.setFont(new Font("메이플스토리", Font.BOLD, 35));
			bt5.setBounds(34, 690, 1200, 100);
			p.add(bt5);
		
		JButton back = new JButton("뒤로가기");
		back.setFont(new Font("메이플스토리", Font.BOLD, 40));
		back.setBounds(1375,41,262,133);
		p.add(back);
	
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
	
		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e4) {
				General_Content t=new General_Content();
				t.Get_Content_Count=1;
				t.General_Content_run();
			}
		});
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				General_Content t=new General_Content();
				t.Get_Content_Count=0;
				t.General_Content_run();
			}

		});
		bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				General_Content t=new General_Content();
				t.Get_Content_Count=0;
				t.General_Content_run();
			}

		});
		bt3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				General_Content t=new General_Content();
				t.Get_Content_Count=0;
				t.General_Content_run();
			}

		});
		bt4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				General_Content t=new General_Content();
				t.Get_Content_Count=0;
				t.General_Content_run();
			}

		});
		bt5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				General_Content t=new General_Content();
				t.Get_Content_Count=0;
				t.General_Content_run();
			}

		});
		
		

		
		setSize(1700, 1000);

		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("일반");
		setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
	}
