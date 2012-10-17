package com.concur;

import junit.framework.Assert;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.junit.Test;

public class AxiomTest {

    @Test
    public void testCharacters()
        throws Exception {
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

}
