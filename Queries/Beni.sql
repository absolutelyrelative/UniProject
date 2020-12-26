SELECT *
FROM players
WHERE us_reg_date >= '2000-07-05'
  AND us_reg_date < '2011-11-10' + interval 1 day
  
SELECT *
FROM players
WHERE DATE(us_reg_date) BETWEEN '2000-07-05' AND '2011-11-10'