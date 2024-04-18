# SnakeGame

SnakeGame is a simple Java application that implements the classic Snake game. It allows users to control a snake to eat apples and grow longer while avoiding collisions with walls and its own body.

## Purpose

The purpose of SnakeGame is to provide a fun and interactive gaming experience for users. It aims to recreate the nostalgia of the classic Snake game while incorporating modern features such as customizable usernames, music settings, and a leaderboard system.

## Usage

To run SnakeGame, follow these steps:

1. Ensure you have Java installed on your system.
2. Compile all Java files included in the project.
3. Run the `Main.java` file to start the game.

Once the game starts, you can control the snake using the arrow keys or the W, A, S, D keys. Press the Space key to restart the game when it's over. You can also access the pause menu by pressing the Esc key during gameplay.

In the pause menu, you can choose to continue the game, restart, view the leaderboard, adjust settings such as music and volume, or exit the game.

## Architecture

SnakeGame consists of several Java classes organized into different packages:

- `GameElements`: Contains classes related to the game's graphical elements and logic.
  - `GameFrame.java`: Main frame of the game.
  - `GamePanel.java`: Panel containing the game grid and snake logic.
  - `GameOverPanel.java`: Panel displayed when the game is over.
- `PausePanel`: Contains classes related to the pause menu and settings.
  - `PausePanel.java`: Panel displayed when the game is paused, allowing users to interact with various options.
  - `PauseButton.java`: Customized button class for the pause menu buttons.
- `Music`: Contains classes related to background music handling.
  - `Music.java`: Manages background music playback and volume control.
- `User`: Contains classes related to user settings and data management.
  - `User.java`: Manages user settings such as username and serialization.
- `ScoreSys`: Contains classes related to the leaderboard system.
  - `ScoreSystem.java`: Manages the leaderboard, including saving and loading scores.
