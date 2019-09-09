# Human-Resources-DB
Human resources management is a challenge in our times. It is a real factor of competitiveness, since it is a function with high added value. That said, companies need to focus on the quality and performance of the management system. Thus and thanks to a good human resources administration system companies can reach a satisfactory level in terms of organization of information. The latter is considered the key to the success of human resources management and any other type of management.
The purpose of this work was to answer a problem, that of finding the most effective way to facilitate the management of employee information, the techniques most suited to the requirements of the function and the nature of the information, and finally develop tables grouping the various elements of analysis for a better interpretation of things. In this context we have managed to set up a database that is easy to manage, and promotes transparency, the quality of information and the reduction of time with low added value. In addition this application will serve as a basis to ensure good communication within the company and between the services in interactions. Enhancement features we would like to add to our application in future work are the ability to manage employees absences, holidays, and sick leaves, manage job applications (store resumes), bonuses, and departures, manage training provided by the company and employees performance evaluations and finally the ability to keep track of data on overtime work.

## Demonstration / query with sub-query
The query below demonstrate the use of a sub-query to return the employees that have been working for more than one year:
SELECT first_name, last_name, date_of_bird, gender, email, phone, salary, fk_job, fk_department, active FROM human_resources.employees WHERE employee_id in (SELECT fk_employee FROM human_resources.job_history WHERE hire_date < DATE_SUB(NOW(),INTERVAL 1 YEAR));


## Demonstration / group-by clause
The query below demonstrate the use of a group by clause to return the number of employees by department:
SELECT count(*) as quant, d.name FROM human_resources.employees e INNER JOIN departments d ON (e.fk_department=d.department_id) GROUP BY fk_department
