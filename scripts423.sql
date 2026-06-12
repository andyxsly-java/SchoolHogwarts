SELECT student.name, student.age, faculty.name
FROM student
INNER JOIN faculty ON faculty.id = student.faculty_id;

SELECT student.name, avatar.avatar
FROM student
INNER JOIN avatar ON student.id = avatar.id;