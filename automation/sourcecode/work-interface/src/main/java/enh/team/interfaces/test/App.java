package enh.team.interfaces.test;

public class App {

	public static void main(String[] args) 
	{
		//tester();

	}

	static void tester() 
	{

		System.out.println("TransType,PaymentType,OrderStatus,isRecordAccept");


		System.out.println(test("22", null, null));
		System.out.println(test("22", null, "2"));
		System.out.println(test("22", "2", null));
		System.out.println(test("22", "2", "2"));

		System.out.println(test("22", null, null));
		System.out.println(test("22", null, "1"));
		System.out.println(test("22", "1", null));
		System.out.println(test("22", "1", "1"));


		System.out.println(test("23", null, null));
		System.out.println(test("23", null, "2"));
		System.out.println(test("23", "2", null));
		System.out.println(test("23", "2", "2"));

		System.out.println(test("23", null, null));
		System.out.println(test("23", null, "1"));
		System.out.println(test("23", "1", null));
		System.out.println(test("23", "1", "1"));


		System.out.println(test(null, null, null));
		System.out.println(test("2", "1", null));
		System.out.println(test("2", "1", "1"));
		System.out.println(test("2", "1", "2"));

		System.out.println(test("1", null, null));
		System.out.println(test("1", "1", null));
		System.out.println(test("1", "1", "1"));
		System.out.println(test("1", "1", "2"));

		System.out.println(test("2", null, null));
		System.out.println(test(null, "1", null));
		System.out.println(test(null, "1", "1"));
		System.out.println(test(null, "1", "2"));




	}


	static boolean test(String transType, String paymentType, String orderStatus) {
		System.out.print(transType+","+paymentType+","+orderStatus+",");
		boolean status = true;

		if(transType != null && transType.equals("22") 
				&& paymentType != null && paymentType.equals("2")
				&& orderStatus != null && orderStatus.equals("2")) {
			//success
			status = true;
		}
		if(transType != null && transType.equals("22") 
				&& paymentType != null && (paymentType.equals("1") || paymentType.equals("2"))
				&& orderStatus != null && (orderStatus.equals("1") || orderStatus.equals("2")) ) {
			//fail
			status = false;
		}
		if(transType != null && transType.equals("23") 
				&& paymentType != null && paymentType.equals("1")
				&& orderStatus != null && orderStatus.equals("1")) {
			//success
			status = true;
		}
		if(transType != null && transType.equals("23") 
				&& paymentType != null && (paymentType.equals("1") || paymentType.equals("2"))
				&& orderStatus != null && (orderStatus.equals("1") || orderStatus.equals("2")) ) {
			//fail
			status = false;
		}
		if(transType != null && (transType.equals("22") || transType.equals("23"))
				&& (paymentType == null || orderStatus == null)) {
			//fail
			status = false;
		}

		return status;
	}

}
