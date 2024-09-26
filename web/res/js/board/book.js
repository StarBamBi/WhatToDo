$('#search').click( function() {
    if($('#query').val() == ''){
        alert('검색어를 입력해 주세요');
        $('#query').focus();
    }else{
        callAjax($('#query').val());
        $('#name').val("");
    }
});

function callAjax(query) {
    $.ajax({
        url:'book_proxy.php',
        dataType:'json',
        type:'POST',
        data:{'query':query},
        success:function(result){
            $('#result').html('');
            if(result['channel']['display'] > 0){
                $('.__oldlist').remove();
                for(var i in result['channel']['item']){
                    var item = result['channel']['item'][i];
                    $html = template('__template', item);
                    $html.addClass('__oldlist');
                    $('#list').append($html);
                    $('.__oldlist').show();
                }

            }else{
                $('#result').html('');
                $('#result').append("데이터가 없거나 정상적으로 로딩 되지 않았습니다.");
            }
        }
    });
};

function template(template_id, params){
    var c = $('.' + template_id).clone();
    var html = $('<div>').append(c).html();
    for(var key in params){
        html = html.replace(new RegExp('#' + key + '#', 'g'), params[key]);
    }
    html = html.replace(/#template_([^#]*)#/g, '$1');
    var $html = $(html).removeClass(template_id).removeClass('__template');
    return $html;
}