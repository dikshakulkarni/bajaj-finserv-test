SELECT 
    d.department_name AS DEPARTMENT_NAME,
    t.total_salary AS SALARY,
    CONCAT(e.first_name, ' ', e.last_name) AS EMPLOYEE_NAME,
    TIMESTAMPDIFF(YEAR, e.dob, CURDATE()) AS AGE
FROM
(
    SELECT 
        e.emp_id,
        e.department,
        SUM(p.amount) AS total_salary,
        ROW_NUMBER() OVER (
            PARTITION BY e.department 
            ORDER BY SUM(p.amount) DESC
        ) AS rn
    FROM payments p
    JOIN employee e ON p.emp_id = e.emp_id
    WHERE DAY(p.payment_time) <> 1
    GROUP BY e.emp_id, e.department
) t
JOIN employee e ON t.emp_id = e.emp_id
JOIN department d ON e.department = d.department_id
WHERE t.rn = 1;
