package person.hong.learn.api.utils;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MemoryRandomImageTest {

    @Test
    void getImage() {
        MemoryRandomImage randomImage = new MemoryRandomImage();
        randomImage.getImage();
    }
}