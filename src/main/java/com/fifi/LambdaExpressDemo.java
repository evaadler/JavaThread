package com.fifi;

/**
 * LambdaExpressDemo
 *
 * @author Alicia
 * @description
 * @date 2020/7/3
 */
public class LambdaExpressDemo {
    public static void main(String[] args) {
        Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("*******Hello,fifi********");
            }
        };

        foo.sayHello();

        Foo fooLambda = () -> System.out.println("Hello, lambda");
        fooLambda.sayHello();

        foo.div(10, 5);
    }


}

interface Foo{
    public void sayHello();

    default int div(int x, int y){
        System.out.println("**** div method ****");
        return x/2;
    }

    public static int mv(int x, int y){
        return x*y;
    }
}

