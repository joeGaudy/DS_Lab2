import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calendar.Meeting;
import calendar.MeetingCalendar;

class CalendarEventTest 
{

	
	OneTimeEvent A;
	PriorityEvent B;
	WeeklyEvent C;
	

	OneTimeEvent AB;
	MultiDayPerWeekEvent BC;
	
	GregorianCalendar startA;
	GregorianCalendar endA;
	GregorianCalendar startAB;
	GregorianCalendar endAB;
	GregorianCalendar endB;
	GregorianCalendar endC;
	GregorianCalendar endW;
	
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
		endW = new GregorianCalendar(2023,9,5,10,00);
		
		A = new OneTimeEvent("A","ALoc",startA,endA);
		B = new PriorityEvent("B","BLoc",endA,endB);
		C = new WeeklyEvent("C","CLoc",endB,endC,endW);

		
		AB = new OneTimeEvent("AB","ABLoc",startAB,endAB);
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
		Meeting M = cal.findMeeting(startA); 
		
		assertEquals(A.getDescription(),M.getDescription());
		assertEquals(A.getLocation(),M.getLocation());
		assertEquals(A.getStartTime(),M.getStartTime());
		assertEquals(A.getEndTime(),M.getEndTime());
		
	}
	
	@Test
	void testPriorityEvent()
	{
		assertEquals("B",B.getDescription());
		assertEquals("BLoc",B.getLocation());
		assertEquals(endA,B.getStartTime());
		assertEquals(endB,B.getEndTime());
		
		AB.scheduleEvent(cal);
		
		B.scheduleEvent(cal);
		
		Meeting M = cal.findMeeting(endA);
		
		assertNull(cal.findMeeting(startAB));
		
		assertEquals(B.getDescription(),M.getDescription());
		assertEquals(B.getLocation(),M.getLocation());
		assertEquals(B.getStartTime(),M.getStartTime());
		assertEquals(B.getEndTime(),M.getEndTime());
		
	}
	
	@Test
	void testWeeklyEvent()
	{
		assertEquals("C",C.getDescription());
		assertEquals("CLoc",C.getLocation());
		assertEquals(endB,C.getStartTime());
		assertEquals(endC,C.getEndTime());
		assertEquals(endW,C.getRepeatUntil());
		
		C.scheduleEvent(cal);
		
		Meeting M = cal.findMeeting(endB);
		
		assertEquals(C.getDescription(),M.getDescription());
		assertEquals(C.getLocation(),M.getLocation());
		assertEquals(C.getStartTime(),M.getStartTime());
		assertEquals(C.getEndTime(),M.getEndTime());
		
		endW.add((GregorianCalendar.DAY_OF_MONTH), 7);
		assertEquals(C,cal.findMeeting(endW));
		
		endW.add((GregorianCalendar.DAY_OF_MONTH), 14);
		assertNull(cal.findMeeting(endW));
	}
	
	

}
