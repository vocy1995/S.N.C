import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.String;

//Another_major,Army,Competition,Engineering,general,supervision,free_board의 클래스들은 다 똑같은 틀에서 약간씩 다르기 때문에 클래스마다 주석을 달지 않았습니다.
public class Another_major extends JFrame {
	
	Simpleclient client = new Simpleclient();
	
	public void Another_major_run(){
		
		String title[] = new String[50];
		 Toolkit toolkit = Toolkit.getDefaultToolkit();
	     Image imgg = toolkit.getImage("img//logo.jpg");
	     setIconImage(imgg);
		try {
			client.Client_connect();
		JPanel p = new JPanel();
		getContentPane().add(p);
		p.setLayout(null);
			try{
				client.ClientRun("another_major"); //서버에서 '제목전송' 구분을 위한 데이터 전송
		    	
		    	String receiveData = "";
		    	receiveData = client.br.readLine();
		    	System.out.println("제목 받은것 : "+receiveData);	
		    	String Engineering_split[] = receiveData.split("!!!"); //split으로 제목끼리의 구분된것을 자름
		    	System.out.println("자른것 : "+Engineering_split[0]);
		    	for(int count=0;count<Engineering_split.length;count++) {
		    		title[count]=Engineering_split[count];
		    		System.out.println("자른것 : "+Engineering_split[count]);
		    		System.out.println("저장된 제목 : "+title[count]);
		    	}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		/*JLabel lblNewLabel = new JLabel("학과공지사항");
		lblNewLabel.setFont(new Font("돋움", Font.BOLD, 30));
		lblNewLabel.setBounds(177, 75, 232, 88);
		p.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("기타공지사항");
		label_1.setFont(new Font("돋움", Font.BOLD, 30));
		label_1.setBounds(716, 75, 210, 88);
		p.add(label_1);*/
		
		JButton bt1 = new JButton(title[0]); //자른 제목들을 순서에 맞게 0~5개로 구분
		p.add(bt1);
		bt1.setBounds(34, 40, 1200, 100);
		bt1.setFont(new Font("메이플스토리", Font.BOLD, 35));
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
		
		getContentPane().add(p);
		p.setLayout(null);
		JButton back = new JButton("뒤로가기");
		back.setFont(new Font("메이플스토리", Font.BOLD, 40));
		back.setBounds(1375,40,262,133);
		p.add(back);
	
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
	
		j2.addActionListener(new ActionListener() { // 버튼을 누를시 내용 데이터를 받기위해 get_content_count값 전송
			@Override
			public void actionPerformed(ActionEvent e4) {
				Another_major_Content t=new Another_major_Content();
				t.Get_Content_Count=1;
				t.Another_major_Content_run();
			}
		});
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				Another_major_Content t=new Another_major_Content();
				t.Get_Content_Count=0;
				t.Another_major_Content_run();
			}

		});
		bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				Another_major_Content t=new Another_major_Content();
				t.Get_Content_Count=0;
				t.Another_major_Content_run();
			}

		});
		bt3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				Another_major_Content t=new Another_major_Content();
				t.Get_Content_Count=2;
				t.Another_major_Content_run();
			}

		});
		bt4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				Another_major_Content t=new Another_major_Content();
				t.Get_Content_Count=0;
				t.Another_major_Content_run();
			}

		});
		bt5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e3) {
				Another_major_Content t=new Another_major_Content();
				t.Get_Content_Count=0;
				t.Another_major_Content_run();
			}

		});
	
	

		
		setSize(1700, 1000);

		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("미디어");
		setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
