package enh.team.interfaces.dates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

class AppDate {


	public static void main(String[] args) throws ParseException {


		//DateOpration dateOpration = new DateOpration();


		//		for (LocalDate localDate : dateOpration.daysBetween(new LocalDate(2020,01,1), new LocalDate()))
		//			System.out.println(localDate);


//		for (LocalDate localDate : dateOpration.daysBetweenFromMonthStarttoGivenDate(new LocalDate(2020,01,29)))
//			System.out.println(localDate);

		/*
		 * LocalDate localDate = new LocalDate(); localDate =
		 * localDate.withDayOfWeek(DateTimeConstants.MONDAY);
		 * System.out.println(localDate);
		 * 
		 * System.out.println("dayOfWeek :: "+localDate.dayOfWeek().get());
		 * System.out.println("getDayOfWeek :: "+localDate.getDayOfWeek());
		 * System.out.println("getWeekOfMonth :: "+localDate.getDayOfMonth()/7);
		 * System.out.println("withDayOfWeek :: "+localDate.withDayOfWeek(2));
		 */
		
		/*ArrayList<Integer> dayFrombeatScheduleMap = new ArrayList<Integer>();
		for(int i = 1; i < 8; i ++)
		{
			dayFrombeatScheduleMap.add(i);
			i++;
		}
		System.out.println("dayFrombeatScheduleMap : " + dayFrombeatScheduleMap);
		Calendar currentDate = Calendar.getInstance();
		Calendar monthEndCalendar = Calendar.getInstance();
		monthEndCalendar.set(Calendar.DAY_OF_MONTH, monthEndCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		int daysLeftForMonthEnd = (int) ((monthEndCalendar.getTimeInMillis() - currentDate.getTimeInMillis())/(1000*60*60*24));
		
		LocalDate start = new LocalDate().plusDays(1);
		System.out.println("start : " + start);
		LocalDate configuredDate = new LocalDate().plusDays(daysLeftForMonthEnd);
		ArrayList<Date> futureDates = new ArrayList<Date>(); 
		
		for (LocalDate date = start; !date.isAfter(configuredDate);) 
		{ 
//			System.out.println("date : " + date);
			for(int day : dayFrombeatScheduleMap) 
			{ 
				date = date.withDayOfWeek(day);
				if(date != null && !date.isBefore(start) && !date.isAfter(configuredDate))
					futureDates.add(date.toDate()); 
			} 
			
			date = date.plusDays(1);
		}
		
		System.out.println("futureDates : " + futureDates.size());
		for(Date date : futureDates)
		{
			System.out.println("Future Date : " + date);
		}
		System.out.println(new Date());
		Date dt = new SimpleDateFormat("dd-MM-yyyy").parse("07-03-2020");
		System.out.println(dt + " : " + dt.getDay());
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		System.out.println(c.getTime());
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		LocalDate date = new LocalDate();
		System.out.println(date.toDate());
		date = date.plusDays(-3);
		System.out.println(date.getDayOfWeek());*/
//		Date dt = new SimpleDateFormat("dd-MM-yyyy").parse("07-03-2020");
		/*Date dt = new Date();
		System.out.println(dt + " : " + dt.getDay());
		
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		
		Calendar endDt = Calendar.getInstance();
		endDt.add(Calendar.DATE, 1);
		
		int num = (int) (endDt.getTimeInMillis() - c.getTimeInMillis())/(1000*60*60*24);
		System.out.println(num);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String endFormat = format.format(endDt.getTime());
		Calendar cal = Calendar.getInstance();
		
		  for(int i = 0; i < num; i ++) 
		  { 
			  c.add(Calendar.DATE, 1);
			  System.out.println(c.getTime());
		  }*/
		
		SimpleDateFormat dd_MM_yyyy = new SimpleDateFormat("dd-MM-yyyy");
		HashSet<String> holidays = new HashSet<String>();
		Calendar holidayStart = Calendar.getInstance();
		holidayStart.setTime(new Date());
		holidays.add(dd_MM_yyyy.format(new Date()));
		
		Calendar holidayEnd = Calendar.getInstance();
		holidayEnd.add(Calendar.DATE, 1);
		
		int numOfHolidays = (int) ((holidayEnd.getTimeInMillis() - holidayStart.getTimeInMillis())/(1000*60*60*24));
		
		for(int i = 0; i < numOfHolidays; i ++)
		{
			holidayStart.add(Calendar.DATE, 1);
			holidays.add(dd_MM_yyyy.format(holidayStart.getTime()));
		}
		
		System.out.println(holidays);
	}

}
