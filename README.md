
<ol>
<li>
  Hello world：http://localhost:8080/HelloWorld/
</li>
<li>
  Swagger路徑：<br/>
  http://localhost:8080/HelloWorld/webjars/swagger-ui/index.html
</li>
<li>
新增/刪除/修改/查詢(單一以及分頁)會員功能api。<br>
  /api/member/*<br>
<br>
訂單訂購api，會員可以訂購產品。<br>
  /api/boughtItem/*<br>
<br>
訂單查詢api，會員可以根據訂單編號或產品名稱或購買日期做分頁查詢。<br>
  /api/boughtItem/*<br>
<br>
統計api，統計訂單數大於n的會員資料。<br>
  /api/caculate/*<br>
</li>
<li>
  產品控制<br>
  /api/product/*<br>
</li>
</ol>
  


<h2>TODO:</h2>
<ol>
<li>2024/07/21: Security目前沒有啟動，要再看怎麼設定比較好</li>
<li>
  <del>spring-WebFlux 應該要跟 spriing webmvc 脫鉤</del>
</li>
<li>
  <del>spring-doc 應該要換到 weflux 版本</del>
</li>
  
</ol>
