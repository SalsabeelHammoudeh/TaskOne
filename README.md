You need to implement a back-end server side using spring, jpa and rest APIs. 
Use the following models:

1-	Employee(id,name,age,phoneNumer,address,gender,baseSalaray,currentSalary,hireDate,currentSalary,department,role)
2-	Department(id,name,employees,manager)
3-	Address(id,location,..)

-Each employee can have more than one department
-Each department has a list of employees and only one manager(which is an employee)
-Each employee has only one address, but the same address can belong to more than one employee
-currentSalary of the employee is not present in the database and should be calculated according to the hire date(every year before current year equals 200$ + the base salary)
-support pagination and sorting (sort by whatever you want)
-In addition to basic CRUD APIs (GET,POST,PUT,DELETE) you need to implement the following:

a- get all names of employees who work in the specific department.
b-get employees by gender
c- get all managers of departments
