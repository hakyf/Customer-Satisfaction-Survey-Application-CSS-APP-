# Information for the Final Project Repository

## **`Kelompok 02`**

- **Riki Setiyawan**
- **Habibullah Dzaky Musthafa**
- **Yoga Sadewa**

## **`Project Priority`**

- Modul Section, Question, Parameter
- Modul Survey
- Modul Send Form Survey
- Modul Answer
- Modul Result
- Modul Client
- Modul ADD2, Employee
- Modul History, Status

## **`Draft Design & Documentation`**

- BPMN : [Link](https://drive.google.com/file/d/1FOQ5H6A1Q47xzhpKvuQHsagoDPihBm-8/view?usp=sharing)
- Use Case Diagram : [Link](https://drive.google.com/file/d/10AuxibxFDquCuNYFV5Ty2f1Mw1vJyIjr/view?usp=sharing)
- Entity Relational Diagram : [Link](https://drive.google.com/file/d/1X94O6mTQVWFhbWXJA1D2281dAGN6U6ht/view?usp=sharing)
- Data Dummy : [Link](https://docs.google.com/spreadsheets/d/10gDpG6SatiQW8xmxUQlRNW3e7XbkgA5qFfhq4r42nl4/edit?usp=sharing)
- Postman : [Link](https://documenter.getpostman.com/view/27540842/2s93zCZLfV)
- User Manual Book : [Link](https://drive.google.com/file/d/1sD9yi-bGX8RXohl2X7eOFxQT6rlVlRZa/view?usp=sharing)
- Persentation : [Link](https://drive.google.com/file/d/1kZy92d3-NEvAaEp1CA1s8wGh0soMX-m5/view?usp=sharing)

## **`Project Task`**

1. **Riki Setiyawan**

- 28/06
  - Konfigurasi JPA pada serverapp
- 02/07
  - Initial clientapp
  - Add service, controller, restcontroller, js, html employee
  - Fix js, html question
- 03/07
  - Add service, controller, restcontroller, js, html section
- 04/07
  - Add service, controller, restcontroller, js, html user
  - Add service, controller, restcontroller, js, html role
- 05/07
  - Fix UserController
  - Add service, controller, restcontroller, js, html survey
  - Add service, controller, restcontroller, js, html answer
  - Add service, controller, restcontroller, js, html client
- 06/07
  - Update Navbar
  - Update Login Page
  - Custom Email Template
  - Add service, controller, restcontroller, js, html result
- 07/07
  - Add Template Email
- 10/07
  - Update Survey Reppository
- 11/07
  - Edit Logo
  - Update Login Page
- 12/07
  - Update index Survey
  - Update Sidebar
  - Update Dashboard
  - Update Form Survey
- 13/07
  - Membuat Dokumentasi / User Manual Book
  - Membuat Persentasi
  - Update SurveyController
  - Update Form Survey
- 18/07
  - Update logo error & success survey
  - Add loading overlay
- 20/07
  - Add logo expired survey

2. **Habibullah Dzaky Musthafa**

- 28/06
  - Add all models
- 02/07
  - Fix controller, service, & repository serverapp
  - Add model, service, controller, restcontroller clientapp
  - Add security, rest template interceptor, layout dialect
  - Add controller & service login
  - Add html, js question & login
- 03/07
  - Fix bug service serverapp
  - Fix controller & model question
  - Fix html, js & cs
- 04/07
  - Fix bug service clientapp
  - Fix html, css, js clientapp
  - Fix controller & model question clientapp
- 05/07
  - Revamp template
  - Fix js and layout
- 06/07
  - Add send survey in controller
  - Edit create html, js
  - Edit status and result clientapp
  - Fix layout
- 07/07
  - Fix survey clientapp
  - Add service & controller result serverapp
  - Add history service & controller serverapp
  - Fix client clientapp
- 08/07
  - Add survey form page, and send email
  - Hide code all page
  - Fix survey serverapp
  - Add send email surver form
- 09/07
  - Edit email template
- 10/07
  - Edit survey form
- 11/07
  - Edit survey form
  - Fix survey form
  - Fix All conflict
- 12/07
  - Fix survey form
  - Add logic save answer
  - Edit section and question js
- 13/07
  - Fix answer
  - Change data type uuid to string code, and fix method findByCode
- 16/07
  - Add js survey form, logic save answer, all about service and controller survey
  - Add logic result score and mean
- 17/07
  - Add detail result & status reviewed
  - Add auth util, change result

3. **Yoga Sadewa**

- 28/06
  - Add data dummy to database
- 30/06
  - Add controller, service & repository for Survey needs (Section,Question,Answer,Result,Client) in serverapp
- 01/07
  - Add controller, service & repository for Security needs (User,Role,Privilege,etc) in serverapp
  - Create a postman workspace and collection, and perform json testing to test HTTP request Method in Section,Question,Answer
- 02/07
  - Configure Postman by creating a workspace for the team, which will allow the team to work together as editor in a single workspace.
  - Create HTTP Method request and perfom json testing for Survey, create new Fork for Testing and pull request in original collection.
- 03/07
  - Create Status Controller in serverapp & Service,Controller,restController for parameter and status.
- 04/07
  - Create Js & html for parameter & status
- 05/07
  - Add EmailService
- 06/07
  - Add Send Email Survet with Random UUID
- 07/07
  - fix email survey
- 08/07
  - Create directlink email to formbycode
- 09/07
  - fix minor formbycode
- 10/07-12/07
  - add DTO AnswerQuestReq,modif answer service but still error

## **`Ketentuan Implementasi Repository`**

- Silahkan clone repository yang sudah disediakan untuk setiap kelompoknya masing-masing.
- Setalah clone, silahkan masukkan folder project `backend` & `frontend` yang nanti teman-teman buat.

**`NOTE:` di bawah ini merupakan contoh struktur dari folder project.**

```
|-- .gitignore
|-- README.md
|-- serverapp
    |-- .mvn
    |-- src
        |-- main
        |-- test/java/id/co/mii/serverapp
    |-- .gitignore
    |-- mvnw
    |-- mvwn.cmd
    |-- pom.xml
|-- clientapp
    |-- .mvn
    |-- src
        |-- main
        |-- test/java/id/co/mii/clientapp
    |-- .gitignore
    |-- mvnw
    |-- mvwn.cmd
    |-- pom.xml
```
