package fr.projetpoo.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import terminaltographique.Game;
import terminaltographique.Pawn;

class TestIsInLine {

	int type = 4;
	Pawn p11 = new Pawn(1, 1);
	Pawn p12 = new Pawn(1, 2);
	Pawn p13 = new Pawn(1, 3);
	Pawn p14 = new Pawn(1, 4);
	Pawn p15 = new Pawn(1, 5);
	Pawn p16 = new Pawn(1, 6);
	Pawn p17 = new Pawn(1, 7);
	Pawn p18 = new Pawn(1, 8);
	Pawn p21 = new Pawn(2, 1);
	Pawn p22 = new Pawn(2, 2);
	Pawn p23 = new Pawn(2, 3);
	Pawn p24 = new Pawn(2, 4);
	Pawn p25 = new Pawn(2, 5);
	Pawn p26 = new Pawn(2, 6);
	Pawn p27 = new Pawn(2, 7);
	Pawn p28 = new Pawn(2, 8);
	Pawn p31 = new Pawn(3, 1);
	Pawn p32 = new Pawn(3, 2);
	Pawn p33 = new Pawn(3, 3);
	Pawn p34 = new Pawn(3, 4);
	Pawn p35 = new Pawn(3, 5);
	Pawn p36 = new Pawn(3, 6);
	Pawn p37 = new Pawn(3, 7);
	Pawn p38 = new Pawn(3, 8);
	Game g = new Game();

	@Test
	void test() {

		try {
            // In line verification on this Axis :
            //  (1,2)-----(2,2)------(3,2) 

		    // In order :

			assertTrue(g.isInLine(p11, p12, p13));
			assertTrue(g.isInLine(p15, p16, p17));
			assertTrue(g.isInLine(p21, p22, p23));
			assertTrue(g.isInLine(p25, p26, p27));
            assertTrue(g.isInLine(p31, p32, p33));
			assertTrue(g.isInLine(p35, p36, p37));

            assertTrue(g.isInLine(p14, p24, p34));
			assertTrue(g.isInLine(p38, p28, p18));

		
			// In disorder :
		
			assertTrue(g.isInLine(p12, p11, p13));
			assertTrue(g.isInLine(p17, p15, p16));
			assertTrue(g.isInLine(p22, p23, p21));
			assertTrue(g.isInLine(p25, p27, p26));
            assertTrue(g.isInLine(p32, p33, p31));
			assertTrue(g.isInLine(p36, p35, p37));

            assertTrue(g.isInLine(p34, p14, p24));
			assertTrue(g.isInLine(p18, p38, p28));

		    // In line verification on this Axis :
		    //       (1,1)  
		    //         |
		    //       (1,2)  
		    //         |
		    //       (1,3)  

            // In order :
            
			assertTrue(g.isInLine(p13, p14, p15));
			assertTrue(g.isInLine(p17, p18, p11));
			assertTrue(g.isInLine(p23, p24, p25));
			assertTrue(g.isInLine(p27, p28, p21));
            assertTrue(g.isInLine(p33, p34, p35));
			assertTrue(g.isInLine(p37, p38, p31));
		
			// In disorder :
		
			assertTrue(g.isInLine(p14, p13, p15));
			assertTrue(g.isInLine(p18, p17, p11));
			assertTrue(g.isInLine(p25, p23, p24));
			assertTrue(g.isInLine(p21, p27, p28));
            assertTrue(g.isInLine(p35, p33, p34));
			assertTrue(g.isInLine(p31, p37, p38));
		
	        /* In line verification on the corner :
	
	                (1,1)--(2,1)--(3,1)
	                  |      |      | 
	        (1,3)---(1,2)    |      |
	          |              |      |            
	        (2,3)----------(2,2)    |
	          |                     |
	        (3,3)-----------------(3,2)

	        */
            
			assertFalse(g.isInLine(p12, p13, p14));
            assertFalse(g.isInLine(p14, p15, p16));
		    assertFalse(g.isInLine(p16, p17, p18));
            assertFalse(g.isInLine(p18, p11, p12));
            
            assertFalse(g.isInLine(p22, p23, p24));
            assertFalse(g.isInLine(p24, p25, p26));
		    assertFalse(g.isInLine(p26, p27, p28));
            assertFalse(g.isInLine(p28, p21, p22));

            assertFalse(g.isInLine(p32, p33, p34));
            assertFalse(g.isInLine(p34, p35, p36));
		    assertFalse(g.isInLine(p36, p37, p38));
            assertFalse(g.isInLine(p38, p31, p32));
		
		
		} catch (ArithmeticException e) {
			System.out.println(e.getMessage() + " impossible.");
		}
	}
}