/* 자바의 멀티스레드란?
 * 하나의 프로그램내에서 여러개의 작업이 동시 수행되는 것을 말한다.
 * 
 * 멀티스레드 구현법)
 * 첫번째 방법, Thread클래스를 상속받는 것이다. 이 클래스를 상속받으면 단일상속만 가능하다라는 단점이 있다.
 * 자동 정렬 ctrl+A -> ctrl+I
 */
class ThreadExam01 extends Thread{
	public ThreadExam01(String name) {
		super(name);//부모클래스 오버로딩 된 생성자를 호출
	}//생성자 오버로딩

	@Override
	public void run() {
		for(int num=1; num<=5; num++) {
			for(int k=1; k<100000000; k++);
			System.out.println(getName() + " : "+ num); //getName()메서드로 쓰레드 이름을 반환
		}
	}//멀티스레드 문장 구현

}//ThreadExam01 class
public class Thread01 {
	public static void main(String[] args) {
		ThreadExam01 th01=new ThreadExam01("첫번째 스레드");
		ThreadExam01 th02=new ThreadExam01("두번째 스레드");
		th01.start(); //스레드 시작
		th02.start();
	}

}
