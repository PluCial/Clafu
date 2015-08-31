package com.clafu.dao;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SpamUserModelDaoTest extends AppEngineTestCase {

    private SpamUserModelDao dao = new SpamUserModelDao();

    @Test
    public void test() throws Exception {
        assertThat(dao, is(notNullValue()));
    }
}
