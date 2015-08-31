package com.clafu.controller.info;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ClafuControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/info/clafu");
        ClafuController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/info/clafu.jsp"));
    }
}
