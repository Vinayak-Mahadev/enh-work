package enh.team.interfaces.dates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;

public class DateOpration {

	public List<LocalDate> daysBetween(LocalDate startDate, LocalDate endDate)
	{
		int days = Days.daysBetween(startDate, endDate).getDays();
		List<LocalDate> dates = new ArrayList<LocalDate>(days);  // Set initial capacity to `days`.
		for (int i=0; i < days; i++) {
			LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i);
			dates.add(d);
		}
		dates.add(endDate);
		return dates;
	}

	public List<LocalDate> daysBetweenFromMonthStarttoGivenDate(final LocalDate date)
	{
		LocalDate startDate = new LocalDate(date.getYear(),date.getMonthOfYear(),1);

		int days = Days.daysBetween(startDate, date).getDays();
		List<LocalDate> dates = new ArrayList<LocalDate>(days);  // Set initial capacity to `days`.
		for (int i=0; i < days; i++) {
			LocalDate d = startDate.withFieldAdded(DurationFieldType.days(), i);
			dates.add(d);
		}
		dates.add(date);
		return dates;
	}
	
	public void calenderOpr(String format, String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat(format);
		calendar.setTime(simpleDateformat.parse(date));
		//System.out.println(calendar.getTime());
		calendar.add(Calendar.DATE, 1);
		System.out.println(calendar.getTime());
		
	}
}
