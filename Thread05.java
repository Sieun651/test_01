/* 스레드 스케줄링 메서드의 일시 정지해 주는 suspend(), suspend()에 의해서 일시 정지된 스레드를 다시 깨워서 실행되기 상태로 만들어주는 resume()메서드,
 * 스레드를 종료하는 stop()메서드에 대해서 학습하는 소스이다.
 */

class ThreadEx05 implements Runnable{

	@Override
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName());//현재 실행중인 스레드 이름을 반환
			
			try {
				Thread.sleep(1000); //1초간 해당 스레드 일시 정지
			}catch(InterruptedException ie) {}
		}
	}//스레드 문장 구현
}//멀티스레드 클래스 ThreadEx05
public class Thread05 {

	public static void main(String[] args) {
		ThreadEx05 t01 = new ThreadEx05();
		Thread th01 = new Thread(t01, "#");
		Thread th02 = new Thread(t01, "##");
		Thread th03 = new Thread(t01, "###");
		
		th01.start();//첫번째 스레드 시작
		th02.start();
		th03.start();
		
		//try,catch문 : 예외처리
		try {
			Thread.sleep(2000); //2초간 일시정지
			th01.suspend();//#스레드 일시 정지
			
			Thread.sleep(2000);
			th02.suspend();//2초 뒤에 ##스레드 일시 정지
			
			Thread.sleep(3000);
			th01.suspend();//3초 뒤에 #스레드를 다시 깨워서 동작하도록 한다.
			
			Thread.sleep(3000);
			th01.stop(); th02.stop(); //3초 뒤에 #, ## 스레드 중지
			
			Thread.sleep(2000);
			th03.stop();//2초 뒤에 ## 스레드 중지
		}catch(InterruptedException ie) {}
	}

}
