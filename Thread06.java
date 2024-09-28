/* 자바의 멀티스레드 프로그램에서 출금작업이 이루어 질 때 동기화를 처리하지 않아서 은행잔고가 음수가 나오는 예제 */

class Account2{
	private int balance = 1000;
	
	public int getBalance() {
		return balance;
	}
	
	public void withdraw(int money) {
		if(balance >= money) {
			try {
				Thread.sleep(1000);
			}catch(InterruptedException ie) {}
			
			balance -= money;
		}
	}//동기화 처리 안된 출금
}//Account2 class

class ThreadEx06 implements Runnable{
	Account2 acc = new Account2();
	
	@Override
	public void run() {
		while(acc.getBalance() > 0) {
			int money = (int)(Math.random()*3+1)*100;
			acc.withdraw(money);//출금
			System.out.println("계좌 잔액="+acc.getBalance());
		}
	}
}//ThreadEx06 스레드 클래스
public class Thread06 {
	public static void main(String[] args) {
		ThreadEx06 th = new ThreadEx06();
		new Thread(th).start();//멀티스레드 시작
		Thread th02=new Thread(th);
		th02.start();
	}

}
