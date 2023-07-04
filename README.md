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
- Modul History, Status
- Modul Client, Employee

## **`Draft Design & Documentation`**

- BPMN : [Link](https://drive.google.com/file/d/1FOQ5H6A1Q47xzhpKvuQHsagoDPihBm-8/view?usp=sharing)
- Use Case Diagram : [Link](https://drive.google.com/file/d/10AuxibxFDquCuNYFV5Ty2f1Mw1vJyIjr/view?usp=sharing)
- Entity Relational Diagram : [Link](https://drive.google.com/file/d/1X94O6mTQVWFhbWXJA1D2281dAGN6U6ht/view?usp=sharing)
- Data Dummy : [Link](https://docs.google.com/spreadsheets/d/10gDpG6SatiQW8xmxUQlRNW3e7XbkgA5qFfhq4r42nl4/edit?usp=sharing)
- Postman : [Link](https://documenter.getpostman.com/view/27540842/2s93zCZLfV)

## **`Project Task`**

- **02 Team Start**

1. 21/06 - 28/06
   - Initiate ideas for team projects
   - Create and improve ERD, BPMN, Usecase Diagram, and Data Dummy, with discussion with the team together.

- **Riki Setiyawan**

1. 28/06 Konfigurasi JPA pada serverapp
2. 02/07 Add controller, service & repository pada clientapp
3. 02/07 Update clientapp

- **Habibullah Dzaky Musthafa**

1. 28/06 Add all models
2. 02/07
   - Fix controller, service, & repository serverapp
   - Add model, service, controller, restcontroller clientapp
   - Add security, rest template interceptor, layout dialect
   - Add controller & service login
   - Add html, js question & login
3. 03/07
   - Fix bug service serverapp
   - Fix controller & model question
   - Fix html, js & cs

- **Yoga Sadewa**

1. 28/06
   - Add data dummy to database
2. 30/06
   - Add controller, service & repository for Survey needs (Section,Question,Answer,Result,Client) in serverapp
3. 01/07
   - Add controller, service & repository for Security needs (User,Role,Privilege,etc) in serverapp
   - Create a postman workspace and collection, and perform json testing to test HTTP request Method in Section,Question,Answer
4. 02/07
   - Configure Postman by creating a workspace for the team, which will allow the team to work together as editor in a single workspace.
   - Create HTTP Method request and perfom json testing for Survey, create new Fork for Testing and pull request in original collection.
4. 03/07
   - Create Status Controller in serverapp & Service,Controller,restController for parameter and status.
5. 04/07
   - Create Js & html for parameter & status
 
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
