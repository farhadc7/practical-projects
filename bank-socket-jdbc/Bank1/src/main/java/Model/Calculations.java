package Model;

import performance.PerformanceChecker;

import java.io.IOException;

public class Calculations {
    PerformanceChecker pc=new PerformanceChecker();
    public synchronized Long withdraw(Long accountBalance, Long amount){
        ///////////start of performance checking
        Double performanceNum=Thread.currentThread().getId()*Math.random();
        try {
            pc.timeLogger(performanceNum);
        } catch (IOException e) {
            System.out.println("cannt convert to int");
        }
        if(amount<0){
            amount=amount*-1;
        }
        long res=accountBalance-amount;
        ///////////end of performance checking
        try {
            pc.timeLogger(performanceNum);
        } catch (IOException e) {
            System.out.println("cannot convert to int");
        }
        if(res < 0){
            return -1l;
        }else return res;

    }
    public synchronized Long deposite(Long accountBalance, Long amount){
        ///////////start of performance checking
        Double performanceNum=Thread.currentThread().getId()*Math.random();
        try {
            pc.timeLogger(performanceNum);
        } catch (IOException e) {
            System.out.println("cannt convert to int");
        }

        long res=accountBalance+amount;
        ///////////start of performance checking
        try {
            pc.timeLogger(performanceNum);
        } catch (IOException e) {
            System.out.println("cannt convert to int");
        }

        if(res < 0){
            return -1l;
        }else return res;
    }
    public synchronized void card2card(){

    }



}

