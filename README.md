# 🚀 Guru99 Automation Framework

This repository contains the automation framework for testing two web applications:
- ✅ [Guru99 Telecom](https://demo.guru99.com/telecom/index.html)
- ✅ [Guru99 Payment Gateway](https://demo.guru99.com/payment-gateway/index.php)

The framework is built using **Selenium WebDriver**, **Cucumber (BDD)**, **TestNG**, **ExtentReports**, and **Page Object Model (POM)** design pattern.

---

## 🧰 Tech Stack

- Java 21  
- Selenium 4.8.3  
- Cucumber  
- TestNG  
- Maven  
- ExtentReports  
- WebDriverManager  
- Page Object Model (POM)

---

## 📁 Folder Structure

guru99-automation/
│
├── telecom/ → Guru99 Telecom Project
│ ├── features/ → Cucumber feature files
│ ├── pages/ → Page Object classes
│ ├── stepDefinitions/ → Cucumber step definitions
│ ├── runners/ → TestNG runners
│ └── test-output/ → ExtentReports output
│
├── payment-gateway/ → Guru99 Payment Gateway Project
│ ├── features/
│ ├── pages/
│ ├── stepDefinitions/
│ ├── runners/
│ └── test-output/
│
└── README.md
---

## 🛠️ How to Run the Tests

### ✅ Prerequisites

- Java 21+ installed
- Maven installed
- Chrome browser
- Internet connection

### 🔃 Steps to Execute

1. **Clone the project**
```bash
git clone https://github.com/Hemaranirajput/guru99-automation.git
cd guru99-automation
