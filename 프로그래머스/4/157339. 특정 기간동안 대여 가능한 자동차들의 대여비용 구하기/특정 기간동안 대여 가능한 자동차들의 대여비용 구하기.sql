# CAR_RENTAL_COMPANY_CAR 테이블과 CAR_RENTAL_COMPANY_RENTAL_HISTORY 테이블과 CAR_RENTAL_COMPANY_DISCOUNT_PLAN 테이블에서 자동차 종류가 '세단' 또는 'SUV' 인 자동차 중 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능하고 30일간의 대여 금액이 50만원 이상 200만원 미만인 자동차에 대해서 자동차 ID, 자동차 종류, 대여 금액(컬럼명: FEE) 리스트를 출력하는 SQL문을 작성해주세요. 결과는 대여 금액을 기준으로 내림차순 정렬하고, 대여 금액이 같은 경우 자동차 종류를 기준으로 오름차순 정렬, 자동차 종류까지 같은 경우 자동차 ID를 기준으로 내림차순 정렬해주세요.

SELECT car.CAR_ID,car.CAR_TYPE,
ROUND((car.DAILY_FEE * 30) * ((100-discount.DISCOUNT_RATE)/100)) as FEE 
FROM CAR_RENTAL_COMPANY_CAR car 
JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY rental ON car.CAR_ID = rental.CAR_ID
JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN discount ON car.CAR_TYPE = discount.CAR_TYPE
WHERE car.CAR_ID not in(SELECT CAR_ID FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY WHERE END_DATE>= '2022-11-01' AND START_DATE <= '2022-12-01') AND discount.DURATION_TYPE like '30%'
GROUP BY car.CAR_ID
HAVING car.CAR_TYPE IN ('세단','SUV') AND (FEE >= 500000 AND FEE < 2000000)
ORDER BY FEE DESC, car.CAR_TYPE ASC,car.CAR_ID DESC