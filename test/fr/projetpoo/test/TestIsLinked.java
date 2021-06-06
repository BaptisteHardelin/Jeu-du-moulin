package fr.projetpoo.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import terminaltographique.Node;

class TestIsLinked {

    int type = 4;
    Node c11 = new Node (1,1);
    Node c21 = new Node (2,1);
    Node c31 = new Node (3,1);
    Node c12 = new Node (1,2);
    Node c22 = new Node (2,2);
    Node c32 = new Node (3,2);
    Node c13 = new Node (1,3);
    Node c23 = new Node (2,3);
    Node c33 = new Node (3,3);
    Node c37 = new Node (3,7);
    Node c38 = new Node (3,8);

    @Test
	void test() {

        // Link verification on this Axis :
        //  (1,2)-----(2,2)------(3,2) 

		assertTrue(Node.isLinked(c12,c22,type));
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
        assertTrue(Node.isLinked(c37,c38,type));
        assertTrue(Node.isLinked(c38,c37,type));
        assertFalse(Node.isLinked(c11,c13,type));
        assertFalse(Node.isLinked(c13,c11,type));

        /* Link verification on this Axis :

                  (exemple)----(exemple)----(exemple)
                      |            |              | 
        (exemple)---(1,1)          |              |
            |                      |              |            
        (exemple)----------------(2,1)            |
            |                                     |
        (exemple)-------------------------------(3,1)
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