package com.gwy.aop;

import com.gwy.GpApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GpApplication.class)
public class AnnotationTest {
    @Autowired
    private LoggerApply loggerApply;

    @Test
    public void testAnnotationLogger() {
        try {
            loggerApply.lingLong("Blog Home");
        } catch (Exception e) {
            System.out.println("a exception be there");
        }

    }
}