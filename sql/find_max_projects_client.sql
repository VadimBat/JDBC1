SELECT client.NAME AS NAME,
       COUNT(project.ID) AS PROJECT_COUNT
FROM client client
         LEFT JOIN
     project project ON client.ID = project.CLIENT_ID
GROUP BY client.ID, client.NAME
ORDER BY PROJECT_COUNT DESC
LIMIT 1;