document.addEventListener('DOMContentLoaded', function() {
    const loginButton = document.getElementById('login-btn');
    const signupButton = document.getElementById('signup-btn'); 

    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';

    if (isLoggedIn) {
        loginButton.textContent = '마이페이지';
        loginButton.id = 'mypage-btn';
        signupButton.textContent = '로그아웃';
        signupButton.id = 'logout-btn';

        document.getElementById('mypage-btn').addEventListener('click', function() {
            window.location.href = 'mypage.html';
        });

        document.getElementById('logout-btn').addEventListener('click', function() {
            localStorage.setItem('isLoggedIn', 'false'); // 로그아웃 시 상태 초기화
            window.location.reload();
        });
    } else {
        loginButton.addEventListener('click', function() {
            window.location.href = 'login.html';
        });

        signupButton.addEventListener('click', function() {
            window.location.href = 'signup.html';
        });
    }
});
