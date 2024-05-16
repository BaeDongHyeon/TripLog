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
        } else {
            alert('사용이 가능한 이메일입니다.');
        }
        // 성공
    }).fail(function (result) {
        alert('오류가 발생하였습니다.');
        // 실패
    });
});