// jQuery 코드
function setResume(e) {
    var file = e.target.files[0];
    var resumeContainer = $("#resume_container");
    var maxFileSize = 2 * 1024 * 1024; // 2MB
    var allowedFileTypes = ["application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"];
    var fileSize = file.size;
    var fileType = file.type;
    var fileName = file.name;
    var fileExtension = fileName.split('.').pop().toLowerCase();

    if ($.inArray(fileType, allowedFileTypes) == -1 || fileSize > maxFileSize) {
        alert("2MB 이하의 PDF, DOC, DOCX 파일만 등록 가능합니다.");
        return false;
    }

    var reader = new FileReader();
    reader.onload = function(e) {
        var fileContent = e.target.result;
        var fileSizeInKb = Math.round(fileSize / 1024);
        var fileTypeIcon = '<i class="fa fa-file-o" aria-hidden="true"></i>';
        if (fileType == "application/pdf") {
            fileTypeIcon = '<i class="fa fa-file-pdf-o" aria-hidden="true"></i>';
        } else if (fileType == "application/msword" || fileType == "application/vnd.openxmlformats-officedocument.wordprocessingml.document") {
            fileTypeIcon = '<i class="fa fa-file-word-o" aria-hidden="true"></i>';
        }
        var resumeInfo = '<div class="ResumeInfo">' +
            '<div class="ResumeInfo_Name">' + fileName + '</div>' +
            '<div class="ResumeInfo_Size">' + fileSizeInKb + 'KB</div>' +
            '<div class="ResumeInfo_Type">' + fileExtension.toUpperCase() + '</div>' +
            '<div class="ResumeInfo_Cancel"><a href="#" class="CancelLink"><i class="fa fa-times" aria-hidden="true"></i></a></div>' +
            '</div>';
            resumeContainer.html(resumeInfo);
            };
            reader.readAsDataURL(file);
        }

// 취소 버튼 클릭 시
resumeContainer.on("click", ".CancelLink", function() {
    $(this).closest(".ResumeInfo").remove();
    $("#resume").val("");
});