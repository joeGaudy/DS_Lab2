import java.util.GregorianCalendar;

import calendar.MeetingCalendar;

public class PriorityEvent extends CalendarEvent
{
	
	/**
	 * @param description
	 * @param location
	 * @param startTime
	 * @param endTime
	 */
	public PriorityEvent(String description, String location, GregorianCalendar startTime, GregorianCalendar endTime) {
		super(description, location, startTime, endTime);
	}

	public void scheduleEvent(MeetingCalendar calendar)
	{
		
	}


}
