import java.util.ArrayList;

/*
 * 요리사 쓰레드 Cook, 손님 소비자스레드 Customer, Table클래스 등을 활용하여 동기화와 멀티스레드 스케줄링 메서드에 대해서 알 수 있는 소스이다.
 */
class Table{
	String[] dishName = {"donut", "donut", "burger"}; //음식 재료를 저장한 문자열 배열, 배열크기는 3
	final int MAX_FOOD = 6;
	private ArrayList<String> dishes = new ArrayList<>(); //요리사가 추가한 음식을 저장할 컬렉션 제네릭, 문자열만 저장 가능함

	public synchronized void add(String dish) {
		while(dishes.size() >= MAX_FOOD) {
			String name = Thread.currentThread().getName(); //현재 실행중인 스레드이름을 반환
			System.out.println(name+"is wait");

			try {
				wait(); //요리사 Cook 스레드를 기다리게 한다.
				Thread.sleep(500);
			}catch(InterruptedException ie) {}
		}//while

		dishes.add(dish); //테이블에 음식 추가
		notify(); //기다리고 있는 소비자 Customer 스레드를 깨워서 음식을 먹게 함
		System.out.println("dishes:"+dishes.toString());
	}//테이블에 음식 추가 메서드 => 동기화 처리됨

	public void remove(String dishName) {
		synchronized (this) {
			String name = Thread.currentThread().getName();
			
			while(dishes.size() == 0) { //테이블에 음식이 없는 경우
				System.out.println(name+"is waiting");
				
				try {
					wait(); //소비자 스레드를 대기실로 보내서 기다리게 한다.
					Thread.sleep(500);
				}catch(InterruptedException ie) {}
			}//while
			
			while(true) {
				for(int i=0; i<dishes.size(); i++) {
					if(dishName.equals(dishes.get(i))) {
						dishes.remove(i); //컬렉션으로 부터 음식을 제거
						notify(); //대기하고 있는 요리사 쓰레드를 깨운다.
						return; //종료
					}
				}//for
				
				try {
					System.out.println(name+"is waiting");
					wait(); //소비자를 대기실로 보낸다.
				}catch(InterruptedException ie) {}
			}//while무한루프 반복문
		}//동기화(임계영역)
	}//remove()
	
	public int dishNum() {
		return dishName.length; //음식 재료 개수 3을 반환
	}
}//Table class

//소비자 스레드
class Customer implements Runnable{
	private Table table;
	private String food;
	
	Customer(Table table, String food){
		this.table = table;
		this.food = food;
	}//생성자 오버로딩
	
	@Override
	public void run() {
		while(true) {
			try {Thread.sleep(100);}catch(InterruptedException e) {}
			String name = Thread.currentThread().getName();
			
			table.remove(food); //소비자가 음식을 먹으면 테이블에서 먹은 음식을 제거
			System.out.println(name+ " ate a "+food);
		}//while
	}//스레드 문장을 구현
}//Customer class

//요리사 스레드
class Cook implements Runnable{
	private Table table;
	
	Cook(Table table){
		this.table=table;
	}
	
	@Override
	public void run() {
		while(true) {
			int idx = (int)(Math.random() * table.dishNum()); //0,1,2중 임의의 정수 숫자 난수를 구함.
			table.add(table.dishName[idx]); //요리사가 테이블에 음식을 추가
			try {Thread.sleep(10);}catch(InterruptedException e) {}
		}//while
	}//run()
}//Cook class
public class Thread07 {

	public static void main(String[] args) throws Exception{
		Table table = new Table();
		
		new Thread(new Cook(table), "COOK01").start();//COOK01 요리사 스레드 시작
		new Thread(new Customer(table, "donut"),"CUST01");//첫번째 소비자 스레드 CUST01 시작
	}

}
