package helloworld.hao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shidacaizi
 * @date 2020/4/15 19:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int id;
    private String name;
    private String pwd;
}
