/* 자바 멀티스레드 동기화 기법이란
 *  출금작업과 같은 스레드 동작이 이루어 지는 경우 락을 걸어서 하나의 스레드에 의해서만 해당 작업이 이루어 지도록 하는 것을 말한다. 이렇게 멀티스레드 프로그램
 *  내에서 하나의 스레드에 의해서만 스레드 작업을 처리할 수 있는 영역을 임계영역이라고 한다. 곧 동기화 영역이다.
 *  
 *  동기화를 만들 때는 synchronized 키워드를 사용한다.
 */
class Account{
	private int balance = 1000; //계좌 잔액
	
	public int getBalance() {
		return balance; //계좌 잔액 반환
	}
	
	public synchronized void withdraw(int money) {
		if(balance >= money) {
			try {
				Thread.sleep(1000);// 1초동안 스레드를 잠시 쉰다.
			}catch (InterruptedException ie) {}
			
			balance -= money;
		}
	}//출금 작업이 이루어 질 때 
}//Account 계좌 클래스

class RunnableEx03 implements Runnable{
	Account acc = new Account();
	
	@Override
	public void run() {
		while(acc.getBalance() > 0) {
			int money = (int)(Math.random()*3+1)*100; /* random()메서드는 0.0이상 1.0미만 사이의 실수 숫자 난수를 발생 => *3하면
			0.0이상 3.0미만 실수 숫자 난수가 발생 => +1을 하면 1.0이상 4.0미만 사이 실수 숫자 난수가 발생 => (int)로 형변환 하면 반올림 하지 않고 소수점
			이하는 버리고 정수 숫자만 구해서 1이상 4미만 사이의 정수숫자 난수를 구함 =>*100하면 100이상 400미만 사이의 정수 숫자 난수를 구함
			*/
			acc.withdraw(money); //출금작업
			System.out.println("잔액:"+acc.getBalance());	
		}
	}//스레드 문장을 구현
}//RunnableEx03 스레드 클래스
public class Thread03 {
	public static void main(String[] args) {
		RunnableEx03 r01 = new RunnableEx03();
		new Thread(r01).start();//스레드 시작
		new Thread(r01).start();
	}

}
