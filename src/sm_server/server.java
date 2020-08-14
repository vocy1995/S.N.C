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
	static Statement state = null; // 상태값
	
    public static void main(String[] args) {
       
        try {

        	
        	System.out.println("서버 스타트!");
            ServerSocket server = new ServerSocket(8000);// 서버 소켓 8000설정
            while (true) { //반복
                Socket client = server.accept(); //클라이언트 접솓 받아주기
                TransLator translator = new TransLator(client); // 접속요청을 한 클라이언트에 쓰레드 사용 
                translator.start(); //스타트
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
	
	//각 DB에 대한 url과 jdbc 값 미리 설정
	
	static Connection con = null;
	static Statement state = null; // 상태값
	
	OutputStream os = null;
    OutputStreamWriter osw = null;
    BufferedWriter bw = null;      //데이터 전송을 위한 스트림과 라이트를 객체로 사용하여 오류방지
    
	
    Socket client;

    TransLator(Socket client) {
        this.client = client; //클라이언트의 구분을 위한 this사용
    }
    public void run() {
        try {
        	
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter pw = new PrintWriter(client.getOutputStream());
            String readData = "";
            String data="";
            readData=br.readLine(); //데이터를 받고 전송하기위해 inputstream과 outstream 사용
        	
            System.out.println(client.getInetAddress() + "로 부터 연결요청이 들어옴");
            // 클라이언트로부터 데이터를 받기 위한 InputStream 선언
            
            // 각 데이터를 if문으로 비교를 하여 일치시 데이터를 전송하는 방법으로 하였습니다
            // 대부분의 동작방식이 비슷해서 주석을 일일히 작성하지 않았습니다.
            
            if(readData.equals("login")) {
            	con = DriverManager.getConnection(DB_URL, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	
                String id[] = new String[50];
                String password1[] = new String[50]; //id,password 데이터 전송을 위한 배열
                
            	String login = "select StNum,Password from student_all";	
            	ResultSet res = state.executeQuery(login); //db에 select 문을 쓰기위한 쿼리문 작성
            	
            	System.out.println("login 데어터 받음");
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
            	} //데이터를 구분하지 않으면 클라이언트에서 구분하기 힘들기 떄문에 ###을 전송
            	 bw.close();
                 osw.close();
                 os.close();
                 client.close();
            	state.close();
				con.close();
            }
            if(readData.equals("register")) {
            	con = DriverManager.getConnection(DB_URL, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	
            	
            	String register_read;
            	register_read=br.readLine();
            	System.out.println("register 데어터 받음");
            	
            	System.out.println(register_read);
            	
            	
            	String register_in[]=register_read.split("@@@"); 	
             	
            	String st_sign = "insert into student_all(name,StNum,Password,major) values ('"+register_in[0]+"','"+register_in[1]+"','"+register_in[2]+"','"+register_in[3]+"')";
            	
            	state.executeUpdate(st_sign); //executeUpdate를 사용하여 insert문의 쿼리문 작성
            	 bw.close();
                 osw.close();
                 os.close();
                 client.close();
				state.close();
				con.close();
				
            }
            if(readData.equals("engineering")) {
            	
            	System.out.println("engineering 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	
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
            		//System.out.println("데이터 보냄 : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("engineering_content")) {
            	
            	System.out.println("engineering_content 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from engineering";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("데이터 보내기전 : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("데이터 보냄 : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("general")) {
            	
            	System.out.println("general 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	            	
            	String content[] = new String[50];
            	
            	String notice="select title from general";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("데이터 보내기전 : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("데이터 보냄 : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("general_content")) {
            	
            	System.out.println("general_content 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from general";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("데이터 보내기전 : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("데이터 보냄 : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("free_board")) {
            	System.out.println("engineering 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_BOARD, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	
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
            		System.out.println("데이터 보냄 : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("free_board_content")) {
            	
            	System.out.println("free_board_content 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_BOARD, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	            	
            	String content[] = new String[50];
            	
            	String freeboard="select content from freeboarddb";
            	
            	ResultSet res = state.executeQuery(freeboard);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("데이터 보내기전 : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("데이터 보냄 : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("new_board_write")) {            
            	System.out.println("new_board_write 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_BOARD, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	
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
            if(readData.equals("user")) {       //로그인 후 메인페이지에서 자신의 정보를 화면에 띄우기 위한 if문     
            	System.out.println("user 데어터 받음");            	
               	
            	String user_receive = br.readLine();
            	System.out.println("내 정보에서 보낸 값 : "+user_receive);
            	con = DriverManager.getConnection(DB_URL, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	            	
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
                		System.out.println("일치햇습니다");
                		break;
                	}
            		count++;
            	}//클라이언트에서 로그인시 id와 password를 보내면 서버에서 user_receive로 데이터를 받고 일치할시 재전송 
            	
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
            	
            	System.out.println("competition 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	
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
            		//System.out.println("데이터 보냄 : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("competition_content")) {
            	
            	System.out.println("competition_content 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from competition";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("데이터 보내기전 : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("데이터 보냄 : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("army")) {
            	
            	System.out.println("army 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	
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
            		//System.out.println("데이터 보냄 : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("army_content")) {
            	
            	System.out.println("competition_content 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from army";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("데이터 보내기전 : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("데이터 보냄 : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("supervision")) {
            	
            	System.out.println("competition 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	
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
            		//System.out.println("데이터 보냄 : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            }
            if(readData.equals("supervision_content")) {
            	
            	System.out.println("competition_content 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_NOTICE, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from supervision";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("데이터 보내기전 : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("데이터 보냄 : "+content[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            } if(readData.equals("another_major")) {
            	
            	System.out.println("competition 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_ANOTHER, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	
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
            		//System.out.println("데이터 보냄 : "+title[send_count]);
            	}
            	
            	state.close();
				con.close();
                client.close();
            } if(readData.equals("another_major_content")) {
            	
            	//System.out.println("competition_content 데어터 받음");
            	
            	con = DriverManager.getConnection(DB_URL_ANOTHER, Login_Name, password);
            	state = con.createStatement(); // 쿼리문을 작성하여 적용하기 위한 용도
            	            	
            	String content[] = new String[50];
            	
            	String notice="select content from major";
            	
            	ResultSet res = state.executeQuery(notice);
            	
            	int count=0;
            	while(res.next()) {
            		content[count]=res.getString(1); 
            		System.out.println("데이터 보내기전 : "+content[count]);
            		count++;
            	}
            	System.out.println(""+count);
            	for(int send_count=0; send_count<count;send_count++) {
            		receiveData(content[send_count],client);
            		receiveData("!!!",client);
            		System.out.println("데이터 보냄 : "+content[send_count]);
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
            // 클라이언트로부터 데이터를 보내기 위해 OutputStream 선언          
            bw.write(data);            // 클라이언트로 데이터 전송
            bw.flush();
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }    
}