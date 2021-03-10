package com.example.tetris;

class Test extends Thread{
    public void run(){
        for (int i = 0; i < 5; ++i){
            System.out.print("*");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}