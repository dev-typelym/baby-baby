const $banner = $(".banner");
const width = 344;

var bannerImage = [
    "../../../static/images/nowKids/001.jpg",
    "../../../static/images/nowKids/002.jpg",
    "../../../static/images/nowKids/003.jpg",
    "../../../static/images/nowKids/004.jpg",
    "../../../static/images/nowKids/005.jpg",
];

const totalWidth = width * (bannerImage.length + 2);
$banner.css("width", `${totalWidth}px`);

bannerImage.forEach((e, i) => {
    let text = `
            <div>
                <img src="${bannerImage[i]}">
            </div>
    `;

    $($banner[0]).append(text);
});


