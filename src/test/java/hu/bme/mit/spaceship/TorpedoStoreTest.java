package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fire_Success_WithZeroFailureRate() {
        TorpedoStore store = new TorpedoStore(5, 0.0);
        boolean result = store.fire(1);
        assertTrue(result);
        assertEquals(4, store.getTorpedoCount());
    }

    @Test
    void fire_Failure_WithFullFailureRate() {
        TorpedoStore store = new TorpedoStore(5, 1.0);
        boolean result = store.fire(1);
        assertFalse(result);
        assertEquals(5, store.getTorpedoCount()); 
    }

    @Test
    void fire_MultipleTorpedos_Success() {
        TorpedoStore store = new TorpedoStore(10, 0.0);
        boolean result = store.fire(3);
        assertTrue(result);
        assertEquals(7, store.getTorpedoCount());
    }

    @Test
    void fire_ThrowsException_WhenNumberOfTorpedosIsNegative() {
        TorpedoStore store = new TorpedoStore(5);
        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(-1);
        });
    }

    @Test
    void fire_ThrowsException_WhenNumberOfTorpedosExceedsAvailable() {
        TorpedoStore store = new TorpedoStore(5);
        assertThrows(IllegalArgumentException.class, () -> {
            store.fire(6);
        });
    }
}
