/*
* 提交评论
* */
function post(){
    var questionId = $("#question_id").val();
    var content = $("#commentContent").val();
    comment2Target(questionId, 1, content);
}
function comment2Target(targetId, type, content) {


    if(!content) {
        alert("回复内容不能为空");
        return;
    }
    $.ajax({
        type:"POST",
        url:"/comment",
        data:JSON.stringify({
            "parentId" : targetId,
            "content" : content,
            "type" : type
        }),
        contentType:"application/json",
        dataType: "json",
        success: function (response) {
            if(response.code == 200) {
                window.location.reload();
            } else {
                if(response.code == 2003) {//need login
                    var isAccepted = confirm(response.message);
                    if(isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=c5da5eca0acb859f0405&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);

                    }
                } else {
                    alert(response.message);
                }

            }
            console.log(response);
        }

    });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    console.log(commentId+" "  + content);
    comment2Target(commentId, 2, content);
}







/*
* 展开二级评论
* */
function collapseComments(e){
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    var collapse = e.getAttribute("data-collapse");
    if(collapse) {
        comments.toggleClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");

    } else {        //标记二级评论展开状态
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.toggleClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {

            $.getJSON("/comment/" + id, function (data) {
                if(data.data != null) {
                    $.each(data.data.reverse(), function (index, comment) {
                        var mediaLeftElement = $("<div/>", {
                            "class": "media-left"
                        }).append($("<img/>", {
                            "class": "media-object img-rounded",
                            "src": comment.user.avatarUrl
                        }));

                        var mediaBodyElement = $("<div/>", {
                            "class": "media-body"
                        }).append($("<h5/>", {
                            "class": "media-heading",
                            "html": comment.user.name
                        })).append($("<div/>", {
                            "html": comment.content
                        })).append($("<div/>", {
                            "class": "menu"
                        }).append($("<span/>", {
                            "class": "pull-right",
                            "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                        })));

                        var mediaElement = $("<div/>", {
                            "class": "media"
                        }).append(mediaLeftElement).append(mediaBodyElement);

                        var commentElement = $("<div/>", {
                            "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                        }).append(mediaElement);

                        subCommentContainer.prepend(commentElement);
                    });
                }

                //展开二级评论
                comments.toggleClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }



    }


}

function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}