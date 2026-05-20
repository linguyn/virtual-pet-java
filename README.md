# Virtual Pet

A text-based virtual pet simulation game in Java where you adopt and care for a pet to keep it alive as long as possible.

---

## Gameplay

After typing `Start`, you adopt a pet and choose its species and name. Your pet's hunger, thirst, and energy drain every 20 seconds in real time — if all three hit zero, your pet passes away. Interact through text commands to feed, water, play with, and put your pet to sleep. You can own multiple pets at once, but the more you have the faster you need to act.

**Commands:** `Feed`, `Water`, `Play`, `Sleep`, `Status`, and more via the in-game menu.

---

## Features

- 5 adoptable pet species — Dog, Cat, Bird, Rabbit, Hamster — each with unique responses
- Real-time stat drain using a background scheduler
- Aging system that tracks how long your pet has been alive
- Duplicate name prevention across multiple pets
- HP system — stats hitting zero damages your pet's health

---

## Project Structure

```
VirtualPet/
├── src/
│   ├── ProjectOneTester.java   # Entry point and game loop
│   ├── Pet.java                # Abstract base class with all core logic
│   ├── Dog.java                # Dog species
│   ├── Cat.java                # Cat species
│   ├── Bird.java               # Bird species
│   ├── Rabbit.java             # Rabbit species
│   └── Hamster.java            # Hamster species
└── bin/                        # Compiled .class files
```

---

## How to Run

**Requirements:** Java 8 or higher

**Compile:**
```bash
javac -cp src -d bin src/*.java
```

**Run:**
```bash
java -cp bin ProjectOneTester
```

> On Windows, replace `:` with `;` in the classpath.

---

## Built With

- **Java OOP** — abstract class, inheritance, method overriding
- **java.util.concurrent** — `ScheduledExecutorService` for real-time stat drain and aging
- **Scanner** — text-based user input

---
