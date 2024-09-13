import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calendar.Meeting;
import calendar.MeetingCalendar;

class CalendarEventTest 
{

	OneTimeEvent A;
	OneTimeEvent A2;
	PriorityEvent B;
	WeeklyEvent C;
	WeeklyEvent C2;

	OneTimeEvent AB;
	MultiDayPerWeekEvent BC;
	MultiDayPerWeekEvent BC2;
	
	GregorianCalendar startA;
	GregorianCalendar endA;
	GregorianCalendar startAB;
	GregorianCalendar endAB;
	GregorianCalendar endB;
	GregorianCalendar endC;
	GregorianCalendar endW;
	GregorianCalendar endBC;
	GregorianCalendar endM;
	int[] days;
	
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
		endBC = new GregorianCalendar(2023,8,28,11,00);
		endW = new GregorianCalendar(2023,9,6,10,00);
		
		A = new OneTimeEvent("A","ALoc",startA,endA);
		A2 = new OneTimeEvent("A2","A2Loc",startA,endA);
		B = new PriorityEvent("B","BLoc",endA,endB);
		C = new WeeklyEvent("C","CLoc",endB,endC,endW);
		C2 = new WeeklyEvent("C2","C2Loc",endB,endC,endW);

		days = new int[] {Calendar.MONDAY,Calendar.TUESDAY};
		
		AB = new OneTimeEvent("AB","ABLoc",startAB,endAB);
		BC = new MultiDayPerWeekEvent("BC","BCLoc",endAB,endBC,endW,days);
		BC2 = new MultiDayPerWeekEvent("BC2","BC2Loc",endAB,endBC,endW,days);
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
	void testOneTimeDisplace()
	{
		A.scheduleEvent(cal);
		A2.scheduleEvent(cal);
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
		
		GregorianCalendar endQ = (GregorianCalendar) endB.clone();
		
		
		endQ.add((GregorianCalendar.DAY_OF_YEAR), 7);
		endC.add((GregorianCalendar.DAY_OF_YEAR), 7);
		M = cal.findMeeting(endQ);
		
		assertEquals(C.getDescription(),M.getDescription());
		assertEquals(C.getLocation(),M.getLocation());
		assertEquals(endQ,M.getStartTime());
		assertEquals(endC,M.getEndTime());
		
		endQ.add((GregorianCalendar.DAY_OF_YEAR), 7);
		assertNull(cal.findMeeting(endQ));
	}
	
	@Test
	void testWeeklyEventDisplace()
	{
		C.scheduleEvent(cal);
		C2.scheduleEvent(cal);
		Meeting M = cal.findMeeting(endB);
		
		assertEquals(C.getDescription(),M.getDescription());
		assertEquals(C.getLocation(),M.getLocation());
		assertEquals(C.getStartTime(),M.getStartTime());
		assertEquals(C.getEndTime(),M.getEndTime());
	}
	
	@Test
	void testMultiDayPerWeekEvent()
	{
		assertEquals("BC",BC.getDescription());
		assertEquals("BCLoc",BC.getLocation());
		assertEquals(endAB,BC.getStartTime());
		assertEquals(endBC,BC.getEndTime());
		assertEquals(endW,BC.getRepeatUntil());
		assertEquals(days,BC.getDays());
		
		BC.scheduleEvent(cal);
		
		GregorianCalendar startMulti = new GregorianCalendar(2023,9,2,10,00);
		GregorianCalendar endMulti = new GregorianCalendar(2023,9,2,11,00);
		Meeting M = cal.findMeeting(startMulti);
		
		assertEquals(BC.getDescription(),M.getDescription());
		assertEquals(BC.getLocation(),M.getLocation());
		assertEquals(startMulti,M.getStartTime());
		assertEquals(endMulti,M.getEndTime());
	
		startMulti.add((GregorianCalendar.DAY_OF_YEAR), 1);
		endMulti.add((GregorianCalendar.DAY_OF_YEAR), 1);
		
		M = cal.findMeeting(startMulti);
		
		assertEquals(BC.getDescription(),M.getDescription());
		assertEquals(BC.getLocation(),M.getLocation());
		assertEquals(startMulti,M.getStartTime());
		assertEquals(endMulti,M.getEndTime());
		
		for (int i = 2; i < 6; i++)
		{
			startMulti.add((GregorianCalendar.DAY_OF_YEAR), i);
			assertNull(cal.findMeeting(startMulti));
		}
		
	}
	
	@Test
	void testMultiDayPerWeekEventDisplace()
	{
		BC.scheduleEvent(cal);
		BC2.scheduleEvent(cal);
		
		GregorianCalendar startMulti = new GregorianCalendar(2023,9,2,10,00);
		GregorianCalendar endMulti = new GregorianCalendar(2023,9,2,11,00);
		Meeting M = cal.findMeeting(startMulti);
		
		assertEquals(BC.getDescription(),M.getDescription());
		assertEquals(BC.getLocation(),M.getLocation());
		assertEquals(startMulti,M.getStartTime());
		assertEquals(endMulti,M.getEndTime());
	}

}
