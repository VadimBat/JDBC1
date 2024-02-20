WITH ProjectCosts AS (
	SELECT
		pr.id AS ProjectID,
		SUM(w.SALARY * TIMESTAMPDIFF(MONTH, START_DATE, FINISH_DATE)) AS ProjectCost
	FROM project pr
	JOIN project_worker pw ON pr.ID = pw.PROJECT_ID
	JOIN worker w ON pw.WORKER_ID = w.ID
	GROUP BY pr.ID
)
SELECT
	ProjectID AS ID,
	ProjectCost AS PRICE
FROM ProjectCosts
ORDER BY PRICE DESC;
	