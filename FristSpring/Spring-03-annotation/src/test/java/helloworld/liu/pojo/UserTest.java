package helloworld.liu.pojo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author shidacaizi
 * @date 2020/4/2 12:45
 */
public class UserTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        User user = (User) context.getBean("user");

        System.out.println(user.name);
    }
}
