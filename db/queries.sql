(1) Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.

SELECT ip, count(0) FROM ip_access where access_date between '2017-01-01 13:00:00' and '2017-01-01 14:00:00' group by ip having count(0) > 200

(2) Write MySQL query to find requests made by a given IP.

SELECT * FROM ip_access where ip = '192.168.169.194'

