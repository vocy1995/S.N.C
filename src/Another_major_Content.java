import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

// _Content가 들어간 클래스들은 다 똑같은 틀에서 약간씩 다르기 때문에 클래스마다 주석을 달지 않았습니다.

public class Another_major_Content extends JFrame{ //타 학과의 공지사항 내용을 받기위한 클래스
	
	Simpleclient client = new Simpleclient();
	
	String content[] =null;
	String content_total[] =null;
	int Get_Content_Count; // another_major에서 원하는 공지사항 의 내용을 얻기위해 분별할 정수값
	public void Another_major_Content_run(){
		 Toolkit toolkit = Toolkit.getDefaultToolkit();
	     Image imgg = toolkit.getImage("img//logo.jpg"); 
	     setIconImage(imgg);
		String content[] = new String[50];	
		String content_total[] = new String[50];
		String content_total_test[] = new String[50];
		try{
			client.Client_connect();
			JPanel p2 = new JPanel();
			getContentPane().add(p2);
			p2.setLayout(null);
			
			try {
				client.ClientRun("another_major_content"); //서버에서 데이터 구분을 위한 메시지 전송
				String interval="\n";
		    	String receiveData = "";
		    	receiveData = client.br.readLine();
		    	
		    	//content[0]=receiveData;
		    	String Engineering[] = receiveData.split("!!!");
		    	
		    	String intervar="\n";
		    	int test=0;
		    	int count=0;
		    	for(count=0;count<Engineering.length;count++) {
		    		content[count]=Engineering[count]; //!!! 로 자른 문자열을 다른 배열에 저장
		    			
		    	}
		    	
		    	
		    	String split_test[]=content[Get_Content_Count].split("%n"); // %n으로 자른이유는 \n값을 넣기위해서
		    	
		    	
		    	for(int total=0; total<split_test.length;total++) { 
		    		int total_tot=0;
		    		content_total[total]=split_test[total]+interval;		// interval에 \n값을 미리 넣어서 배열에 저장    		
		    	}
		    	content_total_test[Get_Content_Count]="\n";
		    	for(int ttt=0;ttt<split_test.length;ttt++) {
		    		int total_tot=0;
		    		content_total_test[Get_Content_Count]+=content_total[ttt]; //호출하는 클래스에서 공지사항 번호에 해당하는것을 배열에 저장한다.
		    		
		    	}
		   
		    		
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		
		
	
		TextArea ContentArea = new TextArea(content_total_test[Get_Content_Count]);
		p2.add(ContentArea);
		ContentArea.setEditable(false); 
		
	
		ContentArea.setBounds(100, 50, 1200, 800);

	
	
		JButton bt5 = new JButton("뒤로가기");
		bt5.setFont(new Font("메이플스토리", Font.BOLD, 40));
		bt5.setBounds(1375,41,262,133);
		p2.add(bt5);
	
		bt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		setSize(1700, 1000);
		setLocationRelativeTo(null);
		setTitle("미디어");
		setResizable(false);
		setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
