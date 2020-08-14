
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Media_MainPage extends JFrame{

	Simpleclient client = new Simpleclient();
	String test;
	String title_split[];
	Engineering en1 = new Engineering();
	General ge1 = new General();
	Free_board free = new Free_board();
	Supervision oe1 = new Supervision();
	Competition ce1 = new Competition();
	   Army army1 = new Army();
	   Another_major ma = new Another_major();
	static int main_user_id;
		public void Media_MainPage_run(){
			
			String user_info[] = new String[10];
			
			try {
				
				
				client.Client_connect();
				JPanel p = new JPanel();
				getContentPane().add(p);
				p.setLayout(null);
				
				client.ClientRun("user");
	            try {
	               String send_id=Integer.toString(main_user_id);
	               
	               client.ClientRun(send_id);
	               
	               String receiveData = "";
	                receiveData = client.br.readLine();
	                System.out.println("총 받은것 : "+receiveData);   
	                String user_split[] = receiveData.split("!!!");
	                
	                for(int count=0;count<user_split.length;count++) {
	                   user_info[count]=user_split[count];
	                }
	      
	               
	               
	            }catch(Exception e) {
	               
	            }
				JButton user = new JButton("유저정보");
	            p.add(user);
	            user.setFont(new Font("메이플스토리", Font.BOLD, 15));
	            user.setBounds(2009, 473, 1, 1);
	
				JLabel lblNewLabel_1 = new JLabel(new ImageIcon("img\\그림1.jpg"));
				lblNewLabel_1.setBounds(23, 20, 820, 594);
				p.add(lblNewLabel_1);
				
				JLabel lblNewLabel = new JLabel("공지사항");
				lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
				lblNewLabel.setFont(new Font("메이플스토리", Font.BOLD, 45));
				lblNewLabel.setBounds(910, 50, 727, 104);
				p.add(lblNewLabel);
				
					
				JButton bt1 = new JButton("미디어");
				p.add(bt1);
				bt1.setFont(new Font("메이플스토리", Font.BOLD, 30));
				bt1.setBounds(910, 190, 727, 100);
				JButton j2 = new JButton("일반");
				p.add(j2);
				j2.setFont(new Font("메이플스토리", Font.BOLD, 30));
				j2.setBounds(910, 310, 727, 100);
				
				JButton bt3 = new JButton("장학");
				bt3.setFont(new Font("메이플스토리", Font.BOLD, 30));
				bt3.setBounds(910, 430, 727, 100);
				p.add(bt3);
	   
				JButton bt2 = new JButton("공모전");
				bt2.setFont(new Font("메이플스토리", Font.BOLD, 30));
				bt2.setBounds(910, 550, 727, 100);
				p.add(bt2);
	   
				JButton bt4 = new JButton("군복무");
				bt4.setFont(new Font("메이플스토리", Font.BOLD, 30));
				bt4.setBounds(910, 670, 727, 100);
				p.add(bt4);
	   		
				JButton free_board = new JButton("자유게시판");
			    free_board.setFont(new Font("메이플스토리", Font.BOLD, 30));
				free_board.setBounds(910, 790, 727, 100);
				p.add(free_board);
				
				JLabel major = new JLabel("학과(부)");
				major.setHorizontalAlignment(JLabel.CENTER);
				major.setFont(new Font("메이플스토리", Font.BOLD, 40));
	            p.add(major);
	            major.setBounds(50, 640, 150 ,80);
	            JLabel major_text = new JLabel(user_info[2]);
	            major_text.setHorizontalAlignment(JLabel.CENTER);
	            major_text.setFont(new Font("메이플스토리", Font.BOLD, 40));
	            p.add(major_text);
	         
	            major_text.setBounds(192, 640, 651, 80);
	               // t3.setEchoChar('*');
	            JLabel Stnum = new JLabel("학번");
	            Stnum.setHorizontalAlignment(JLabel.CENTER);
	            Stnum.setFont(new Font("메이플스토리", Font.BOLD, 40));
	            p.add(Stnum);
	            Stnum.setBounds(50, 720, 150 ,80);
	            JLabel Stnum_text = new JLabel(user_info[1]);
	            Stnum_text.setHorizontalAlignment(JLabel.CENTER);
	            Stnum_text.setFont(new Font("메이플스토리", Font.BOLD, 40));
	            p.add(Stnum_text);
	            Stnum_text.setBounds(192, 720, 651, 80);
	            JLabel name= new JLabel("이름");
	            name.setHorizontalAlignment(JLabel.CENTER);
	            name.setFont(new Font("메이플스토리", Font.BOLD, 40));
	            p.add(name);
	            name.setBounds(50, 800, 150 ,80);
	            JLabel name_text = new JLabel(user_info[0]);
	            name_text.setHorizontalAlignment(JLabel.CENTER);
	            name_text.setFont(new Font("메이플스토리", Font.BOLD, 40));
	            p.add(name_text);
	            name_text.setBounds(192, 800, 651, 80);
	                
	               
	        
				j2.addActionListener(new ActionListener() { //일반공지
					@Override
					public void actionPerformed(ActionEvent e4) {
						 try{	    	
							 ge1.General_run();
							
						 }catch(NullPointerException e){
						    e.printStackTrace();
						 }	
						
					}
				});
				bt1.addActionListener(new ActionListener() { //타학과의 과공지
					@Override
					public void actionPerformed(ActionEvent e3) {
						 try{	    	
							 ma.Another_major_run();
							
						 }catch(NullPointerException e){
						    e.printStackTrace();
						 }					   
					}
      
				});
				bt2.addActionListener(new ActionListener() { //공모전공지
					@Override
					public void actionPerformed(ActionEvent e3) {
						 try{	    	
							 ce1.Competition_run();
							
						 }catch(NullPointerException e){
						    e.printStackTrace();
						 }			
					}
      
				});
				bt3.addActionListener(new ActionListener() { //장학공지
					@Override
					public void actionPerformed(ActionEvent e3) {
						 try{	    	
							 oe1.Employ_run();
							
						 }catch(NullPointerException e){
						    e.printStackTrace();
						 }		
					}
      
				});
				bt4.addActionListener(new ActionListener() { //군복무 공지
					@Override
					public void actionPerformed(ActionEvent e3) {
					    try{          
		                    army1.Army_run();
		                   
		                 }catch(NullPointerException e){
		                    e.printStackTrace();
		                 }
			            
					}
      
				});
				free_board.addActionListener(new ActionListener() { //자유게시판
					@Override
					public void actionPerformed(ActionEvent e3) {
						 try{	    	
							 free.Free_board_Run();
							
						 }catch(NullPointerException e){
						    e.printStackTrace();
						 }	
					}
      
				});
				Toolkit toolkit = Toolkit.getDefaultToolkit();
			     Image imgg = toolkit.getImage("img//logo.jpg");
			     setIconImage(imgg);	

		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(1700,1000);
		setTitle("미디어커뮤니케이션학과");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
			setVisible(true);
     }
  }