package com.polaris.monitoringcollector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class Controller {

    @GetMapping(value = "/test")
    public String test(){

        return "Sucsess";
    }

    @GetMapping(value = "/run")
    public String Run(){

        for(int i = 1; i <= 1000; i++){
            System.out.println("printing numbers : "+i);

        }
        return "Numbers printed";
    }

    @GetMapping(value = "/cpu")
    public  String generateHighCPUConsumption(){
        System.out.println(
                "Inside consume CPU method :   ");
        try {
            boolean condition = true;
            while (condition) {

                Runnable r = () -> {
                    System.out.println(
                            "Current Thread Name: "
                                    + Thread.currentThread().getName());
                    while (true) {
                        new Thread().start();
                        try {
                            Thread.sleep(5000);
                            System.out.println(
                                    "Current Inner Thread Name: "
                                            + Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Success";
    }
}
