package fr.projetpoo.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import terminaltographique.Pawn;
import terminaltographique.Node;

class TestIsPossible{


    int type = 4 ;
    protected static Pawn c11 = new Pawn (1,1);
    protected static Pawn c12 = new Pawn (1,2);
    protected static Pawn c13 = new Pawn (1,3);
    protected static Pawn c14 = new Pawn (1,4);
    protected static Pawn c15 = new Pawn (1,5);
    protected static Pawn c16 = new Pawn (1,6);
    protected static Pawn c21 = new Pawn (2,1);
    protected static Pawn c22 = new Pawn (2,2);
    protected static Pawn c23 = new Pawn (2,3);
    protected static Pawn c31 = new Pawn (3,3);
    protected static Pawn c32 = new Pawn (3,2);
    protected static Pawn c33 = new Pawn (3,3);
    protected static Pawn c35 = new Pawn (3,5);
    protected static Pawn c36 = new Pawn (3,6);

    @Test
	void test() {

        // Link verification on this Axis :
        //  (1,2)-----(2,2)------(3,2) 

		assertTrue(Node.isLinked(c11,c12,type));
        assertTrue(Node.isLinked(c22,c32,type));
        assertTrue(Node.isLinked(c32,c22,type));
        assertTrue(Node.isLinked(c22,c12,type));
        assertFalse(Node.isLinked(c12,c32,type));
        assertFalse(Node.isLinked(c32,c12,type));

        // Link verification on this Axis :
        //  (1,1)
        //    |
        //  (1,2)  
        //    |
        //  (1,3) 

        assertTrue(Node.isLinked(c11,c12,type));
        assertTrue(Node.isLinked(c12,c13,type));
        assertTrue(Node.isLinked(c13,c12,type));
        assertTrue(Node.isLinked(c12,c11,type));
        assertTrue(Node.isLinked(c35,c36,type));
        assertTrue(Node.isLinked(c36,c35,type));
        assertFalse(Node.isLinked(c11,c13,type));
        assertFalse(Node.isLinked(c13,c11,type));

        /* Link verification on this Axis :

                  (exemple)--(exemple)--(exemple)
                      |          |          | 
        (exemple)---(1,1)        |          |
            |                    |          |            
        (exemple)--------------(2,1)        |
            |                               |
        (exemple)-------------------------(3,1)
        */

        assertFalse(Node.isLinked(c11,c21,type));
        assertFalse(Node.isLinked(c21,c31,type));
        assertFalse(Node.isLinked(c31,c21,type));
        assertFalse(Node.isLinked(c21,c11,type));
        assertFalse(Node.isLinked(c11,c31,type));
        assertFalse(Node.isLinked(c31,c11,type));
        
        // Link verification on random position in the map :

        assertFalse(Node.isLinked(c11,c33,type));
        assertFalse(Node.isLinked(c32,c21,type));
        assertFalse(Node.isLinked(c22,c13,type));
        assertFalse(Node.isLinked(c12,c31,type));
        assertFalse(Node.isLinked(c23,c11,type));

	}
}