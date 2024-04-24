# Space-Invaders-Game
A Java-based space invaders game, Fully functional and showcasing specific use of Object-orientated techniques and patterns, such as Factory, Chain of Responsibility, Singleton, Open closed principle, and Single responsibility.
<p align="center">
<img width="800" alt="Screenshot 2024-01-26 at 16 29 47" src="https://github.com/ebin-sabu/Space-Invaders-Game/assets/49438210/817330c0-cd52-4785-877b-51232ead767a">
<img width="800" alt="Screenshot 2024-01-26 at 16 15 19" src="https://github.com/ebin-sabu/Space-Invaders-Game/assets/49438210/b87d57ee-ed40-416d-90d8-640a3d2fd4c9">
</p>

In this project, I have applied several Object-Oriented (OO) design patterns and principles to promote code reusability, extensibility, and maintainability. The following techniques have been implemented:

- Factory Pattern: The GameObjectFactory class is responsible for creating various game objects, such as Invaders, Players, and Bullets. This pattern centralizes object creation and allows for the flexible introduction of new object types without modifying existing code.

- Chain of Responsibility Pattern: The CollisionHandler class hierarchy handles collision detection and resolution. Each CollisionHandler subclass in the chain determines whether to process a collision or pass it along to the next handler in the chain. This design pattern promotes decoupling and enables easy extension of collision handling capabilities.

- Singleton Pattern: Two critical components, GameController and SoundManager, are implemented as singletons. The GameController manages the game's flow and components, ensuring a single instance. Similarly, the SoundManager handles game audio, guaranteeing a single instance responsible for sound management.

- Open-Closed Principle: The codebase strives to follow the Open-Closed Principle, allowing classes to be extended without modifying their existing code. For example, new collision handlers can be added to the CollisionHandler hierarchy without altering the existing collision handling logic, enhancing code maintainability.

- Single Responsibility Principle: Each class in the project adheres to the Single Responsibility Principle, ensuring that it has a well-defined and specific responsibility. For instance, the Player class manages player-related functionality, the BulletInvaderCollisionHandler class handles collisions between bullets and invaders, and the SoundManager class is solely responsible for managing game audio.


## To run game:

First, `Compile` code:

   ```
   javac *.java
   ```
Then, to `launch` the game:

   ```
   java main.java
   ```

