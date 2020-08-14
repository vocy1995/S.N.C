package sm_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class server {

	static Connection con = null;
	static Statement state = null; // ���°�
	
    public static void main(String[] args) {
       
        try {

        	
        	System.out.println("���� ��ŸƮ!");
            ServerSocket server = new ServerSocket(8000);// ���� ���� 8000����
            while (true) { //�ݺ�
                Socket client = server.accept(); //Ŭ���̾�Ʈ ���� �޾��ֱ�
                TransLator translator = new TransLator(client); // ���ӿ�û�� �� Ŭ���̾�Ʈ�� ������ ��� 
                translator.start(); //��ŸƮ
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TransLator extends Thread {
	
	private static final String JDBC = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/student_information?serverTimezone=UTC&useSSL=false&autoReconnect=true";
	private static final String DB_URL_NOTICE = "jdbc:mysql://127.0.0.1:3306/smnotice?serverTimezone=UTC&useSSL=false&autoReconnect=true";
	private static final String DB_URL_BOARD = "jdbc:mysql://127.0.0.1:3306/smboard?serverTimezone=UTC&useSSL=false&autoReconnect=true";
	private static final String DB_URL_ANOTHER = "jdbc:mysql://127.0.0.1:3306/anothernotice?serverTimezone=UTC&useSSL=false&autoReconnect=true";
	private static final String Login_Name = "root";
	private static final String password = "Jkm454875"; 
	
	//�� DB�� ���� url�� jdbc �� �̸� ����
	
	static Connection con = null;
	static Statement state = null; // ���°�
	
	OutputStream os = null;
    OutputStreamWriter osw = null;
    BufferedWriter bw = null;      //������ ������ ���� ��Ʈ���� ����Ʈ�� ��ü�� ����Ͽ� ��������
    
	
    Socket client;

    TransLator(Socket client) {
        this.client = client; //Ŭ���̾�Ʈ�� ������ ���� this���
    }
    public void run() {
        try {
        	
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter pw = new PrintWriter(client.getOutputStream());
            String readData = "";
            String data="";
            readData=br.readLine(); //�����͸� �ް� �����ϱ����� inputstream�� outstream ���
        	
            System.out.println(client.getInetAddress() + "�� ���� �����û�� ����");
            // Ŭ���̾�Ʈ�κ��� �����͸� �ޱ� ���� InputStream ����
            
            // �� �����͸� if������ �񱳸� �Ͽ� ��ġ�� �����͸� �����ϴ� ������� �Ͽ����ϴ�
            // ��κ��� ���۹���� ����ؼ� �ּ��� ������ �ۼ����� �ʾҽ��ϴ�.
            
            if(readData.equals("login")) {
            	con = DriverManager.getConnection(DB_URL, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	
                String id[] = new String[50];
                String password1[] = new String[50]; //id,password ������ ������ ���� �迭
                
            	String login = "select StNum,Password from student_all";	
            	ResultSet res = state.executeQuery(login); //db�� select ���� �������� ������ �ۼ�
            	
            	System.out.println("login ������ ����");
            	int count =0;
            	while(res.next()) {
            		id[count] = res.getString(1);
            		password1[count] = res.getString(2);
            		System.out.println(""+id[count]+"\t"+password1[count]);
            		count++;
            	}
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(id[send_count],client);
            		receiveData("###",client); 
            		receiveData(password1[send_count],client);
            		receiveData("###",client);
            	} //�����͸� �������� ������ Ŭ���̾�Ʈ���� �����ϱ� ����� ������ ###�� ����
            	 bw.close();
                 osw.close();
                 os.close();
                 client.close();
            	state.close();
				con.close();
            }
            if(readData.equals("register")) {
            	con = DriverManager.getConnection(DB_URL, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	
            	
            	String register_read;
            	register_read=br.readLine();
            	System.out.println("register ������ ����");
            	
            	System.out.println(register_read);
            	
            	
            	String register_in[]=register_read.split("@@@"); 	
             	
            	String st_sign = "insert into student_all(name,StNum,Password,major) values ('"+register_in[0]+"','"+register_in[1]+"','"+register_in[2]+"','"+register_in[3]+"')";
            	
            	state.executeUpdate(st_sign); //executeUpdate�� ����Ͽ� insert���� ������ �ۼ�
            	 bw.close();
                 osw.close();
                 os.close();
                 client.close();
				state.close();
				con.close();
				
            }
            if(readData.equals("engineering")) {
            	
            	System.out.println("engineering ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	
            	String title[] = new String[50];
        	
            	String notice="select title from engineering";
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		title[count]=res.getString(1);        
            		count++;
            	}
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(title[send_count],client);
            		receiveData("!!!",client);
            		//System.out.println("������ ���� : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("engineering_content")) {
            	
            	System.out.println("engineering_content ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from engineering";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("������ �������� : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("������ ���� : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("general")) {
            	
            	System.out.println("general ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	            	
            	String content[] = new String[50];
            	
            	String notice="select title from general";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("������ �������� : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("������ ���� : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("general_content")) {
            	
            	System.out.println("general_content ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from general";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("������ �������� : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("������ ���� : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("free_board")) {
            	System.out.println("engineering ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_BOARD, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	
            	String title[] = new String[50];
        	
            	String notice="select title from freeboarddb";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		title[count]=res.getString(1);        
            		count++;
            	}
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(title[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("������ ���� : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("free_board_content")) {
            	
            	System.out.println("free_board_content ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_BOARD, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	            	
            	String content[] = new String[50];
            	
            	String freeboard="select content from freeboarddb";
            	
            	ResultSet res = state.executeQuery(freeboard);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("������ �������� : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("������ ���� : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("new_board_write")) {            
            	System.out.println("new_board_write ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_BOARD, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	
            	String write_board = br.readLine();
            	 
            	System.out.println(write_board);
            	
            	String Write_split[] = write_board.split("@@@");
            	//String temp=Write_split[1];

            	
            	String new_board = "insert into freeboarddb(title,content) values ('"+Write_split[0]+"','"+Write_split[1]+"')";
            	
            	state.executeUpdate(new_board);
            
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("user")) {       //�α��� �� �������������� �ڽ��� ������ ȭ�鿡 ���� ���� if��     
            	System.out.println("user ������ ����");            	
               	
            	String user_receive = br.readLine();
            	System.out.println("�� �������� ���� �� : "+user_receive);
            	con = DriverManager.getConnection(DB_URL, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	            	
            	String name[] = new String[15];
            	String stNum[] = new String[15];
            	String passWord[] = new String[15];
            	String major[] = new String[15];
            	
            	String user_data="select * from student_all";
            	
            	ResultSet res = state.executeQuery(user_data);
            	
            	int count=0;
            	while(res.next()) {
            		name[count]=res.getString(1);
            		stNum[count]=res.getString(2); 
            		passWord[count]=res.getString(3); 
            		major[count]=res.getString(4);
            		if(stNum[count].equals(user_receive)) {
                		System.out.println("��ġ�޽��ϴ�");
                		break;
                	}
            		count++;
            	}//Ŭ���̾�Ʈ���� �α��ν� id�� password�� ������ �������� user_receive�� �����͸� �ް� ��ġ�ҽ� ������ 
            	
            	receiveData(name[count],client);
        		receiveData("!!!",client);
        		receiveData(stNum[count],client);
        		receiveData("!!!",client);
        		receiveData(major[count],client);
        		receiveData("!!!",client);
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("competition")) {
            	
            	System.out.println("competition ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	
            	String title[] = new String[50];
        	
            	String notice="select title from competition";
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		title[count]=res.getString(1);        
            		count++;
            	}
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(title[send_count],client);
            		receiveData("!!!",client);
            		//System.out.println("������ ���� : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("competition_content")) {
            	
            	System.out.println("competition_content ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from competition";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("������ �������� : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("������ ���� : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("army")) {
            	
            	System.out.println("army ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	
            	String title[] = new String[50];
        	
            	String notice="select title from army";
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		title[count]=res.getString(1);        
            		count++;
            	}
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(title[send_count],client);
            		receiveData("!!!",client);
            		//System.out.println("������ ���� : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("army_content")) {
            	
            	System.out.println("competition_content ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from army";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("������ �������� : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("������ ���� : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("supervision")) {
            	
            	System.out.println("competition ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	
            	String title[] = new String[50];
        	
            	String notice="select title from supervision";
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		title[count]=res.getString(1);        
            		count++;
            	}
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(title[send_count],client);
            		receiveData("!!!",client);
            		//System.out.println("������ ���� : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("supervision_content")) {
            	
            	System.out.println("competition_content ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from supervision";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("������ �������� : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("������ ���� : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            } if(readData.equals("another_major")) {
            	
            	System.out.println("competition ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_ANOTHER, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	
            	String title[] = new String[50];
        	
            	String notice="select title from major";
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		title[count]=res.getString(1);        
            		count++;
            	}
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(title[send_count],client);
            		receiveData("!!!",client);
            		//System.out.println("������ ���� : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            } if(readData.equals("another_major_content")) {
            	
            	//System.out.println("competition_content ������ ����");
            	
            	con = DriverManager.getConnection(DB_URL_ANOTHER, Login_Name, password);
            	state = con.createStatement(); // �������� �ۼ��Ͽ� �����ϱ� ���� �뵵
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from major";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("������ �������� : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("������ ���� : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(NullPointerException e) {
        	e.printStackTrace();
        }
        catch(SQLException e ) {
        	 e.printStackTrace();
        }
    }
  
    public void receiveData(String data, Socket socket){
       
        try{
            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            // Ŭ���̾�Ʈ�κ��� �����͸� ������ ���� OutputStream ����          
            bw.write(data);            // Ŭ���̾�Ʈ�� ������ ����
            bw.flush();
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }    
}