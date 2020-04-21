package helloworld.liu.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shidacaizi
 * @date 2020/4/2 11:34
 */
public class PersonTest {

    @Test
    public void testToString() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Person person = context.getBean("person", Person.class);

        person.getDog().shout();
        person.getCat().shout();
    }

}
