package com.clafu.dao;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TransmitHistoryModelDaoTest extends AppEngineTestCase {

    private TransmitHistoryModelDao dao = new TransmitHistoryModelDao();

    @Test
    public void test() throws Exception {
        dao.getPostTargetList(2);
        assertThat(dao, is(notNullValue()));
    }
}
