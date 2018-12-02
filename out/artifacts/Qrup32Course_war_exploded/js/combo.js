

function getTeacherComboByLessonId(lessonId) {
    
     $.ajax({
        url: 'cs?action=getTeacherComboByLessonId',
        type: 'GET',
        data: 'lessonId='+lessonId,
        dataType: 'html',
        success: function (response) {
            $('#advTeacherComboId').html(response);
        } 
     });
    
}