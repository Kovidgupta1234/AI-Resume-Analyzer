# AI Resume Analyzer

An attractive, fast, and fully functional multi-page web application to simulate AI-powered resume screening. It scores resumes, checks for missing industry keywords, and provides actionable feedback.

## Features
- **Frontend**: React + Vite + React Router (Multi-page setup)
- **Backend**: Native Java HTTP Server (No external heavy framework required)
- **Design**: Premium UI with an intuitive dark/light mode switcher, micro-animations, and drag-and-drop support.
- **Instant Analysis**: Simulates ATS parsing instantly.

## Project Structure
- `/frontend`: contains the complete React Vite source code.
- `/backend`: contains the `Backend.java` API server.

## Getting Started

### 1. Run the Backend (Java)
Open a terminal in the `backend` folder and run:
```bash
javac Backend.java
java Backend
```
The backend server will start on `http://localhost:8080`.

### 2. Run the Frontend (React)
Open another terminal in the `frontend` folder and run:
```bash
npm install
npm run dev
```
The React application will start on `http://localhost:5173`. Open this URL in your web browser.

## Built For
This project is built for B.Tech Computer Engineering implementations as a high-quality demonstration of full-stack integration and responsive UI/UX principles.
