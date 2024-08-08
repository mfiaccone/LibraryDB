document.addEventListener('DOMContentLoaded', initializePage);

function areYouSure(borrowId) {
    let confirmation = confirm("Are you sure you want to return this book?");

    if (confirmation) {
        // Make the POST request to the /user/return endpoint
        fetch('/user/return?borrowId=' + borrowId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
            .then(response => {
                if (response.ok) {
                    // Redirect the user to the bookshelf page
                    window.location.href = '/user/bookshelf';
                } else {
                    alert('There was an error returning the book. Please try again.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('There was an error returning the book. Please try again.');
            });
    }
}

function initializePage() {
    // Get all the "Return Book" buttons
    const returnButtons = document.querySelectorAll('.return-book-button');

    // Add the click event listener to each button
    returnButtons.forEach(button => {
        button.addEventListener('click', () => {
            const borrowId = button.dataset.borrowId;
            areYouSure(borrowId);
        });
    });
}