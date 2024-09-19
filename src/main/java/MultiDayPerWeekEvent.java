import java.util.GregorianCalendar;

import calendar.Meeting;
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
	
	public boolean checker(GregorianCalendar C)
	{
		for (int i = 0; i < days.length; i++)
		{
			if (days[i] == C.get(GregorianCalendar.DAY_OF_WEEK))
			{
				return true;
			}
		}
		return false;
	}
	
	public void scheduleEvent(MeetingCalendar calendar)
	{
		Meeting M = new Meeting(getDescription(), getLocation(),(GregorianCalendar) getStartTime().clone(), (GregorianCalendar) getEndTime().clone());
		
		while (M.getEndTime().after(getRepeatUntil()) == false)
		{
			if (checker(M.getStartTime())== true)
			{
				calendar.addMeeting(M);
			}
			
			M.getStartTime().add((GregorianCalendar.DAY_OF_WEEK), 1);
			M.getEndTime().add((GregorianCalendar.DAY_OF_WEEK), 1);
			
		}
		
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
