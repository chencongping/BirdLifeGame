package com.chencongping.www.lab.application;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Data
public class Island {
  private ArrayList<Bird> initBirds = new ArrayList<>();
  private ArrayList<Bird> birds = new ArrayList<>();
  private int beans;
  private int days;
  private int liftNumber;

  public Island(int numBirds, int numBeans) {
    for (int i = 0; i < numBirds; i++) {
      initBirds.add(new Bird());
    }
    birds.addAll(initBirds);
    beans = numBeans;
  }

  public Island(int beansNumber, Bird... bird) {
    birds.addAll(Arrays.asList(bird));
    initBirds.addAll(birds);
    beans = beansNumber;
  }

  public void simulateDay() {
    days++;
    growing();
    killBird();
    generateBird();
  }

  private void generateBird() {
    for (Bird bird : new ArrayList<>(birds)) {
      if (bird.getBeans() >= 20 && bird.getGenerateAge() >= 20) {
        bird.generation(this);
        bird.generation(this);
        bird.reset();
      }
    }
  }

  public void killBird() {
    birds.removeIf(bird -> bird.getGenerateAge() >= 20 && bird.getBeans() < 20);
  }

  public void growing() {
    Collections.shuffle(birds);
    for (Bird bird : birds) {
      if (beans > 0) {
        bird.eat();
        bird.growing();
        beans--;
      }
    }
  }

  public void simulate() throws InterruptedException {
    while (beans > 0 && birds.size() > 0) {
      //            System.out.println(
      //                "Day " + days + ": " + beans + " beans left, " + birds.size() + " birds
      // alive");
      System.out.println(String.format("======The days is %s =====", days));
      for (Bird bird : initBirds) {
        System.out.println(
            String.format(
                "Bird name : %s, children size %s", bird.getName(), bird.getChildrenNumber()));
      }
      simulateDay();
      Thread.sleep(50);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    // 初始状态下有50只豆豆鸟和1000个豆豆
    //    Bird bird1 = new Bird().setId(1).setName("zhangsan");
    //    Bird bird2 = new Bird().setId(2).setName("lisi");
    //    Bird bird3 = new Bird().setId(3).setName("wangwu");
    //    Bird bird4 = new Bird().setId(4).setName("mazi");
    //    Island island = new Island(1000, bird1, bird2, bird3, bird4);
    //    island.simulate();

    int count = 1;
    while(true){
      System.out.print(String.format("\033[%dA",count)); // Move up
      System.out.print("\033[2K"); // Erase line content
      System.out.println(count++);
    }
  }
}
