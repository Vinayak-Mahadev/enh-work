package enh.team.interfaces.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class App {

	public static void main(String[] args) 
	{
		SimpleDateFormat simpleDateFormat = null;
		try 
		{
			simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");


			System.out.println(simpleDateFormat.parse("20200626000002"));
			System.out.println(simpleDateFormat.parse("20200626000002_10"));
			System.out.println(simpleDateFormat.parse("20200626000002_08"));
			
		} 
		catch (Exception e) 
		{

		}
	}

	 void sample()
	{

		//tester();
		Set<String> fileNameList = new HashSet<String>();
		Set<String> successList = new HashSet<String>();
		Set<String> failList = new HashSet<String>();

		String orgFileName = "total_revenue";
		String name  = null;
		String temp  = null;
		//Date date = null;
		SimpleDateFormat simpleDateFormat = null;

		fileNameList.add("total_revenue_20200626000002.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_10.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_08.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_04.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_03.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_05a.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_09.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_07.ctl.gz");
		fileNameList.add("total_revenue_bi_20200626000002_06.ctl.gz");

		for (String fileName : fileNameList) 
		{
			simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			try 
			{
				name = fileName;
				temp = name.replaceAll(orgFileName + "_", "");
				temp = temp.replaceAll("." + "ctl" + "." + "gz", "");
	//			System.out.println(fileName + "   :::   "+ temp);
				simpleDateFormat.parse(temp);
	//			System.out.println(fileName + "   :::   "+ date);
				successList.add(fileName);
			} 
			catch (ParseException e) 
			{
				failList.add(fileName);
			}
		}

		System.out.println(successList);
		System.out.println(failList);

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
