# image4j

#Overview

The image4j library allows you to read and write certain image formats in 100% pure Java.

Currently the following formats are supported:

BMP (Microsoft bitmap format - uncompressed; 1, 4, 8, 24 and 32 bit)
ICO (Microsoft icon format - 1, 4, 8, 24 and 32 bit [XP uncompressed, Vista compressed])

#Purpose

This project aims to provide:

<ul>
<li>an open source library for handling various image formats in Java</li>
<li>with a commercial-friendly license</li>
<li>using only Java code, ie. without using any JNI hacks</li>
<li>with no dependencies on third-party libraries (where possible)</li>
</ul>

#License

The image4j library is licensed under the GNU LGPL so you are free to use it in your Free Software and Open Source projects, as well as commercial projects, under the terms of the LGPL.

#History

This project began after I spent hours searching for a library that would meet the above criteria and found that there is probably no such thing - or at least, not any more. The only support I could find for the ICO format was read-only, so doing cool stuff - like generating favicons on the fly - using only Java code was not possible.

#Planned Features

The following features are planned for development:

A WebStart demo
Support for RLE (Run Length Encoded) bitmaps
ImageIO provider for the various supported formats, where no provider currently exists (eg. ICO format)
Ant tasks for reading/writing/converting the various supported image formats
A javax.swing.Icon implementation for the ICO format that selects the best matching image for the current graphics environment
If you know of a feature you'd like to see in this library, drop me a mail <ianmdev at users.sourceforge.net> and we'll talk :)

#QuickStart

It is possible to decode/encode BMP and ICO files with just one line of code!

The image4j library consists of only Java code and has no dependencies on third-party libraries, so you just add it to the classpath and you're good to go.

Although the code is compatible with Java 1.5.0 or later, it should be relatively simple to port the code to an older version, eg. 1.4.2. If you would like support for an earlier version of Java, please write to me at <ianmdev at sourceforge.net>.

BMP

Decode


```java
BufferedImage image = BMPDecoder.read(new File("input.bmp"));
```

Encode

```java
BMPEncoder.write(image, new File("output.bmp"));
```

ICO

Decode

```java
List<BufferedImage> image = ICODecoder.read(new File("input.ico"));
```

Encode

```java
ICOEncoder.write(images, new File("output.ico"));
```

#Documentation

Browse the API Javadocs online.

#Download

Download the latest version at SourceForge.net.

#Credits

The File Formats page at DaubNET for information on various image formats
The Duke project at dev.java.net for the open source images of Duke - the Java mascot [used on this page and in the examples]
SourceForge.net for hosting this project
The NetBeans project for the best open source Java IDE
The GIMP, which I use for editing images
Disclaimer

To my knowledge, there are no patents on either the BMP or ICO formats. If you believe that this library or this page violates any patents or copyrights, please write to me at <ianmdev at users.sourceforge.net>.
