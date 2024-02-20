WITH YoungestOldestCTE AS (
    SELECT
        'YOUNGEST' AS TYPE,
        NAME,
        BIRTHDAY,
        RANK() OVER (ORDER BY BIRTHDAY DESC) AS rnk
    FROM worker
    UNION
    SELECT
        'OLDEST' AS TYPE,
        NAME,
        BIRTHDAY,
        RANK() OVER (ORDER BY BIRTHDAY ASC) AS rnk
    FROM worker
)
SELECT
    TYPE,
    NAME,
    BIRTHDAY
FROM YoungestOldestCTE
WHERE rnk = 1;
