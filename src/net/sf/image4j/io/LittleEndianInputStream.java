/*
 * LittleEndianInputStream.java
 *
 * Created on 07 November 2006, 08:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.sf.image4j.io;

import java.io.EOFException;
import java.io.IOException;

/**
 * Reads little-endian data from a source <tt>InputStream</tt> by reversing byte ordering.
 * @author Ian McDonagh
 */
public class LittleEndianInputStream extends java.io.DataInputStream {
  
	private int read;
	
  /**
   * Creates a new instance of <tt>LittleEndianInputStream</tt>, which will read from the specified source.
   * @param in the source <tt>InputStream</tt>
   */
  public LittleEndianInputStream(java.io.InputStream in) {
    super(in);
  }
  
  public int getBytesRead() {
	return read;
  }
  
  @Override
  public int read() throws IOException {
	int b = super.read();
	if (b != -1) {
		read++;
	}
	return b;
  }
  
  public int readUByte() throws IOException {
	  int b = readUnsignedByte();
	  read++;
	  return b;
  }
  
  public byte readSByte() throws IOException {
	  byte b = readByte();
	  read++;
	  return b;
  }
  
  public int skip(int count, boolean strict) throws IOException {
	  int skipped = 0;
	  while (read() != -1 && skipped < count) {
		  skipped++;
	  }
	  if (skipped < count && strict) {
		  throw new EOFException("Failed to skip "+count+" bytes in input");
	  }
	  return skipped;
  }
  
  /**
   * Reads a little-endian <tt>short</tt> value
   * @throws java.io.IOException if an error occurs
   * @return <tt>short</tt> value with reversed byte order
   */
  public short readShortLE() throws IOException {
    
    int b1 = read();
    int b2 = read();
    
    if (b1 < 0 || b2 < 0) {
      throw new EOFException();
    }
    
    short ret = (short) ((b2 << 8) + (b1 << 0));
    
    return ret;
  }
  
  /**
   * Reads a little-endian <tt>int</tt> value.
   * @throws java.io.IOException if an error occurs
   * @return <tt>int</tt> value with reversed byte order
   */
  public int readIntLE() throws IOException {
    int b1 = read();
    int b2 = read();
    int b3 = read();
    int b4 = read();
    
    if (b1 < -1 || b2 < -1 || b3 < -1 || b4 < -1) {
      throw new EOFException();
    }
    
    int ret = (b4 << 24) + (b3 << 16) + (b2 << 8) + (b1 << 0);
    
    return ret;
  }
  
  /**
   * Reads a little-endian <tt>float</tt> value.
   * @throws java.io.IOException if an error occurs
   * @return <tt>float</tt> value with reversed byte order
   */
  public float readFloatLE() throws IOException {
    int i = readIntLE();
    
    float ret = Float.intBitsToFloat(i);
    
    return ret;
  }
  
  /**
   * Reads a little-endian <tt>long</tt> value.
   * @throws java.io.IOException if an error occurs
   * @return <tt>long</tt> value with reversed byte order
   */
  public long readLongLE() throws IOException {
    
    int i1 = readIntLE();
    int i2 = readIntLE();
    
    long ret = ((long)(i1) << 32) + (i2 & 0xFFFFFFFFL);
    
    return ret;
  }
  
  /**
   * Reads a little-endian <tt>double</tt> value.
   * @throws java.io.IOException if an error occurs
   * @return <tt>double</tt> value with reversed byte order
   */
  public double readDoubleLE() throws IOException {
    
    long l = readLongLE();
    
    double ret = Double.longBitsToDouble(l);
    
    return ret;
  }      
  
  /**
   * @since 0.6
   */
  public long readUnsignedInt() throws IOException {
    long i1 = readUByte();
    long i2 = readUByte();
    long i3 = readUByte();
    long i4 = readUByte();
   
    long ret = ((i1 << 24) | (i2 << 16) | (i3 << 8) | i4); 
    
    return ret;
  }
  
  /**
   * @since 0.6
   */
  public long readUnsignedIntLE() throws IOException {
    long i1 = readUByte();
    long i2 = readUByte();
    long i3 = readUByte();
    long i4 = readUByte();
    
    long ret = (i4 << 24) | (i3 << 16) | (i2 << 8) | i1;
    
    return ret;
  }
}
