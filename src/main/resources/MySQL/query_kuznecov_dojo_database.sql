select * from instructor;

select first_name, second_name, age, phone 
from instructor
where age > 30;

select * from certificate;

select * from sport_club;

select * from instructor_sport_club;

select * from instructor, certificate;


-- INNER JOIN
select *
from certificate
inner join instructor
on instructor.instr_id = certificate.instr_id
order by instructor.instr_id;

-- OUTER LEFT JOIN
select instructor.second_name, certificate.date_pass, certificate.number, certificate.degree 
from certificate
left join instructor
on certificate.instr_id = instructor.instr_id
where instructor.second_name = "Павел";

-- update table_name
-- set column1 = value1, column2 = value2, ..., columnN = valueN
-- where [condition];

-- delete from table_name
-- where [condition]; 