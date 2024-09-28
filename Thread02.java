/* 자바 멀티스레드 두번째 구현법)
 * 	Thread조상인 Runnable인터페이스를 구현상속 받는다. 이 방법은 다중 상속을 받을 수 있다는 장점이 있다.
 * 
 */
class ThreadExam02 implements Runnable{

	@Override
	public void run() {
		for(int num=1; num<=5; num++) {
			for(int k=1; k<100000000; k++);
			System.out.println(Thread.currentThread().getName() + " : "+num);
			//Thread.currentThread().getName()은 현재 실행중인 스레드 이름을 반환
		}
	}
}//ThreadExam02 class

public class Thread02 {

	public static void main(String[] args) {
		ThreadExam02 th01 = new ThreadExam02();
		ThreadExam02 th02 = new ThreadExam02();
		Thread t01 = new Thread(th01, "첫번째 스레드");
		Thread t02 = new Thread(th02, "두번째 스레드");
		t01.start();//스레드 시작
		t02.start();
	}

}
