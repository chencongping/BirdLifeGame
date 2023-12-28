package com.chencongping.www.lab.application;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Accessors(chain = true)
public class Bird {
  private int id;
  private String name;
  private int beans = 0;
  private int generateAge = 0;
  private int age = 0;
  private List<Bird> children = new ArrayList<>();

  public void eat() {
    beans++;
  }

  public int getBeans() {
    return beans;
  }

  public void growing() {
    generateAge++;
    age++;
  }

  public void reset() {
    generateAge = 0;
    beans = 0;
  }

  public void generation(Island island) {
    int lifeNumber = island.getLiftNumber() + 1;
    Bird bird = new Bird().setName(String.format("%s.%s", this.name, island.getLiftNumber()));
    children.add(bird);
    island.getBirds().add(bird);
    island.setLiftNumber(lifeNumber);
  }

  public int getChildrenNumber() {
    if (children.isEmpty()) {
      return 0;
    } else {
      int childrenSize = 0;
      for (Bird bird : children) {
        childrenSize++;
        childrenSize = childrenSize + bird.getChildrenNumber();
      }
      return childrenSize;
    }
  }
}
