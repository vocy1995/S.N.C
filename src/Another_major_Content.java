import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

// _Content�� �� Ŭ�������� �� �Ȱ��� Ʋ���� �ణ�� �ٸ��� ������ Ŭ�������� �ּ��� ���� �ʾҽ��ϴ�.

public class Another_major_Content extends JFrame{ //Ÿ �а��� �������� ������ �ޱ����� Ŭ����
	
	Simpleclient client = new Simpleclient();
	
	String content[] =null;
	String content_total[] =null;
	int Get_Content_Count; // another_major���� ���ϴ� �������� �� ������ ������� �к��� ������
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
				client.ClientRun("another_major_content"); //�������� ������ ������ ���� �޽��� ����
				String interval="\n";
		    	String receiveData = "";
		    	receiveData = client.br.readLine();
		    	
		    	//content[0]=receiveData;
		    	String Engineering[] = receiveData.split("!!!");
		    	
		    	String intervar="\n";
		    	int test=0;
		    	int count=0;
		    	for(count=0;count<Engineering.length;count++) {
		    		content[count]=Engineering[count]; //!!! �� �ڸ� ���ڿ��� �ٸ� �迭�� ����
		    			
		    	}
		    	
		    	
		    	String split_test[]=content[Get_Content_Count].split("%n"); // %n���� �ڸ������� \n���� �ֱ����ؼ�
		    	
		    	
		    	for(int total=0; total<split_test.length;total++) { 
		    		int total_tot=0;
		    		content_total[total]=split_test[total]+interval;		// interval�� \n���� �̸� �־ �迭�� ����    		
		    	}
		    	content_total_test[Get_Content_Count]="\n";
		    	for(int ttt=0;ttt<split_test.length;ttt++) {
		    		int total_tot=0;
		    		content_total_test[Get_Content_Count]+=content_total[ttt]; //ȣ���ϴ� Ŭ�������� �������� ��ȣ�� �ش��ϴ°��� �迭�� �����Ѵ�.
		    		
		    	}
		   
		    		
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		
		
	
		TextArea ContentArea = new TextArea(content_total_test[Get_Content_Count]);
		p2.add(ContentArea);
		ContentArea.setEditable(false); 
		
	
		ContentArea.setBounds(100, 50, 1200, 800);

	
	
		JButton bt5 = new JButton("�ڷΰ���");
		bt5.setFont(new Font("�����ý��丮", Font.BOLD, 40));
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
		setTitle("�̵��");
		setResizable(false);
		setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
