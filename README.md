# Sudoku (Client-Server Java Implementation)

A Sudoku game implemented in Java with a client-server architecture.  
The project demonstrates networking concepts, game state synchronization, and modular software design.

---

##  Overview

This project is a Sudoku application where the game logic is separated between a client and a server.  
The server is responsible for managing the game state, while the client handles user interaction and input.

The goal of this project was to explore:
- Network communication using Java sockets
- Distributed system design
- Game state management
- Clean separation of concerns between components

---

## Features

- Sudoku gameplay with standard rules
- Client-server communication via sockets
- Centralized game state management on the server
- Real-time updates between client and server
- Input validation for moves
- Modular structure (client, server, shared logic)

---

##  Architecture

- **Server**
  - Hosts the Sudoku game logic
  - Validates moves
  - Maintains the authoritative game state

- **Client**
  - Provides user interface
  - Sends player moves to the server
  - Receives updates and renders game state

- **Communication**
  - Uses Java sockets for message exchange
  - Simple request-response protocol between client and server

---
