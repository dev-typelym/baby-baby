/* announcement.js */
let $title = $('.article');
let $content = $('.admin-writed');

$title.on('click', function () {
    let index = $(this).parent().index();
    let $currentContent = $content.eq(index);

    if ($currentContent.is(':visible')) {
      $currentContent.hide();
    } else {
      $content.hide();
      $currentContent.show();
    }
});