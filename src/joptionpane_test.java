import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class joptionpane_test  extends JFrame {

	     
	    private joptionpane_test F = this;
	    int result;
	    public void joptionpane_test() {
	      
	    	result = JOptionPane.showConfirmDialog(null, "작성하시겠습니까?", "확인",
                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.WARNING_MESSAGE, null);
	    }
	    //자유게시판(new_board_write)의 클래스에서 작성 버튼을 눌렀을시 확인 메시지를 사용하기 위한 클래스입니다.
}
