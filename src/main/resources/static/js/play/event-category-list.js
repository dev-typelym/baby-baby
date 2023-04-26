$('.event-price').each(function() {
    var price = $(this).text();
    price = parseInt(price);
    price = price.toLocaleString();
    $(this).text(price);
});