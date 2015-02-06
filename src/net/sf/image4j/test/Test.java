/*
 * Test.java
 *
 * Created on January 19, 2007, 11:15 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.sf.image4j.test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 *
 * @author Ian McDonagh
 */
public class Test {

	public static void main(String[] args) {

		// input and output file names

		if (args.length < 2) {
			System.out.println("Usage:\n\tTest <inputfile> <outputfile>");
			System.exit(1);
		}
		
		String strInFile = args[0];
		String strOutFile = args[1];

		java.io.InputStream in = null;
		try {
			
			java.util.List<java.awt.image.BufferedImage> images;

			/***** decode ICO and save images as BMP and PNG ****/

			if (strInFile.startsWith("http:")) {
				in = new URL(strInFile).openStream();
			} else {
				in = new FileInputStream(strInFile);
			}
			
			if (!strInFile.endsWith(".ico")) {

				images = new ArrayList<java.awt.image.BufferedImage>(1);
				images.add(ImageIO.read(in));
				
				System.out.println("Read image "+strInFile+"...OK");
				
			} else {

				System.out.println("Decoding ICO file '" + strInFile + "'.");

				// load and decode ICO file

				images = net.sf.image4j.codec.ico.ICODecoder.read(in);
				System.out.println("ICO decoding...OK");

				// display summary of decoded images

				System.out.println("  image count = " + images.size());
				System.out.println("  image summary:");
				for (int i = 0; i < images.size(); i++) {
					java.awt.image.BufferedImage img = images.get(i);
					int bpp = img.getColorModel().getPixelSize();
					int width = img.getWidth();
					int height = img.getHeight();
					System.out.println("    # " + i + ": size=" + width + "x"
							+ height + "; colour depth=" + bpp + " bpp");
				}

				// save images as separate BMP and PNG files

				System.out.println("  saving separate images:");

				String format = "png";

				for (int j = 0; j < images.size(); j++) {
					java.awt.image.BufferedImage img = images.get(j);
					String name = strOutFile + "-" + j;
					java.io.File bmpFile = new java.io.File(name + ".bmp");
					java.io.File pngFile = new java.io.File(name + ".png");

					// write BMP
					System.out.println("    writing '" + name + ".bmp'");
					net.sf.image4j.codec.bmp.BMPEncoder.write(img, bmpFile);

					// write PNG
					System.out.println("    writing '" + name + ".png'");
					javax.imageio.ImageIO.write(img, format, pngFile);
				}

				System.out.println("BMP encoding...OK");

				/***** reload BMP images *****/

				System.out.println("  reloading BMP files:");

				java.util.List<java.awt.image.BufferedImage> images2 = new java.util.ArrayList<java.awt.image.BufferedImage>(
						images.size());

				for (int k = 0; k < images.size(); k++) {
					String name = strOutFile + "-" + k + ".bmp";
					java.io.File file = new java.io.File(name);

					// read BMP
					System.out.println("    reading '" + name + "'");
					java.awt.image.BufferedImage image = net.sf.image4j.codec.bmp.BMPDecoder
							.read(file);
					images2.add(image);
				}

				System.out.println("BMP decoding...OK");

			}

			/***** encode images and save as ICO *****/

			System.out.println("Encoding ICO file '" + strOutFile + "'.");

			java.io.File outFile = new java.io.File(strOutFile);

			net.sf.image4j.codec.ico.ICOEncoder.write(images, outFile);

			System.out.println("ICO encoding...OK");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ex) { }
		}

	}

	private static void usage() {

	}

}
