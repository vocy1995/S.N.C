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
	      
	    	result = JOptionPane.showConfirmDialog(null, "�ۼ��Ͻðڽ��ϱ�?", "Ȯ��",
                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.WARNING_MESSAGE, null);
	    }
	    //�����Խ���(new_board_write)�� Ŭ�������� �ۼ� ��ư�� �������� Ȯ�� �޽����� ����ϱ� ���� Ŭ�����Դϴ�.
}
