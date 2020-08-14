import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.String;

public class Free_board extends JFrame {
	
	Simpleclient client = new Simpleclient();
	New_board_Write write = new New_board_Write();
	public void Free_board_Run(){
		 Toolkit toolkit = Toolkit.getDefaultToolkit();
	     Image imgg = toolkit.getImage("img//logo.jpg");
	     setIconImage(imgg);
		String title[] = new String[50];
		
		try {
			client.Client_connect();
			JPanel p = new JPanel();
			getContentPane().add(p);
			p.setLayout(null);
			try {
				client.ClientRun("free_board");
				
				String receiveData = "";
		    	receiveData = client.br.readLine();
		    	System.out.println("제목 받은것 : "+receiveData);	
		    	String free_board_title[] = receiveData.split("!!!");
		    	System.out.println("자른것 : "+free_board_title[0]);
		    	for(int count=0;count<free_board_title.length;count++) {
		    		title[count]=free_board_title[count];
		    		//System.out.println("자른것 : "+free_board_title[count]);
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
		
		JButton New_Board = new JButton("글쓰기");
		New_Board.setFont(new Font("메이플스토리", Font.BOLD, 40));
		New_Board.setBounds(1375,250,262,133);
		p.add(New_Board);
			
		
		
		JButton back = new JButton("뒤로가기");
		back.setFont(new Font("메이플스토리", Font.BOLD, 40));
		back.setBounds(1375,41,262,133);
		p.add(back);
		
		New_Board.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				 try{	    	
					write.New_board_Write_Run();
					
				 }catch(NullPointerException e){
				    e.printStackTrace();
				 }	
			}

		});
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				 try{	    	
					 Free_Board_Content run1 = new Free_Board_Content();
					 run1.Get_Content_Count=0;
					 run1.Free_Board_Content_run();
					 
				 }catch(NullPointerException e){
				    e.printStackTrace();
				 }	
			}

		});
		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				 try{	    	
					 Free_Board_Content run1 = new Free_Board_Content();
					 run1.Get_Content_Count=1;
					 run1.Free_Board_Content_run();
					
				 }catch(NullPointerException e){
				    e.printStackTrace();
				 }	
			}

		});
		bt3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				 try{	    	
					 Free_Board_Content run1 = new Free_Board_Content();
					 run1.Get_Content_Count=2;
					 run1.Free_Board_Content_run();
					
				 }catch(NullPointerException e){
				    e.printStackTrace();
				 }	
			}

		});
		bt4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				 try{	    	
					 Free_Board_Content run1 = new Free_Board_Content();
					 run1.Get_Content_Count=3;
					 run1.Free_Board_Content_run();
					
				 }catch(NullPointerException e){
				    e.printStackTrace();
				 }	
			}

		});
		bt5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				 try{	    	
					 Free_Board_Content run1 = new Free_Board_Content();
					 run1.Get_Content_Count=4;
					 run1.Free_Board_Content_run();
					
				 }catch(NullPointerException e){
				    e.printStackTrace();
				 }	
			}

		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		setSize(1700, 1000);

		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("자유게시판");
		setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
