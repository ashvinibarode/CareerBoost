# 🚀 CareerBoost

> An AI-powered Career Preparation Platform that helps students and job seekers improve their resumes, compare them with job descriptions, practice interviews, and assess technical skills.

---

## 📌 Overview

CareerBoost is a Java Full Stack web application built using **Spring Boot**, **MySQL**, and **Google Gemini AI**. It provides intelligent resume analysis, ATS score evaluation, job description matching, interview preparation, and technical quizzes to help users become job-ready.

---

# ✨ Features

## 🤖 AI Resume Analyzer

- Upload Resume (PDF)
- AI Resume Analysis
- Resume Score
- ATS Score
- Strengths & Weaknesses
- Missing Skills Detection
- Keyword Analysis
- ATS Improvement Suggestions
- Project Suggestions
- Interview Tips
- Personalized Learning Roadmap

---

## 💼 Resume vs Job Description

- Upload Resume
- Paste Job Description
- AI Match Score
- Matched Skills
- Missing Skills
- Missing Keywords
- Resume Improvement Suggestions
- Keyword Recommendations

---

## 👤 User Module

- User Registration
- User Login
- User Profile
- Dashboard

---

## 📝 Quiz Module

- Technical MCQs
- Automatic Score Calculation
- Performance Analysis

---

## 🎤 AI Interview Preparation

- AI-generated Interview Questions
- Role-based Questions
- Interview Practice

---

# 🛠 Tech Stack

### Backend

- Java 17
- Spring Boot
- Spring AI
- Spring Data JPA
- REST API
- Maven

### Frontend

- HTML5
- CSS3
- JavaScript

### Database

- MySQL

### AI

- Google Gemini 2.5 Flash API

### Tools

- IntelliJ IDEA
- VS Code
- Git
- GitHub
- Postman

---

# 📂 Project Structure

```
CareerBoost
│
├── frontend
│   ├── css
│   ├── js
│   ├── login.html
│   ├── dashboard.html
│   ├── resume.html
│   ├── job-description.html
│   └── ...
│
├── src
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── config
│   └── resources
│
├── pom.xml
└── README.md
```

---

# ⚙️ Installation

### Clone Repository

```bash
git clone https://github.com/ashvinibarode/CareerBoost.git
```

Open the project in IntelliJ IDEA.

---

## Configure Database

Create a MySQL database.

```sql
CREATE DATABASE careerboost_db;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/careerboost_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.ai.google.genai.api-key=YOUR_GEMINI_API_KEY
```

---

## Run Backend

```bash
mvn spring-boot:run
```

Application will start at

```
http://localhost:8080
```

---

## Run Frontend

Open

```
frontend/login.html
```

or use **Live Server** in VS Code.

---

# 🔗 REST API

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/auth/register` | Register User |
| POST | `/auth/login` | User Login |
| POST | `/ai/resume/pdf` | AI Resume Analysis |
| POST | `/jd/analyze` | Resume vs Job Description |
| GET | `/dashboard/{userId}` | Dashboard |
| GET | `/profile/{userId}` | User Profile |
| PUT | `/profile/update` | Update Profile |
| GET | `/quiz` | Get Quiz |
| POST | `/quiz/submit` | Submit Quiz |
| POST | `/interview` | AI Interview Questions |

---

# 🚀 Future Enhancements

- JWT Authentication
- Resume Builder
- Resume Download (PDF)
- Admin Dashboard
- Email Notifications
- Docker Deployment
- Cloud Deployment
- Multi-language Support

---


# 📸 Screenshots

## Login Page

![Login Page](screenshots/login.png)

---

## Dashboard

![Dashboard](screenshots/dashboard.png)

---

## Resume Analyzer

![Resume Analyzer](screenshots/resume-analyzer.png)

---

## Job Match

![Job Match](screenshots/jd-analyzer.png)
---
## Quiz

![Quiz](screenshots/quiz.png)
---
## AI Interview

![AI Interview](screenshots/interview.png)
---
****

# 👨‍💻 Author

**Ashvini Barode**

🎓 MCA Student  
💻 Java Full Stack Developer  
🤖 Spring Boot & AI Enthusiast


---

## ⭐ Support

If you found this project useful, consider giving it a **Star ⭐** on GitHub.
