import java.util.GregorianCalendar;

import calendar.Meeting;
import calendar.MeetingCalendar;

public class OneTimeEvent extends CalendarEvent 
{
	/**
	 * @param description
	 * @param location
	 * @param startTime
	 * @param endTime
	 */
	public OneTimeEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime) {
		super(description, location, startTime, endTime);
	}

	public void scheduleEvent(MeetingCalendar calendar)
	{
		
		Meeting M = new Meeting(getDescription(), getLocation(), getStartTime(), getEndTime());
		calendar.addMeeting(M);
	}
	
}
