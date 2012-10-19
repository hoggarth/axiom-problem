package com.concur;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;

import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMXMLBuilderFactory;
import org.junit.Test;

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
        String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><a>" + entity + "</a>";

        OMFactory f = OMAbstractFactory.getOMFactory();
        StringReader sr = new StringReader(xmlString);
        OMElement docElement = OMXMLBuilderFactory.createOMBuilder(f, sr).getDocumentElement();

        Writer out = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
        docElement.serialize(out);
        System.out.println();
        QName foo = docElement.getTextAsQName();
        System.out.println(foo.toString());

        // System.out.println(docElement.toString());
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
