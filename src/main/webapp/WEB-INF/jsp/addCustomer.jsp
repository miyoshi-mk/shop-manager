<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客登録</title>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="sidebar.jsp" />

<main class="main-content">
	<h2>顧客登録</h2>
	<div class="form-container">
		<form action="${pageContext.request.contextPath}/add-customer" method="post">
		    <div class="form-group">
			    <label>名前：</label><br>
			    <input type="text" name="name" required><br>
		    </div>
		    <div class="form-group">
			    <label>Email：</label><br>
			    <input type="email" name="email"><br>
		    </div>
		    <div class="form-group">
			    <label>電話番号：</label><br>
			    <input type="text" name="phone"><br>
		    </div>
		    <div class="form-group">
			    <label>郵便番号：</label><br>
			    <input type="text" id="zipcode" name="zipcode" maxlength="8" placeholder="例：100-0001 または 1000001">
			    <button type="button" id="searchBtn">検索</button><br>
		    </div>
		    <div class="form-group">
			    <label>住所：</label><br>
			    <textarea id="address" name="address" rows="3" cols="30"></textarea><br>
		    </div>
		     <div class="form-actions">
		    	<button type="submit">登録</button>
		    </div>
		</form>
		<p><a href="${pageContext.request.contextPath}/page?name=customerList">顧客一覧に戻る</a></p>
	</div>
</main>
<p style="color:red;">${error}</p>
<a href="${pageContext.request.contextPath}/customerList">顧客一覧へ戻る</a>

<script>
(function(){
  document.addEventListener("DOMContentLoaded", function(){
    console.log("DOMContentLoaded - script loaded");

    const btn = document.getElementById("searchBtn");
    const zipcodeInput = document.getElementById("zipcode");
    const addressField = document.getElementById("address");

    if(!btn || !zipcodeInput || !addressField){
      console.error("必要な要素が見つかりませんでした:", {btn, zipcodeInput, addressField});
      return;
    }

    btn.addEventListener("click", async function(e){
      try {
        console.log("検索ボタンクリック");
        let zipcode = zipcodeInput.value.trim();
        if(zipcode === ""){
          alert("郵便番号を入力してください。");
          return;
        }

        zipcode = zipcode.replace(/\D/g, "");
        console.log("整形後のzipcode:", zipcode);

        if(zipcode.length !== 7){
          alert("郵便番号は7桁で入力してください（例：1000001）。");
          return;
        }

        const url = "https://zipcloud.ibsnet.co.jp/api/search?zipcode=" + zipcode;
        console.log("fetch開始:", url);
        const resp = await fetch(url);
        console.log("fetch終了, status:", resp.status);

        if(!resp.ok){
          console.error("HTTPエラー:", resp.status);
          alert("住所検索に失敗しました（HTTPエラー）。");
          return;
        }

        const data = await resp.json();
        console.log("API応答:", data);

        if(data && data.results && data.results.length > 0){
          const r = data.results[0];
          const full = (r.address1 || "") + (r.address2 || "") + (r.address3 || "");
          addressField.value = full;
          console.log("住所セット:", full);
        } else {
          console.warn("住所が見つかりませんでした。API結果:", data);
          alert("住所が見つかりませんでした。");
        }
      } catch(err) {
        console.error("郵便番号検索で例外:", err);
        alert("住所検索中にエラーが発生しました。コンソールを確認してください。");
      }
    }); // btn click
  }); // DOMContentLoaded
})();
</script>

</body>
</html>