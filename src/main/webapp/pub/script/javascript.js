


// document.addEventListener('DOMContentLoaded', function() {
//     const returnForms = document.querySelectorAll('form[id^="returnForm"]');
//
//     returnForms.forEach(form => {
//         form.addEventListener('submit', function(event) {
//             event.preventDefault();
//
//             const bookTitle = this.closest('.card-body').querySelector('.card-title').textContent;
//
//             if (confirm(`Are you sure you want to return "${bookTitle}"?`)) {
//                 this.submit();
//             }
//         });
//     });
// });

document.addEventListener('DOMContentLoaded', initializePage);

function areYouSure() {
    let confirmation = confirm("Are you sure you want to return this book?");

    if (confirmation) {
        
    }
}

function initializePage() {

}