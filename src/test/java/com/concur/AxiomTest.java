package com.concur;

import junit.framework.Assert;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.junit.Test;

import java.io.ByteArrayOutputStream; 
import java.io.OutputStreamWriter; 
import java.nio.charset.Charset; 

public class AxiomTest {

    @Test
    public void testCharacters()
        throws Exception {

        System.out.println("Default Charset=" + Charset.defaultCharset()); 
        System.out.println("file.encoding=" + System.getProperty("file.encoding")); 
        System.out.println("Default Charset in Use=" + getDefaultCharSet()); 

        // Arrange
        String entity = "&#xFA;";
        String expected = "ú";
        String xmlString = "<a>" + entity + "</a>";
        OMElement docElement = AXIOMUtil.stringToOM(xmlString);

        // Act
        String actual = docElement.getText();

        // Assert
        Assert.assertEquals(expected, actual);
    }

    private static String getDefaultCharSet() { 
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream()); 
        String enc = writer.getEncoding(); 
        return enc; 
    } 

}
