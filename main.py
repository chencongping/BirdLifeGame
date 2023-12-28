import time
import random

birdNum = 0


class Bird:
    def __init__(self, id, name):
        self.id = id
        self.beans = 0
        self.genrateAge = 0
        self.age = 0
        self.childs = []
        self.name = name
        self.death = False

    def update(self):
        self.beans += 1
        self.genrateAge += 1
        self.age += 1

    def reset(self):
        self.genrateAge = 0
        self.beans = 0

    def genrate(self):
        id = birdNum + 1
        Bird(id, f"{self.name}.{id}")


class Island:
    def __init__(self, num_birds, num_beans):
        self.birds = [Bird(i, i) for i in range(num_birds)]
        self.beans = num_beans
        self.days = 0

    def simulate_day(self):
        self.comumerTimes()
        self.consumerBeans()
        self.genrateBird()
        self.death()
        self.show()

    def comumerTimes(self):
        self.days += 1

    def consumerBeans(self):
        random.shuffle(self.birds)
        for bird in self.birds:
            if self.beans > 0:
                bird.update()
                self.beans -= 1

    def genrateBird(self):
        for bird in [bird for bird in self.birds if bird.beans >= 20]:
            self.birds.append(bird.genrate())
            self.birds.append(bird.genrate())
            bird.reset()

    def death(self):
        self.birds = [bird for bird in self.birds if bird.beans < 20]

    def show(self):
        print(f"Day {self.days}: {self.beans} beans left, {len(self.birds)} birds alive")

    def run(self):
        while self.beans > 0 and len(self.birds) > 0:
            self.simulate_day()
            # time.sleep(1)


# 初始状态下有50只豆豆鸟和1000个豆豆
island = Island(4, 100000)
island.run()
