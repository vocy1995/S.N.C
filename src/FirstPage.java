import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class FirstPage extends JFrame{
   
   ImageIcon icon;

   static Connection con = null;
   static Statement state = null; // 상태값
   public String compare[];
   Scanner scan;      
   String b51;
   Image img = null;
   
   static int ID_num;

   MainPage insert = new MainPage();
   Media_MainPage insert2 = new Media_MainPage();
   
   Simpleclient client = new Simpleclient();
   public void FirstPage_run()
   {
      try {
         client.Client_connect();
       icon = new ImageIcon("img//view.jpg");

        // 백그라운드 이미지 삽입할 메소드에 이름없는 클래스로 구현
        JPanel p = new JPanel() {
         public void paintComponent(Graphics g) {
          // Approach 1: Dispaly image at at full size
          g.drawImage(icon.getImage(), 0, 0, null);
          // Approach 2: Scale image to size of component
          // Dimension d = getSize();
          // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
          // Approach 3: Fix the image position in the scroll pane
          // Point p = scrollPane.getViewport().getViewPosition();
          // g.drawImage(icon.getImage(), p.x, p.y, null);
          setOpaque(false);
          super.paintComponent(g);
         }
        };
        p.setLayout(null);
         getContentPane().add(p);
      
      //Scanner scan= new Scanner(System.in);
      //client_connect client1= new client_connect();
         //System.out.println("now, you can use translator!!");
        
      
        //텍스트필드 만들기
      TextField b4 = new TextField();
      p.add(b4);
      //위치 
      b4.setBounds(111, 196, 200, 30);
      TextField b5 = new TextField();
      p.add(b5);
      b5.setEchoChar('*');
      b5.setBounds(111, 232, 200, 30);
      //버튼 만들기
      JButton b6 = new JButton("로그인");
      p.add(b6);
      //글씨체, 글씨굵기, 글씨크기 변경
      b6.setFont(new Font("메이플스토리", Font.BOLD, 15));
      b6.setBounds(331, 196, 90, 30);
      JButton b7 = new JButton("회원가입");
      p.add(b7);
      b7.setFont(new Font("메이플스토리", Font.BOLD, 15));
      b7.setBounds(331, 232, 90, 30);
      

      //왼쪽상단 로고변경
       Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image imgg = toolkit.getImage("img//logo.jpg");
        setIconImage(imgg);
      
      //이 버튼 클릭 시 해당 페이지 이동 
      b7.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            ProducePage f2= new ProducePage();
         }
      });
      
      b6.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e2) {
            try {
               //client.Client_connect();
               client.ClientRun("login");
            
               String[] id_data = new String[50];
               String[] password_data = new String[50];
                 //bw.newLine();
               
                 String receiveData = "";
                 receiveData = client.br.readLine();        // 서버로부터 데이터 한줄 읽음
                
                 System.out.println(receiveData);
                 String id_split[] = receiveData.split("###");
                 String login_id,login_password;
                 login_id=b4.getText().trim();
               login_password=b5.getText().trim();
               
               ID_num=Integer.parseInt(login_id);
               String computer_st = "244";
                     String nurse_st= "144";
                     
                     for(int a=0;a<id_split.length-1;a++) {      
                          if(login_id.equals(id_split[a])) {
                             if(login_password.equals(id_split[a+1])) {
                                insert2.main_user_id=ID_num;
                                insert.main_user_id=ID_num;
                                String com_token[] =login_id.split(computer_st);
                                String nurse_token[] =login_id.split(nurse_st);
                                if(com_token[0].equals(login_id)) { //ex 2013244094 틀렸을때 틀리면 nures랑 맞는지 비교
                                   if(!(nurse_token[0].equals(login_id))) {
                                      System.out.println("간호학과입니다."); 
                                      Media_MainPage f4 = new Media_MainPage();
                                      f4.Media_MainPage_run();
                                   }
                                }
                                if(nurse_token[0].equals(login_id)) { //ex 틀렸을때 틀리면 nures랑 맞는지 비교
                                   if(!(com_token[0].equals(login_id))) {
                                      System.out.println("컴퓨터학과입니다");
                                       MainPage f3 = new MainPage();
                                       f3.MainPage_run();
                                   }
                                }                          
                             }
                          }
                       }
              //틀린경우 경고 메세지 뜨게 작업하기
             }catch(NullPointerException e){
                e.printStackTrace();
             }catch(Exception e){
                 e.printStackTrace();
             }
         }

      });
      //전체적인 gui디자인 변경
      try {
         UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
         JFrame.setDefaultLookAndFeelDecorated(true);
         } catch (Exception e) {}
      //gui크기 변경
      setSize(466, 330);
      //클로우즈 누를시 화면 종료 어플리케이션 종료
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //gui 창 가운데로 r
      setLocationRelativeTo(null);
      //크기조절 X
      setResizable(false);
      //gui 제목변경
      setTitle("로그인 화면 ");
      //gui 보이게하기
      setVisible(true);

   }catch(Exception e) {
      e.printStackTrace();
   }
   }
}