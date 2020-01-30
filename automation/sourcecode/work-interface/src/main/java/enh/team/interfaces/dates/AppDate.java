package enh.team.interfaces.dates;

import org.joda.time.LocalDate;

class AppDate {


	public static void main(String[] args) {


		DateOpration dateOpration = new DateOpration();


		//		for (LocalDate localDate : dateOpration.daysBetween(new LocalDate(2020,01,1), new LocalDate()))
		//			System.out.println(localDate);


//		for (LocalDate localDate : dateOpration.daysBetweenFromMonthStarttoGivenDate(new LocalDate(2020,01,29)))
//			System.out.println(localDate);

		LocalDate localDate = new LocalDate();
		System.out.println(localDate);
		
		System.out.println("dayOfWeek :: "+localDate.dayOfWeek().get());
		System.out.println("getDayOfWeek :: "+localDate.getDayOfWeek());
		System.out.println("getWeekOfMonth :: "+localDate.getDayOfMonth()/7);
		
		
	}

}
