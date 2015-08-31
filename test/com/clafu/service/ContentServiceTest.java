package com.clafu.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ContentServiceTest extends AppEngineTestCase {

    private ContentService service = new ContentService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
