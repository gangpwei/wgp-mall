package com.wgp.mall.mongodb;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.wgp.mall.mongodb.entity.Employee;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.junit.Assert.assertTrue;

/**
 * @author gangpeng.wgp
 * @date 2023/6/14 下午10:48
 */
@SpringBootTest
public class MongoTest extends ApplicationTest {

    @Resource
    private MongoTemplate mongoTemplate;


    @Test
    public void testInsert(){
        Employee employee = new Employee(1, "小明", 30,10000.00, new Date());

        //添加文档
        // sava:  _id存在时更新数据
        //mongoTemplate.save(employee);
        // insert： _id存在抛出异常   支持批量操作
        //mongoTemplate.insert(employee);
        //
        List<Employee> list = Arrays.asList(
            new Employee(2, "张三", 21,5000.00, new Date()),
            new Employee(3, "李四", 26,8000.00, new Date()),
            new Employee(4, "王五",22, 8000.00, new Date()),
            new Employee(5, "张龙",28, 6000.00, new Date()),
            new Employee(6, "赵虎",24, 7000.00, new Date()),
            new Employee(7, "赵六",28, 12000.00, new Date()));
        //插入多条数据
        mongoTemplate.insert(list,Employee.class);
    }

    @Test
    public void findAll() {
        List<Employee> employees = mongoTemplate.findAll(Employee.class);
        employees.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Employee employee = mongoTemplate.findById(2, Employee.class);
        System.out.println(employee);
    }

    @Test
    public void insert() {

        Employee employee = new Employee();
        employee.setId(1000);
        employee.setAge(20);
        employee.setName("鹏哥");
        employee.setBirthday(new Date());
        employee.setSalary(40000D);
        mongoTemplate.insert(employee);

        employee = mongoTemplate.findById(1000, Employee.class);
        System.out.println(employee);
    }

    @Test
    public void update() {

        Query query = new Query();
        query.addCriteria(Criteria.where("username").is("老魏"));
        Update update = new Update();
        update.set("age", 32);
        UpdateResult updateResult = null;
        updateResult = mongoTemplate.updateFirst(query, update, Employee.class);
        assertTrue(updateResult.getModifiedCount() == 1);

        Employee employee = mongoTemplate.findById(2, Employee.class);
        assertTrue(employee.getAge() == 32);

        query = new Query();
        query.addCriteria(Criteria.where("id").is(1000));
        update = new Update();
        update.set("age", 31);

        updateResult = mongoTemplate.updateFirst(query, update, Employee.class);
        assertTrue(updateResult.getModifiedCount() == 1);
    }

    @Test
    public void delete() {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(2));

        DeleteResult deleteResult = mongoTemplate.remove(query, Employee.class);
        assertTrue(deleteResult.getDeletedCount() == 1);

        Employee employee = mongoTemplate.findById(2, Employee.class);
        assertTrue(employee == null);
    }

}
