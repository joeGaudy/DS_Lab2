import java.util.GregorianCalendar;

import calendar.MeetingCalendar;

public class MultiDayPerWeekEvent extends CalendarEvent
{
	private GregorianCalendar repeatUntil;
	private int days[];
	
	/**
	 * @param description
	 * @param location
	 * @param startTime
	 * @param endTime
	 */
	public MultiDayPerWeekEvent(String description, String location, GregorianCalendar startTime,
			GregorianCalendar endTime, GregorianCalendar repeatUntil, int days[]) {
		super(description, location, startTime, endTime);
		this.repeatUntil = repeatUntil;
		this.days = days;
	}
	
	public void scheduleEvent(MeetingCalendar calendar)
	{
		
	}

	/**
	 * @return the repeatUntil
	 */
	public GregorianCalendar getRepeatUntil() {
		return repeatUntil;
	}

	/**
	 * @param repeatUntil the repeatUntil to set
	 */
	public void setRepeatUntil(GregorianCalendar repeatUntil) {
		this.repeatUntil = repeatUntil;
	}

	/**
	 * @return the days
	 */
	public int[] getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(int[] days) {
		this.days = days;
	}

	

}
