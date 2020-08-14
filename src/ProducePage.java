import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProducePage extends JFrame {
	
	private static final String JDBC = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/student_information?serverTimezone=UTC&useSSL=false&autoReconnect=true";
	private static final String Login_Name = "root";
	private static final String password = "Jkm454875";
	
	static Connection con = null;
	static Statement state = null; // 상태값
	
	//String name,major,password1,st_num;
	Simpleclient client = new Simpleclient();
	
	public ProducePage(){
		
		JPanel p = new JPanel();
	    getContentPane().add(p);
	    p.setLayout(null);
	    TextField t5 = new TextField();
	    p.add(t5);
	    t5.setBounds(119, 216, 280, 120);
	    JButton j1 = new JButton("회원가입");
	    p.add(j1);
	    j1.setFont(new Font("메이플스토리", Font.BOLD, 15));
	    j1.setBounds(119, 401, 100, 30);
	    JButton j2 = new JButton("취소");
	    p.add(j2);
	    j2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		setVisible(false);
	    	}
	    });
	    j2.setFont(new Font("메이플스토리", Font.BOLD, 13));
	    j2.setBounds(254, 401, 100, 30); 
	    Label l5 = new Label("추가사항");
	    p.add(l5);
	    l5.setFont(new Font("메이플스토리", Font.BOLD, 13));
	    l5.setBounds(37, 212, 60, 40);
	    Label l4 = new Label("학과(부)");
	    p.add(l4);
	    l4.setFont(new Font("메이플스토리", Font.BOLD, 13));
	    l4.setBounds(37, 171, 60, 40);
	    TextField t4 = new TextField();
	    p.add(t4);
	    t4.setBounds(119, 171, 280, 30);
	    Label l3= new Label("비밀번호");
	    p.add(l3);
	    l3.setFont(new Font("메이플스토리", Font.BOLD, 13));
	    l3.setBounds(37,125,60,40);
	    TextField t3 = new TextField();
	    p.add(t3);
	    t3.setEchoChar('*');
	    t3.setBounds(119, 135, 200, 30);
	    Label l2 = new Label("학번");
	    p.add(l2);
	    l2.setFont(new Font("메이플스토리", Font.BOLD, 13));
	    l2.setBounds(37, 90, 40, 40);
	    TextField t2 = new TextField();
	    p.add(t2);
	    t2.setBounds(119, 99, 200, 30);
	    Label l1= new Label("이름");
	    p.add(l1);
	    l1.setFont(new Font("메이플스토리", Font.BOLD, 13));
	    l1.setBounds(37, 57, 40, 40);
	    TextField t1 = new TextField();
	    p.add(t1);
	    t1.setBounds(119, 63, 200, 30);
	 
	    j1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent T) {
			try{
				client.Client_connect();
				client.ClientRun("register");
				String st_information[] = new String[4];
				student_data st= new student_data();
				
				
				st_information[3]=t4.getText().trim();
				st_information[2]=t3.getText().trim();
				st_information[1]=t2.getText().trim();
				st_information[0]=t1.getText().trim();
				//텍스트 필드에서 작성한 데이터를 가져오기위해 getText().trim();을 사용하였습니다
				String information_sum;
				String interval = "@@@";
				information_sum=st_information[0]+interval+st_information[1]+interval+st_information[2]+interval+st_information[3];
				
				System.out.println(information_sum);
				client.ClientRun(information_sum);
				//회원가입시 데이터 중복일 경우 경고 메시지 보내기 뜨게하기
				
				JOptionPane.showMessageDialog(null, "회원가입을 축하합니다!!");
				dispose();
				
			}catch (Exception ex){
				JOptionPane.showMessageDialog(null,ex);
			}
			
			
		}
		
	});
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	     Image imgg = toolkit.getImage("img//logo.jpg");
	     setIconImage(imgg);
		setSize(500,500);
		setTitle("회원가입");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
        setVisible(true);
	} 
	
}



