// 쿠키에서 JWT 토큰을 읽어와 변수에 저장
function jwtToken(){
    var jwtToken = null;
    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();
        if (cookie.startsWith("jwtToken=")) {
            jwtToken = cookie.substring("jwtToken=".length, cookie.length);
            break;
        }
    }

    return jwtToken;
}

