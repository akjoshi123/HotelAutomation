package org.sahaj.homeautomation.inputs;


import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.sahaj.hotelautomation.inputs.Input;
import org.sahaj.hotelautomation.models.inputs.InitialInput;
import org.sahaj.hotelautomation.models.inputs.MotionInput;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InputTest {

    private static StringBuilder builder = new StringBuilder();
    private static InputStream customStream;

    static {
        builder.append("1");
        builder.append(System.lineSeparator());

        builder.append("2");
        builder.append(System.lineSeparator());

        builder.append("abc");
        builder.append(System.lineSeparator());


        builder.append("2");
        builder.append(System.lineSeparator());

        builder.append("11");
        builder.append(System.lineSeparator());

        builder.append("22");
        builder.append(System.lineSeparator());
        try {
            customStream = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.setIn(customStream);
    }


    @Test
    public void initialInputTest() {
        InitialInput input = Input.getInitialInput();

        assertEquals(1, input.getFloor());
        assertEquals(2, input.getMainCorridorCount());
        assertEquals(2, input.getSubCorridorCount());

    }

    @Test
    public void motionInputTest() {
        MotionInput motionInput = Input.getMotionInput();


        assertEquals(11, motionInput.getFloor());
        assertEquals(22, motionInput.getSubCorridor());
    }

    @After
    public void terminate() {
        // Should kill hotel object in GC
        builder = null;
    }

}
