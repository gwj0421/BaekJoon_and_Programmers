-- 코드를 입력하세요
SELECT ao.ANIMAL_ID,ao.NAME FROM ANIMAL_OUTS ao
WHERE ao.ANIMAL_ID NOT IN (SELECT ai.ANIMAL_ID FROM ANIMAL_INS ai)
ORDER BY ao.ANIMAL_ID ASC