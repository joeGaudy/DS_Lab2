import java.util.GregorianCalendar;

import calendar.Meeting;
import calendar.MeetingCalendar;

public class WeeklyEvent extends CalendarEvent
{
	private GregorianCalendar repeatUntil;
	
	/**
	 * @param description
	 * @param location
	 * @param startTime
	 * @param endTime
	 */
	public WeeklyEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime, GregorianCalendar repeatUntil) {
		super(description, location, startTime, endTime);
		this.repeatUntil = repeatUntil;
	}
	
	public void scheduleEvent(MeetingCalendar calendar)
	{
		Meeting M = new Meeting(getDescription(), getLocation(), getStartTime(), getEndTime());
		
		while (M.getEndTime().after(getRepeatUntil()) == false)
		{
			calendar.addMeeting(M);
			M.getStartTime().add((GregorianCalendar.DAY_OF_YEAR), 7);
			M.getEndTime().add((GregorianCalendar.DAY_OF_YEAR), 7);
			
			
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

}

