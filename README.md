# ChatServer 💬 - Java TCP Chat Application

## Overview
This is a simple multi-client chat server built in Java using TCP Sockets and Threads. Clients can connect and communicate in real-time through the terminal. This project demonstrates basic networking, multithreading, and I/O handling in Java.

## Features
- Multi-client support (multiple users can chat simultaneously)
- Username selection upon connection
- Real-time message broadcast to all clients (except sender)
- Basic text-based communication via terminal (using `nc` or other terminal-based clients)

## Technologies Used
- **Java** (JDK 8+)
- **TCP/IP Sockets**
- **Multithreading** (Java `Thread` and `Runnable`)
- **BufferedReader** and **PrintWriter** for communication

## Setup Instructions

### Prerequisites
- Java 8 or higher
- Terminal or Command Prompt

### Steps to Run:
1. **Clone the repository** to your local machine:
   ```bash
   git clone https://github.com/oliveirammluis/chatServer.git
   cd chatServer
   
2. **Compile the Java files**:
   ```bash
   javac -d out src/io/codeforall/kernelfc/*.java
   
3. **Run the server**:
   ```bash
   java -cp out io.codeforall.kernelfc.Server

4. **Connect via terminal using nc (netcat) on the same machine**:
   ```bash
   nc localhost 8080

### Example Output:
When the server is running, you will see:
   Server started on port 8080

### Clients will be prompted with:
   Enter your username:


---

## Commands
- **Enter a message**: Chat with all other connected clients.
- **exit**: Disconnect from the chat server.

---

## Future Improvements
- Add input validation for usernames.
- Implement private messaging between users.
- Allow command-based actions (e.g., `/help` for a list of commands).
