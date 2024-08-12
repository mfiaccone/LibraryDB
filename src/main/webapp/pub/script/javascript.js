document.addEventListener('DOMContentLoaded', initializePage);

// this listens for anything labeled .return-book-form
function initializePage() {
    const returnForms = document.querySelectorAll('.return-book-form');
    returnForms.forEach(form => {
        form.addEventListener('submit', handleReturnBook);
    });
}

function handleReturnBook(event) {
    event.preventDefault(); // this prevents the form from submitting by default

    const form = event.target;
    let confirmation = confirm("Are you sure you want to return this book?");

    if (confirmation) {
        submitReturnBook(form);
    }

}

function submitReturnBook(form) {
    fetch(form.action, {
        method: 'POST',
        body: new URLSearchParams(new FormData(form))
    })
        .then(response => {
            if (response.ok) {
                // this part refreshes the page
                window.location.reload();
            } else {
                alert('There was an error returning the book. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('There was an error returning the book. Please try again.');
        });
}
