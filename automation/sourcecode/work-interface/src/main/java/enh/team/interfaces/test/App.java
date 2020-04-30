package enh.team.interfaces.test;

public class App {

	public static void main(String[] args) {

		System.out.println(test("22", "2", "2"));
		System.out.println(test("23", "1", "1"));

		System.out.println(test("22", "1", "2"));
		System.out.println(test("22", "2", "1"));
		System.out.println(test("22", "2", null));
		System.out.println(test("22", null, null));

		System.out.println(test("23", "1", "2"));
		System.out.println(test("23", "2", "1"));
		System.out.println(test("23", "1", null));
		System.out.println(test("23", null, null));


		System.out.println(test("2", "1", "2"));
		System.out.println(test("1", "1", "1"));
		System.out.println(test(null, "1", "2"));
		System.out.println(test("1", null, "1"));
	}

	

	static boolean test(String transType, String paymentType, String orderStatus) {

		
		if(transType != null && transType.equals("22") 
				&& paymentType != null && paymentType.equals("2")
				&& orderStatus != null && orderStatus.equals("2")) {
			//success
			return true;
		}
		else if(transType != null && transType.equals("23") 
				&& paymentType != null && paymentType.equals("1")
				&& orderStatus != null && orderStatus.equals("1")) {
			//success
			return true;
		}
		else if(transType != null && (transType.equals("22") || transType.equals("23"))) {
			//fail
			return false;
		}
		return true;
	}

}
