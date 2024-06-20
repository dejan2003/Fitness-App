const name = document.getElementById('name')
const surname = document.getElementById('surname')

document.getElementById('save').addEventListener('click', () => {
    if (name.value == null || name.value === '') {
        alert('Ime klijenta ne sme biti prazno')
        return
    }

    if (surname.value == null || surname.value === '') {
        alert('Prezime klijenta ne sme biti prazno')
        return
    }

    fetch('http://localhost:8080/api/user', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: name.value,
            surname: surname.value,
        })
    }).then(rsp => {
        if (rsp.ok) {
            window.location.href = './index.html'
            return
        }
        alert(`Dodavanje klijenta nije uspelo (HTTP ${rsp.status})`)
    })
})