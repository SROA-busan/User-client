# User-client
### SROA User-Client앱
### 네이밍 규칙
* activity = 메뉴_기능_화면종류 ex) SearchCurrentActivity
* lqyout = 메뉴_화면종류(activity, fragment)_기능 ex) reserve_fragment_input
* id = 현 페이지_기능   
ex) search_detail_name, search_detail_addButton
  status : 
  * 0-> 예약완료 
  * 1 -> 처리 완료 (endDate NotNull) <- 완전히 종결된 시간/ 엔지어가 방문해서 찍는거
  * 2 -> 엔지니어가 수령
  * 3 -> 엔지니어가 수리 완료
  /수리완료 시점에 고객에게 반납 예약을 하세요 라고 떠야된다 이때 찍은 날짜는 EndDate로 
  * 4 -> 고객이 반납 서비스를 예약완료 (endDate NotNull) <- 예약된 시간
  /startDate는 예약 완료 시점에 저장된 그대로(예약완료에 찍힌 startDate)
  * 5 -> 평가 완료 (endDate NotNull)
* startDate : 서비스 받을날짜
* endDate : 서비스, 반납 받을날짜