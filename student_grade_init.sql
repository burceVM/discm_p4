INSERT INTO students (name, username, password)
VALUES ('Jane Doe', 'jane.doe', 'password123');
INSERT INTO courses (course_code, course_name, term)
VALUES ('MATH101', 'Calculus I', 'Fall');
INSERT INTO grades (user_id, course_code, course_grade, term, year)
VALUES (1, 'MATH101', 'A', 'Fall', 2023);
SELECT * FROM grades;
