package com.hrc.staticproxy;

/**
 * 计算器实现类的代理
 */
public class CalculatorProxy implements Calculator{
    //自己拥有一个目标类
    private CalculatorImp calculator;
    //构造器传入你要代理的实例对象


    public CalculatorProxy(CalculatorImp calculator) {
        this.calculator = calculator;
    }

    @Override
    public int add(int a, int b) {
        System.out.println("加数："+a+"+"+b);
        int sum = calculator.add(a, b);
        System.out.println("计算结果："+sum);
        return sum;
    }
}
