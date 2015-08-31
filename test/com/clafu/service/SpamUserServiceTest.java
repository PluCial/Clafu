package com.clafu.service;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SpamUserServiceTest extends AppEngineTestCase {

    private SpamUserService service = new SpamUserService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
