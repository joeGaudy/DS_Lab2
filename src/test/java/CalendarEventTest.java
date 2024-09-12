import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calendar.Meeting;
import calendar.MeetingCalendar;

class CalendarEventTest 
{

	
	OneTimeEvent A;
	MultiDayPerWeekEvent B;
	MultiDayPerWeekEvent C;
	

	MultiDayPerWeekEvent AB;
	MultiDayPerWeekEvent BC;
	
	GregorianCalendar startA;
	GregorianCalendar endA;
	GregorianCalendar startAB;
	GregorianCalendar endAB;
	GregorianCalendar endB;
	GregorianCalendar endC;
	
	MeetingCalendar cal;
	
	@BeforeEach
	void setUp() throws Exception
	{
		
		cal = new MeetingCalendar();
		startA = new GregorianCalendar(2023,8,28,8,30);
		endA = new GregorianCalendar(2023,8,28,9,30);
		endB = new GregorianCalendar(2023,8,28,10,30);
		endC = new GregorianCalendar(2023,8,28,11,30);
		
		startAB = new GregorianCalendar(2023,8,28,9,00);
		endAB = new GregorianCalendar(2023,8,28,10,00);
		GregorianCalendar endBC = new GregorianCalendar(2023,8,28,11,00);
		
		
		
		A = new OneTimeEvent("A","ALoc",startA,endA);
		//B = new MultiDayPerWeekEvent("B","BLoc",endA,endB);
		//C = new MultiDayPerWeekEvent("C","CLoc",endB,endC);

		
		//AB = new MultiDayPerWeekEvent("AB","ABLoc",startAB,endAB);
		//BC = new MultiDayPerWeekEvent("BC","BCLoc",endAB,endBC);
	}
	
	@Test
	void testOneTimeEvent() 
	{
		assertEquals("A",A.getDescription());
		assertEquals("ALoc",A.getLocation());
		assertEquals(startA,A.getStartTime());
		assertEquals(endA,A.getEndTime());
		
		A.scheduleEvent(cal);
		assertEquals(A.getDescription(),cal.findMeeting(startA)); 
		assertEquals(A,cal.findMeeting(endA));
		
		
	}
	
	

}
