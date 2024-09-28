import javax.swing.JOptionPane;

class ThreadEx04 extends Thread{

	@Override
	public void run() {
		for(int i=10; i>0; i--) {
			System.out.println(i);
			try {
				sleep(1000); //1초간 스레드 일시 정지
			}catch(Exception e) {}
		}
	}//스레드 문장을 구현
}
public class Thread04 {
	public static void main(String[] args) throws Exception {

		ThreadEx04 th04 = new ThreadEx04();
		th04.start(); //스레드 시작
		
		String addr = JOptionPane.showInputDialog("주소 입력:");
		/*javax.swing패키지의 JOptionPane하위의 showInputDialog()정적 메서드는 메시지를 담고 입력필드를 가진 스윙 메시지를 박스를 띄운다.
		 */
		System.out.println("입력한 주소:"+addr);
	}
}
