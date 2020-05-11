package net.sf.image4j;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import net.sf.image4j.codec.ico.ICODecoder;

public class ICODecoderTest {

	@Test
	public void checkSkipOnTooLongLayerDefinition() throws IOException {
		// The image header is some bytes after the last layer definition, so the offset is higher than the current position
		try(InputStream icon = ICODecoderTest.class.getResourceAsStream("254.ico")){
			ICODecoder.read(icon);
		}
	}

}
