package javahighconcurrent.ch6;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Auther: zdw
 * @Date: 2021/06/07/11:08
 * @Description:

 */
public class LambdaTest {
    static int []arr={1,2,3,4};

    /**
     * 测试不可变对象
     * 第14行对每个数组成员进行了+1,但是操作完成后进行打印arr数组所有成员值
     *  你还是会发现数组成员没有发生变化。
     */
    @org.junit.jupiter.api.Test
    public  void test1(){
        Arrays.stream(arr)
                .map(x->x+1)
                .forEach(System.out::println);
        System.out.println();
        Arrays.stream(arr).forEach(System.out::println);
    }




    
    @org.junit.jupiter.api.Test
    public void test2(){
        IntStream.of(1, 2, 3, 4,5)
               .filter(e -> e > 2)
               .peek(e -> System.out.println("Filtered value: " + e))
               .map(e -> e * e)
               .peek(e -> System.out.println("Mapped value: " + e))
               .sum();
    }


    @org.junit.jupiter.api.Test
    public void test3(){
        int num=2;  //fianl int num
        Function<Integer,Integer> function =  (from) -> from * num;
        /**
         * java8会自动的在lanbda表达式中使用的变量视为final
         * 如果此处num++，会报一个编译错误。Variable used in lambda expression should be final or effectively final
         */
        System.out.println(function.apply(3));

    }

    @org.junit.jupiter.api.Test
    public void test4(){
       int num=2;
        int finalNum = num;
        Function<Integer,Integer> function =  (from) -> from * finalNum;
      num++;

        System.out.println(function.apply(3));

    }

    /**
     * 方法引用,通过类名和方法名来定位一个静态方法或者实例方法
     */
    @org.junit.jupiter.api.Test
    public void test5(){
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User(i,"jack"+Integer.toString(i)));
        }
        users.stream()
                .map(User::getName)
                .forEach(System.out::println);

    }


    @Data
    @AllArgsConstructor
    public class User{
        private Integer id;
        private  String name;
    }

    /**
     * 统计集合内所有学生平均分
     */
    @org.junit.jupiter.api.Test
    public void test(){
        long count = IntStream.range(1, 100)
                .filter(x -> x % 2 == 0)
                .count();
        System.out.println(count);

        ArrayList<Student> list = new ArrayList<>();
        Student s1 =new Student().setId(1).setName("jack").setScore(77);
        Student s2 =new Student().setId(2).setName("kobe").setScore(67.5);
        Student s3 =new Student().setId(3).setName("tony").setScore(100);
        list.add(s1);
        list.add(s2);
        list.add(s3);

        double ave = list.stream().mapToDouble(s -> s.score).average().getAsDouble();
        double ave2 = list.stream().mapToDouble(Student::getScore).average().getAsDouble();

    }



    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public class Student{
        private Integer id;
        private  String name;
        private double score;
    }

}
