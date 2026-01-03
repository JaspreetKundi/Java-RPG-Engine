# Java 2D RPG Engine (Custom Built)

A top-down action RPG built entirely from scratch using **Core Java**, **Swing**, and **AWT**.

**I engineered this project during Grade 11** to challenge myself and deepen my understanding of computer science fundamentals. While I could have used a pre-made engine like Unity, I chose to build the rendering pipeline, physics system, and game loop manually. This approach allowed me to master **Object-Oriented Programming (OOP)** and **algorithm design** at a young age, laying a strong foundation for my software engineering skills.

![Game Screenshot](assets/game_pic.png)

## 🎓 Core Concepts Mastered

Building this engine as a high school student forced me to solve complex problems without libraries. This project helped me master:

* **Object-Oriented Design (OOP):** I learned how to structure scalable systems using **Inheritance** and **Polymorphism**. For example, I created a flexible entity system where `Boss` and `FriendlyNPC` classes inherit behavior from a base `NPC` class, allowing the game loop to update all entities uniformly.
* **Algorithmic Thinking:** I developed a predictive **Axis-Aligned Bounding Box (AABB)** collision algorithm from scratch. Instead of relying on a physics engine, I wrote the logic to calculate future coordinates against a tile map to resolve collisions before rendering.
* **Concurrency & Threading:** I mastered the concept of the "Game Loop" by implementing the `Runnable` interface and managing thread lifecycles to ensure a consistent 60 FPS update cycle independent of hardware speed.

## 🛠️ Technical Implementation

* **Language:** Java (Core)
* **Graphics:** Java AWT (`Graphics2D`, `BufferedImage`) for manual sprite rendering.
* **Input Handling:** `KeyListener` integration for responsive, event-driven player control.
* **State Management:** Implemented frame-perfect counters for invincibility frames (i-frames) and attack cooldowns using the modulo operator.

## 🎮 Game Features

* **Combat System:** Real-time combat with hit detection and health management.
* **NPC Interaction:** Dialog system for friendly NPCs (e.g., the "Old Man" quest giver).
* **Dynamic AI:** Bosses utilize random number generation and state updates to patrol areas and change direction.
* **World Building:** A tile-based map system that supports collision layers and scrolling camera logic.

## 🚀 How to Run

### Prerequisites
* Java Development Kit (JDK) 8 or higher.

### Installation
1.  Clone the repository:
    ```bash
    git clone [https://github.com/YOUR-USERNAME/YOUR-REPO-NAME.git](https://github.com/YOUR-USERNAME/YOUR-REPO-NAME.git)
    ```
2.  Compile the source code:
    ```bash
    javac *.java
    ```
3.  Run the game:
    ```bash
    java Main
    ```

## 📂 Project Structure

* `GamePanel.java`: The core engine, handling the game loop, thread management, and inputs.
* `CollisionCheck.java`: The physics engine handling tile and entity intersection.
* `Boss.java` / `FriendlyNPC.java`: Entity logic and AI behavior.

---
*Created by Jaspreet Kundi | Grade 11 Computer Science Project*