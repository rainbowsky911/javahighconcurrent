package javahighconcurrent.ch2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 不安全的发布
 *
 * @author 51473
 */
public class Unsafepublish {

    private List<Integer> list = new ArrayList<>(3);

    private static final  List<Object> list2= Collections.singletonList(new ArrayList<>(3));

    public Unsafepublish() {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    public List getList() {
        return list;
    }

    public static void main(String[] args) {
        Unsafepublish unSafePublish = new Unsafepublish();
        List<Integer> list = unSafePublish.getList();
        System.out.println(list);
        list.add(4);
        System.out.println(list);
        System.out.println(unSafePublish.getList());
    }

}
