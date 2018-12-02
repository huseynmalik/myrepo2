var globBtnId = '';
var globStudentId = 0;

$(function () {

    $('body').layout({ applyDemoStyles: true });
    $('.ui-layout-center,.ui-layout-south,.ui-layout-north,.ui-layout-east,.ui-layout-west').css('background-color','aqua');

    $('#newStudentDialogId').dialog({
        height: 400,
        width: 400,
        title: 'New Student',
        autoOpen: false,
        buttons: {
            "Save": function () {
                addStudent();

            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#editStudentDialogId').dialog({
        height: 400,
        width: 400,
        title: 'Update Student',
        autoOpen: false,
        buttons: {
            "Update": function () {
                updateStudent();
                $(this).dialog('close');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#newTeacherDialogId').dialog({
        height: 400,
        width: 400,
        title: 'New Teacher',
        autoOpen: false,
        buttons: {
            "Save": function () {
            //    addStudent();
                $(this).dialog('close');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#newScheduleDialogId').dialog({
        height: 300,
        width: 400,
        title: 'New Schedule',
        autoOpen: false,
        buttons: {
            "Save": function () {
                addSchedule();
                $(this).dialog('close');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });

    $('#editScheduleDialogId').dialog({
        height: 300,
        width: 400,
        title: 'Update Schedule',
        autoOpen: false,
        buttons: {
            "Update": function () {
              //  addSchedule();
                $(this).dialog('close');
            },
            "Close": function () {
                $(this).dialog('close');
            }
        }
    });


    $('#studentDataBtnId').click(function () {
        getStudentList();
    });

    $('#teacherDataBtnId').click(function () {
        getTeacherList();
    });

    $('#scheduleDataBtnId').click(function () {
        getScheduleList();
    });

    $('.btnDesign').click(function () {
        $('#keywordId').val('');
        globBtnId = $(this).attr('id');
     //   alert(globBtnId);
    });



    $('#newBtnId').click(function () {

        switch (globBtnId) {
            case 'studentDataBtnId':
                $('#newStudentDialogId').load('views/newStudent.jsp',function () {
                    $(this).dialog('open');
                });
                break;
            case 'teacherDataBtnId':
                $('#newTeacherDialogId').load('views/newTeacher.jsp',function () {
                   $(this).dialog('open');
                });
                break;
            case 'lessonDataBtnId':
                alert('Lesson');

                break;
            case 'scheduleDataBtnId':
                $('#newScheduleDialogId').load('cs?action=newSchedule',function () {
                    $(this).dialog('open');
                });
                break;
            default:
                alert('Please , select menu!');
        }



    });

     $('#searchBtnId').click(function () {
         var keyword = $('#keywordId').val();
         switch (globBtnId) {
             case 'studentDataBtnId':
                searchStudentData(keyword);
                 break;
             case 'teacherDataBtnId':

                 break;
             case 'lessonDataBtnId':
                 alert('Lesson');

                 break;
             case 'scheduleDataBtnId':
                 searchScheduleData(keyword);
                 break;
             default:
                 alert('Please , select menu!');
         }


     });

     $('#keywordId').keyup(function () {
         var keyword = $('#keywordId').val();
         switch (globBtnId) {
             case 'studentDataBtnId':
                 searchStudentData(keyword);
                 break;
             case 'teacherDataBtnId':

                 break;
             case 'lessonDataBtnId':
                 alert('Lesson');

                 break;
             case 'scheduleDataBtnId':
                 searchScheduleData(keyword);
                 break;
             default:
                 alert('Please , select menu!');
         }
     })


});



function getStudentList() {
    $.ajax({
         url: 'cs?action=getStudentList',
         type: 'GET',
         dataType: 'html', // text , xml , json
         success: function (data) {
             $('.ui-layout-center').html(data);
         },
        error: function (data) {
            alert('Error: '+data);
        }
      });
}

function getTeacherList() {

    $.ajax({
       url: 'cs?action=getTeacherList',
       type: 'GET',
       dataType: 'html',
       success: function (data) {
           $('.ui-layout-center').html(data);
       }
    });
}

function getScheduleList() {

    $.ajax({
       url: 'cs?action=getScheduleList',
       type: 'GET',
       dataType: 'html',
       success: function (data) {
           $('.ui-layout-center').html(data);
       }
    });

}

function addStudent() {
    var name = $('#nameId').val();
    var surname = $('#surnameId').val();
    var dob = $('#dobId').val();
    var phone = $('#phoneId').val();
    var email = $('#emailId').val();
    var address = $('#addressId').val();

    var data = {
        name:name,
        surname:surname,
        dob:dob,
        phone:phone,
        email:email,
        address:address
    };

    $.ajax({
       url: 'cs?action=addStudent',
       type: 'POST',
       dataType: 'text',
       data: data,
       success: function (resp) {
           if (resp == 'exist') {
               alert('Student already exist!');
           } else
           if (resp == 'success') {
               alert('Student has been successfully added!');
               getStudentList();
               $('#newStudentDialogId').dialog('close');
           } else {
               alert('Problem!Student has not been successfully added!');

           }
       }

    });
}

function editStudent(studentId) {
    globStudentId = studentId;

     $.ajax({
        url: 'cs?action=editStudent',
        type: 'GET',
        data: 'studentId='+studentId,
        dataType: 'html',
        success: function (data) {
            $('#editStudentDialogId').html(data);
            $('#editStudentDialogId').dialog('open');
        }
     });

}

function updateStudent() {
    var name = $('#nameIdU').val();
    var surname = $('#surnameIdU').val();
    var dob = $('#dobIdU').val();
    var phone = $('#phoneIdU').val();
    var email = $('#emailIdU').val();
    var address = $('#addressIdU').val();

    var data = {
        name:name,
        surname:surname,
        dob:dob,
        phone:phone,
        email:email,
        address:address,
        studentId: globStudentId
    };

    $.ajax({
        url: 'cs?action=updateStudent',
        type: 'POST',
        dataType: 'text',
        data: data,
        success: function (resp) {
            if (resp == 'success') {
                alert('Student has been successfully updated!');
                getStudentList();
            } else {
                alert('Problem!Student has not been successfully updated!');

            }
        }

    });
}

function deleteStudent(studentId,name,surname) {

   var isDelete = confirm('Are you sure?');
    if (isDelete) {
        $.ajax({
            url: 'cs?action=deleteStudent',
            type:'POST',
            data:'studentId='+studentId,
            dataType: 'text',
            success: function (data) {
                if (data == 'success') {
                    alert(name+' '+surname+' has been successfully deleted!');
                    getStudentList();
                } else {
                    alert('Problem!Student has not been successfully deleted!');

                }
            }
        });
    }


}

function addSchedule() {
    var studentCombo = $('#studentComboId').val();
    var teacherCombo = $('#teacherComboId').val();
    var lessonCombo = $('#lessonComboId').val();

    var data = {
        studentCombo:studentCombo,
        teacherCombo:teacherCombo,
        lessonCombo:lessonCombo
    };

    $.ajax({
       url: 'cs?action=addSchedule',
       type: 'POST',
       data: data,
       dataType: 'text',
       success: function (data) {
           if (data == 'success') {
               alert('Schedule has been successfully added!');
               getScheduleList();
           } else {
               alert('Problem!Schedule has not been successfully added!');

           }
       }
    });
}

function editSchedule(scheduleId) {

    $.ajax({
        url: 'cs?action=editSchedule',
        type: 'GET',
        data:'scheduleId='+scheduleId,
        dataType: 'html',
        success: function (data) {
          $('#editScheduleDialogId').html(data);
          $('#editScheduleDialogId').dialog('open');
        }
    })

}

function searchScheduleData(keyword) {

      $.ajax({
          url: 'cs?action=searchScheduleData',
          type: 'GET',
          data: 'keyword='+keyword,
          dataType: 'html',
          success: function (response) {
              $('.ui-layout-center').html(response);
          }

      });


}

function searchStudentData(keyword) {
    $.ajax({
        url: 'cs?action=searchStudentData',
        type: 'GET',
        data: 'keyword='+keyword,
        dataType: 'html',
        success: function (response) {
            $('.ui-layout-center').html(response);
        }

    });
}

function advancedSearchSchedule() {

    var lessonId = $('#advLessonComboId').val();
    var teacherId = $('#advTeacherComboId').val();
    var beginDate = $('#advBeginDateId').val();
    var endDate = $('#advEndDateId').val();

    var data = {
        lessonId:lessonId,
        teacherId:teacherId,
        beginDate:beginDate,
        endDate:endDate
    };

    $.ajax({
        url: 'cs?action=advancedSearchSchedule',
        type: 'GET',
        data: data,
        dataType: 'html',
        success: function (response) {
            $('#scheduleDivId').html(response);
        }
    })

}