let isEmailValid = false;

function resetEmailValidationStatus() {
    isEmailValid = false;
}

$('#duplicateCheckButton').on('click', function () {
    let data = {
        email: $('#email').val()
    };

    $.ajax({
        type: 'POST',
        url: '/email/duplicate/check',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json'
    }).done(function (result) {
        if (result) {
            alert('이미 존재하는 이메일입니다.')
            isEmailValid = false;
        } else {
            alert('사용이 가능한 이메일입니다.');
            isEmailValid = true;
        }
        // 성공
    }).fail(function (jqXHR) {
        if (jqXHR.status === 400) {
            let errors = jqXHR.responseJSON;
            let errorMessage = errors.email;
            alert(errorMessage);
            isEmailValid = false;
        } else {
            alert('오류가 발생하였습니다.');
            isEmailValid = false;
        }
    });
});

// 이메일 입력 필드가 변경될 때 상태 초기화
$('#email').on('input', function () {
    resetEmailValidationStatus();
});

// 가입하기 버튼 클릭 시 이메일 검증 상태를 확인
$('#signUpButton').on('click', function () {
    if (!isEmailValid) {
        alert('이메일 중복 확인을 완료해주세요.');
        return false;
    }

    $('#signUpForm').submit();
});