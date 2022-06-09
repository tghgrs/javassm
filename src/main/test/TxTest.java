import com.fic091.bean.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//通过JdbcTemplate进行业务
public class TxTest {
    ApplicationContext ioc=new ClassPathXmlApplicationContext("JDBCConfig.xml");
    JdbcTemplate jdbcTemplate=ioc.getBean(JdbcTemplate.class);
    NamedParameterJdbcTemplate namedParameterJdbcTemplate=ioc.getBean(NamedParameterJdbcTemplate.class);
    @Test
    public void test() throws SQLException {
        DataSource bean=ioc.getBean(DataSource.class);
        Connection connection = bean.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    public void test01(){
        System.out.println(jdbcTemplate);
    }

    @Test
    public void test02(){
        String sql="UPDATE employee SET salary=? WHERE emp_id=?";
        int update = jdbcTemplate.update(sql, 1300, 5);
        System.out.println("更新员工:"+update);
    }

    @Test
    public void test03(){
        String sql="INSERT INTO employee(emp_name,salary) VALUES(?,?)";
        //List<Object[]>
        //List的长度就是sql语句要执行的次数
        //Object[]是每次执行要用的参数
        List<Object[]> batchArgs=new ArrayList<>();
        batchArgs.add(new Object[]{"张三",1998.98});
        batchArgs.add(new Object[]{"李四",2998.98});
        batchArgs.add(new Object[]{"王五",3998.98});
        batchArgs.add(new Object[]{"赵六",4998.98});
        int[] is = jdbcTemplate.batchUpdate(sql, batchArgs);
        for (int i:is
             ) {
            System.out.println(i);
        }
    }

    //查询封装为Object
    @Test
    public void test04(){
        String sql="SELECT emp_id empId,emp_name empName,salary FROM employee WHERE emp_id=?";
        //RowMapper:每一行记录和javaBean的属性如何映射
        Employee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), 5);
        System.out.println(employee);
    }

    //查询封装为List
    @Test
    public void test05(){
        String sql="SELECT emp_id empId,emp_name empName,salary FROM employee WHERE salary>?";
        List<Employee> employees=jdbcTemplate.query(sql,new BeanPropertyRowMapper<Employee>(Employee.class),4000);
        for (Employee e:employees
             ) {
            System.out.println(e);
        }
    }

    //查询最大工资
    @Test
    public void test06(){
        String sql="SELECT MAX(salary) FROM employee";
        Double maxSalary = jdbcTemplate.queryForObject(sql, Double.class);
        System.out.println(maxSalary);
    }

    //使用具有具名参数的SQL语句插入一条员工记录,并以Map形式传入参数值
    //具名参数:参数有名字,不用问号做占位符
    @Test
    public void test07(){
        String sql="INSERT INTO employee(emp_name,salary) VALUES(:empName,:salary)";
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("empName","钱七");
        paramMap.put("salary","9887.98");
        int update = namedParameterJdbcTemplate.update(sql, paramMap);
        System.out.println(update);
    }

    //重复实验7,以SqlParameterSource形式传入参数值
    @Test
    public void test08(){
        String sql="INSERT INTO employee(emp_name,salary) VALUES(:empName,:salary)";
        Employee employee = new Employee();
        employee.setEmpName("哈哈");
        employee.setSalary(998.98);
        int update = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(employee));
        System.out.println(update);
    }
}
