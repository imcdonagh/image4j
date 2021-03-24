package net.sf.image4j.codec.bmp;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class BMPDecoderTest {

    InputStream is;

    String filename = "/monochrome.bmp";

    @Before
    public void setUp() throws Exception {
        is = getClass().getResourceAsStream(filename);
    }

    @After
    public void tearDown() throws Exception {
        if (is != null) {
            is.close();
        }
    }

    @Test
    public void testMonochromeImage() throws Exception {
        assertNotNull("Test file missing",
                getClass().getResource(filename));
        BufferedImage image = BMPDecoder.read(is);

        assertEquals("Wrong width", 4, image.getWidth());
        assertEquals("Wrong height", 8, image.getHeight());
        assertEquals("Wrong image type", BufferedImage.TYPE_BYTE_BINARY, image.getType());

        Raster raster = image.getRaster();
        assertEquals("Wrong number of bands", 1, raster.getNumBands());
        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                if (x == y) {
                    assertEquals("Wrong pixel on the main diagonal", 1, raster.getSample(x, y, 0));
                } else {
                    assertEquals("Wrong pixel outside the main diagonal", 0, raster.getSample(x, y, 0));
                }
            }
        }
    }

}
