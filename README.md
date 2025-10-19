# Habit Tracker

A **Java-based habit tracking application** designed to help users build and maintain  habits through daily tracking, visualization, and data persistence.  
Developed as part of a Software dependability project, the application also integrates **CI/CD pipelines**, **SonarCloud static analysis**, **Docker**, and **automated testing** for robust software quality.

---

## Features

- Create, update, delete, and view habits  
- Track progress over time (daily/weekly habit completion)  
- Data persistence between sessions  
- Modular, extensible code architecture  
- Automated testing with Randoop and JUnit  
- Integrated CI/CD pipeline with GitHub Actions  
- Code quality and security analysis via **SonarCloud**  
- Dockerized for easy deployment

---

## Technologies Used

| Category | Technology |
|-----------|-------------|
| Language | Java 17 |
| Build Tool | Maven |
| Static Analysis | SonarCloud |
| Testing | JUnit, Randoop |
| Mutation Testing | PIT (Planned) |
| Security Analysis | SpotBugs / OWASP FindSecBugs |
| Containerization | Docker |
| CI/CD | GitHub Actions |
| Performance Testing | JMH |

---

## Project Structure

SWD-HabitTracker/
├── src/
│ ├── main/java/… # Core habit tracking logic
│ └── test/java/… # Unit and integration tests
├── randoop-tests/ # Automatically generated tests
├── .mvn/ # Maven wrapper files
├── Dockerfile # Container setup
├── pom.xml # Maven project configuration
├── spotbugs-exclude.xml # Sonar/FindSecBugs configuration
└── README.md

yaml
Copy code

---

##  Setup & Usage

###  Prerequisites
- Java 17+
- Maven 3.8+
- (Optional) Docker

###  Run Locally

git clone https://github.com/AEEltayeb/SWD-HabitTracker.git
cd SWD-HabitTracker
./mvnw clean install
java -jar target/HabitTracker-1.0.jar

 Run via Docker

docker build -t habit-tracker .
docker run -it habit-tracker

 Testing
Run unit and Randoop tests:

./mvnw test
Planned integrations:

JaCoCo for coverage

PIT for mutation testing

JMH for performance benchmarks

 Quality & Security
This project is continuously analyzed by SonarCloud for:

-Code smells

-Bugs

-Security vulnerabilities

-Test coverage


License
This project is licensed under the MIT License.
See LICENSE for details.

Author
A.E. Eltayeb
GitHub: @AEEltayeb
