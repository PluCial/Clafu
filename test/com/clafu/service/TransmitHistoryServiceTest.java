package com.clafu.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TransmitHistoryServiceTest extends AppEngineTestCase {

    private TransmitHistoryService service = new TransmitHistoryService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
