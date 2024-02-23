# Scrum Board Analyzer

## Overview

The Scrum Board Analyzer is a comprehensive full-stack desktop application designed for agile teams to connect to and analyze data from the Trello API. This project enables users to visualize and assess their project's progress in real-time, enhancing decision-making and team collaboration. Developed during an intensive software engineering course, this application embodies a blend of technical proficiency and practical functionality, addressing the real-world needs of agile project management.

## Features

- **Continuous Integration**: Ensures code integrity by automatically building and testing code changes, facilitating a robust development process.
- **Automated Testing**: Incorporates a suite of tests to validate functionality and ensure reliability across updates.
- **Style Checks**: Adheres to industry-standard coding practices to maintain code quality and readability.
- **API Integration**: Seamlessly connects with the Trello API to fetch real-time data, offering a dynamic and interactive user experience.
- **Front-End & Back-End Development in Java**: A cohesive full-stack solution, with a user-friendly interface and a powerful server-side to process and display data efficiently.
- **Gradle Build Tool**: Utilizes Gradle for streamlined building, testing, and deployment across different machines, ensuring compatibility and ease of use.

## Technical Highlights

- **Java**: Developed with Java [specify version, e.g., Java 11], leveraging its robust features for cross-platform application development. This choice demonstrates a deep understanding of Java's capabilities and best practices in software engineering.
- **Gradle 8.6**: Utilizes Gradle 8.6 for efficient project management and builds automation, showcasing expertise in modern development tools and practices. This version of Gradle supports a wide range of features that enhance the project's scalability and maintainability.
- **Trello API Integration**: Demonstrates the ability to integrate with external APIs, providing dynamic data fetching and real-time project management insights.
- **Continuous Integration/Continuous Deployment (CI/CD)**: Implements CI/CD pipelines using GitHub Actions, highlighting a commitment to automation and quality in the development process.
- **Automated Testing**: Incorporates JUnit for backend testing and Selenium for front-end testing, emphasizing a thorough approach to quality assurance and reliability.


## Installation

Before you begin, ensure you have Java [specify version, e.g., Java 11] and Gradle 8.6 installed on your machine. These prerequisites are essential for running the Scrum Board Analyzer application successfully.

```bash
# Check Java version
java -version
# Output should match the Java version required, e.g., Java 11

# Check Gradle version
gradle -v
# Output should show Gradle 8.6

# Clone the repository
git clone https://github.com/AyersAuthentic/scrum-board-analyzer.git

# Navigate to the project directory
cd scrum-board-analyzer

# Build the project with Gradle
./gradle clean build

# Run the application
./gradle run
```

