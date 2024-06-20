const table = document.getElementById('table')
const template = document.getElementById('user')
const searchTitle = document.getElementById('search-title')

if (searchParam != null && searchParam !== '') {
    searchTitle.innerText = 'Pretraga klijenata'
    fetchUsers('/name/' + searchParam)
} else {
    searchTitle.innerText = 'Lista klijenata'
    fetchUsers()
}

function fetchUsers(url = '') {
    fetch(`http://localhost:8080/api/user${url}`)
        .then((rsp) => rsp.json())
        .then((data) => {
            if (data.length === 0) {
                alert("Klijent nije pronađen")
                fetchUsers()
                return
            }
            data.forEach((user) => {
                const copy = template.content.cloneNode(true)
                copy.querySelector(".id").innerText = user.id
                copy.querySelector(".name").innerText = user.name
                copy.querySelector(".surname").innerText = user.surname
                copy.querySelector('.created').innerText = formatDate(user.createdAt)
                copy.querySelector('.updated').innerText = formatDate(user.updatedAt)
                copy.querySelector(".edit").href = `./edit.html?id=${user.id}`
                copy.querySelector('.remove').addEventListener('click', () => {
                    if (confirm(`Želite obrisati klijenta ${user.name} ${user.surname}`)) {
                        fetch(`http://localhost:8080/api/user/${user.id}`, {
                            method: 'DELETE',
                        })
                            .then(rsp => {
                                if (rsp.status === 204) {
                                    window.location.href = './index.html'
                                    return
                                }
                                alert(`Brisanje klijenta nije uspelo (HTTP ${rsp.status})`)
                            })
                    }
                })
                table.appendChild(copy)
            })
        })
}
