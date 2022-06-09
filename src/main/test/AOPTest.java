import com.fic091.aop.Calculator;
import com.fic091.aop.CalculatorProxy;
import com.fic091.aop.MyMathCalculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

    @Test
    public void test(){
        Calculator calculator=new MyMathCalculator();
        Calculator proxy= CalculatorProxy.getProxy(calculator);
        System.out.println(proxy.add(1,2));
    }

    @Test
    public void test2(){
        ApplicationContext ioc=new ClassPathXmlApplicationContext("AOP-config.xml");
        Calculator bean = ioc.getBean(Calculator.class);
        //bean.add(2,1);
        System.out.println(bean.add(2,1));
    }
}
