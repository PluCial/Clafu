package com.clafu.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TransmitHistoryModelTest extends AppEngineTestCase {

    private TransmitHistoryModel model = new TransmitHistoryModel();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
